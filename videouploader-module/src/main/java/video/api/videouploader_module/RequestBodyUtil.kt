package video.api.videouploader_module

import okhttp3.MediaType
import okhttp3.RequestBody
import okhttp3.internal.closeQuietly
import okio.BufferedSink
import okio.buffer
import okio.source
import java.io.IOException
import java.io.InputStream


class RequestBodyUtil {
    companion object{
        fun create(inputStream: InputStream): RequestBody {
            return object : RequestBody() {
                override fun contentType(): MediaType? {
                    return null
                }

                override fun contentLength(): Long {
                    return try {
                        inputStream.available().toLong()
                    } catch (e: IOException){
                        0
                    }
                }

                @Throws(IOException::class)
                override fun writeTo(sink: BufferedSink) {
                    try {
                        var source = inputStream.source()
                        sink.writeAll(source.buffer())
                    }catch (e: IOException){
                        e.printStackTrace()
                    } finally {
                        inputStream.closeQuietly()
                    }
                }
            }
        }
    }
}