package video.api.videouploader_module
import android.os.Handler
import android.os.Looper
import androidx.core.os.HandlerCompat
import okhttp3.*
import org.json.JSONObject
import video.api.androidkotlinsdk.model.ApiError
import java.io.IOException

class CallbackImpl(
    private val callback: CallBack
): Callback {
    val mainThreadHandler: Handler = HandlerCompat.createAsync(Looper.getMainLooper())

    override fun onFailure(call: Call, e: IOException) {
        mainThreadHandler.post {
            callback.onFatal(e)
        }
    }

    override fun onResponse(call: Call, response: Response) {
        response.use {
            when (response.code) {
                200, 201 -> {
                    val body = JSONObject(response.body!!.string())
                    mainThreadHandler.post {
                        callback.onSuccess(body)
                    }
                }
                204 -> {
                    mainThreadHandler.post {
                        callback.onSuccess(JSONObject())
                    }
                }
                400, 404 -> {
                    val body = JSONObject(response.body!!.string())
                    mainThreadHandler.post {
                        callback.onError(ApiError(body))
                    }
                }
                else -> {
                    mainThreadHandler.post {
                        callback.onFatal(IOException("Unexpected code $response"))
                    }
                }
            }
        }
    }
}