package video.api.androidkotlinsdk.http

import okhttp3.Request
import video.api.videouploader_module.CallBack

interface UploaderRequestExecutor {
    fun execute(request: Request, callback: CallBack)
}

