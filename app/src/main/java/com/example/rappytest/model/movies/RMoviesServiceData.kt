package com.example.rappytest.model.movies

import java.io.Serializable

class RMoviesServiceData(
    var page: Int? = 0,
    var total_results: Int? = 0,
    var total_pages: Int = 0,
    var results: MutableList<RMovie> = mutableListOf()

) : Serializable {

    fun map(data: Map<String, Any>) {

        page = data["page"] as Int
        total_results = data["total_results"] as Int
        total_pages = data["total_pages"] as Int
        results = data["results"] as MutableList<RMovie>
    }
}