package video.api.videouploader_module

import org.json.JSONObject
import java.io.IOException

interface CallBack {
    fun onError(apiError: ApiError)

    fun onFatal(e: IOException)

    fun onSuccess(result: JSONObject)
}