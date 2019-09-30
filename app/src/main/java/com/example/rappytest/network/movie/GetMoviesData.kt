package com.example.rappytest.network.movie

import com.example.rappytest.model.movies.RMoviesServiceData
import io.reactivex.Single
import retrofit2.http.GET

interface GetMoviesData {
    @get: GET("movie/popular?api_key=3ad04e92e664b3d9080556658ba63ce6")
   val popular: Single<RMoviesServiceData>

    @get: GET("movie/top_rated?api_key=3ad04e92e664b3d9080556658ba63ce6")
    val topRated: Single<RMoviesServiceData>

    @get: GET("movie/upcoming?api_key=3ad04e92e664b3d9080556658ba63ce6")
    val upcoming: Single<RMoviesServiceData>

}