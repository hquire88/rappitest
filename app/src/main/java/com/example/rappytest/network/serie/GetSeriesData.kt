package com.example.rappytest.network.serie

import com.example.rappytest.model.series.RSeriesServiceData
import io.reactivex.Single
import retrofit2.http.GET

interface GetSeriesData {
    @get: GET("tv/popular?api_key=3ad04e92e664b3d9080556658ba63ce6")
   val popular: Single<RSeriesServiceData>

    @get: GET("tv/top_rated?api_key=3ad04e92e664b3d9080556658ba63ce6")
    val topRated: Single<RSeriesServiceData>

    @get: GET("tv/airing_today?api_key=3ad04e92e664b3d9080556658ba63ce6")
    val airing_today: Single<RSeriesServiceData>

}