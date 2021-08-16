package video.api.videouploader_module

import okhttp3.*

class UploaderRequestExecutorImpl(
    private val client: OkHttpClient
) : UploaderRequestExecutor {

    override fun execute(request: Request, callback: CallBack) {
        client.newCall(request).enqueue(CallbackImpl(callback))
    }
}