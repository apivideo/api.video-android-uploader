package video.api.androidkotlinsdk.model

import org.json.JSONObject

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