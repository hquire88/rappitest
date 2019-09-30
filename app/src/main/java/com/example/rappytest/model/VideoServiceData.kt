package com.example.rappytest.model

import java.io.Serializable

class VideoServiceData (

    var id: Int? = 0,
    var results: MutableList<RVideo> = mutableListOf()

) : Serializable {

    fun map(data: Map<String, Any>) {

        id = data["id"] as Int
        results = data["results"] as MutableList<RVideo>
    }
}