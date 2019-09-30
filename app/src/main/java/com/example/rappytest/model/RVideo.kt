package com.example.rappytest.model

import java.io.Serializable

class RVideo(
    var id: String? = "",
    var iso_639_1: String? = "",
    var iso_3166_1: String? = "",
    var key: String? = "",
    var name: String? = "",
    var site: String? = "",
    var size: Int? = 0,
    var type: String? = ""

) : Serializable {
    fun map(data: Map<String, Any>) {

        id = data["id"] as String
        iso_639_1 = data["iso_639_1"] as String
        iso_3166_1 = data["iso_3166_1"] as String
        key = data["key"] as String
        name = data["name"] as String
        site = data["site"] as String
        size = data["size"] as Int
        type = data["type"] as String
    }
}