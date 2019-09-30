package com.example.rappytest.model.movies

import java.io.Serializable

class RMovie(
    var id: Int = 0,
    var popularity: Double? = 0.0,
    var vote_average: Double? = 0.0,
    var voteCount: Int? = 0,
    var title: String? = "",
    var release_date: String? = "",
    var poster_path: String? = "",
    var backdrop_path: String? = "",
    var original_language: String? = "",
    var original_title: String? = "",
    var genre_ids: Array<Int> = arrayOf(),
    var overview: String? = "",
    var adult: Boolean? = false
) : Serializable {

    fun map(data: Map<String, Any>) {

        id = data["id"] as Int
        popularity = data["popularity"] as Double
        vote_average = data["vote_average"] as Double
        voteCount = data["vote_Count"] as Int
        title = data["title"] as String
        release_date = data["release_date"] as String
        poster_path = data["poster_path"] as String
        backdrop_path = data["backdrop_path"] as String
        original_language = data["original_language"] as String
        original_title = data["original_title"] as String
        genre_ids = data["genre_ids"] as Array<Int>
        overview = data["overview"] as String
        adult = data["adult"] as Boolean
    }
}