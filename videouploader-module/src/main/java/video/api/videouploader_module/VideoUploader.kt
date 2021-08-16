package video.api.videouploader_module

import android.util.Log
import okhttp3.*
import okhttp3.RequestBody.Companion.asRequestBody
import org.json.JSONObject
import video.api.androidkotlinsdk.RequestBodyUtil
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.lang.IllegalArgumentException
import java.util.concurrent.CountDownLatch

class VideoUploader(
    private val baseUri: String,
    private val executorUploader: UploaderRequestExecutor,
    private val client: OkHttpClient,
    private val chunkLength: Long = 1024L * 1024L * 50
)  {
    companion object {
        private val boundary: String = "----WebKitFormBoundary" + System.currentTimeMillis()
    }

    private val userAgent = "api.video uploader (android; v:0.0.5; )"

    init {
        if(chunkLength < 1024L * 1024L * 5 || chunkLength > 1024L * 1024L * 128) {
            throw IllegalArgumentException("Invalid chunk size: must be greater that 5MB and smaller than 128MB");
        }
    }

   // private val chunkLength = 1024L * 1024L * 50

    fun uploadWithDelegatedToken(delegatedToken: String, file: File, callBack: CallBack) {
        return upload("$baseUri/upload?token=$delegatedToken", null,null, file, callBack)
    }

    fun uploadWithAuthentication(bearerToken: String, videoId: String, file: File, callBack: CallBack) {
        return upload("$baseUri/videos/$videoId/source", bearerToken, videoId, file, callBack)
    }

    private fun upload(
        apiPath: String,
        bearerToken: String?,
        videoId: String?,
        file: File,
        callBack: CallBack
    ) {
        val size = file.length()

        return if (size < chunkLength) {
            uploadSmallFile(apiPath, bearerToken, videoId, file, callBack)
        } else {
            uploadBigFile(apiPath, bearerToken, videoId, file, callBack)
        }
    }

    /**
     * @param  videoId Identifier of the video to be uploaded
     * @param  file local video file
     * @return JSONObject or Exception
     * @see    callBack use response as JSONObject
     */
    private fun uploadSmallFile(
        apiPath: String,
        bearerToken: String?,
        videoId: String?,
        file: File,
        callBack: CallBack
    ) {
        val requestBodyBuilder = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("file", file.name, file.asRequestBody())

        if(videoId != null) {
            requestBodyBuilder.addFormDataPart("videoId", videoId);
        }

        val requestBody = requestBodyBuilder.build()

        val requestBuilder = Request.Builder()
            .url(apiPath)
            .addHeader("Content-Type", "multipart/form-data; boundary=$boundary")
            .addHeader("User-Agent", userAgent)
            .post(requestBody)

        if(bearerToken != null) {
            requestBuilder.header("Authorization", "Bearer ${bearerToken}")
        }

        executorUploader.execute(requestBuilder.build(), callBack)
    }

    /**
     * Upload large file by chunks
     *
     * @param videoId
     * @param file
     */
    private fun uploadBigFile(
        apiPath: String,
        bearerToken: String?,
        _videoId: String?,
        file: File,
        callBack: CallBack){
        val fileLength = file.length()

        var videoId = _videoId;
        var result: JSONObject? = null;

        try {
            Log.e("chunkLength", chunkLength.toString())
            var b = ByteArray(chunkLength.toInt())
            var bytesReads = 0

            for (offset in 0 until fileLength step chunkLength){
                var readBytes: Int
                val fileStream = file.inputStream()
                var currentPosition = (offset.toInt()) + chunkLength.toInt() - 1

                // foreach chunk except the first one
                if(offset > 0){
                    // skip all the chunks already uploaded
                    fileStream.skip(offset)
                }

                // if this is the last chunk
                if(currentPosition > fileLength){
                    readBytes = fileStream.read(b, 0, (fileLength - bytesReads).toInt())
                    currentPosition = file.length().toInt() - 1
                }else{
                    readBytes = fileStream.read(b, 0, chunkLength.toInt())
                }
                bytesReads += readBytes

                val byteArrayOutput = ByteArrayOutputStream()
                byteArrayOutput.write(b, 0, readBytes)
                val byteArrayInput = ByteArrayInputStream(byteArrayOutput.toByteArray())
                val videoFile = RequestBodyUtil.create(byteArrayInput)

                val requestBodyBuilder = MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("file", file.name, videoFile)

                if(videoId != null) {
                    requestBodyBuilder.addFormDataPart("videoId", videoId);
                }

                val body = requestBodyBuilder.build();

                val requestBuilder = Request.Builder()
                    .url(apiPath)
                    .addHeader(
                        "Content-Range",
                        "bytes ${offset.toInt()}-$currentPosition/${file.length().toInt()}"
                    )
                    .addHeader("User-Agent", userAgent)
                    .post(body)

                if(bearerToken != null) {
                    requestBuilder.header("Authorization", "Bearer ${bearerToken}")
                }
                val countDownLatch = CountDownLatch(1)

                var apiError: ApiError? = null;
                var fatal: IOException? = null;

                client.newCall(requestBuilder.build()).enqueue(CallbackImpl(object: CallBack {
                    override fun onError(e: ApiError) {
                        apiError = e;
                        countDownLatch.countDown();
                    }

                    override fun onFatal(e: IOException) {
                        fatal = e;
                        countDownLatch.countDown();
                    }

                    override fun onSuccess(r: JSONObject) {
                        videoId = r.getString("videoId");
                        result = r;
                        countDownLatch.countDown();
                    }
                }))

                countDownLatch.await()
                byteArrayOutput.close()
                byteArrayInput.close()
                fileStream.close()

                if(fatal != null) {
                    callBack.onFatal(fatal!!);
                    break;
                }

                if(apiError != null) {
                    callBack.onError(apiError!!);
                    break;
                }
            }
        } catch (e: IOException){
            callBack.onFatal(e);
        }
        result?.let { callBack.onSuccess(it) };
    }

}