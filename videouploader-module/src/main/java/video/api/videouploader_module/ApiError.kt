package video.api.videouploader_module

import org.json.JSONObject
import video.api.androidkotlinsdk.model.getStringOrNull

class ApiError(val body: JSONObject) {

    val name: String?
        get() = body.getStringOrNull("name")

    val type: String?
        get() = body.getStringOrNull("type")

    val title: String?
        get() = body.getStringOrNull("title")


    override fun toString(): String {
        return body.toString()
    }
}