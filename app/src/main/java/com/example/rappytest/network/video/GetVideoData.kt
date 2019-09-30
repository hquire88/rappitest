package com.example.rappytest.network.movie

import com.example.rappytest.model.VideoServiceData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Url



interface GetVideoData {

    @GET
    fun getVideosData(@Url url: String): Single<VideoServiceData>
}