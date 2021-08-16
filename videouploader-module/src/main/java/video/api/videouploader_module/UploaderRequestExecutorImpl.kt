package video.api.androidkotlinsdk.http

import okhttp3.*
import video.api.videouploader_module.CallBack
import video.api.videouploader_module.CallbackImpl

class UploaderRequestExecutorImpl(
    private val client: OkHttpClient
) : UploaderRequestExecutor {

    override fun execute(request: Request, callback: CallBack) {
        client.newCall(request).enqueue(CallbackImpl(callback))
    }
}