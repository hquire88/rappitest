package com.example.rappytest.network.movie

import android.content.Context
import com.example.rappytest.model.*
import com.example.rappytest.network.RetrofitClient
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class VideoAPI {

    private var myCompositeDisposable: CompositeDisposable = CompositeDisposable()

    fun getVideoData(type: String ,movieID: Int, context: Context): Single<VideoServiceData> {
        val retrofit = RetrofitClient.getClient(context)
        val requestInterface = retrofit.create(GetVideoData::class.java)
        val videoRequest = requestInterface.getVideosData("$type/$movieID/videos?api_key=3ad04e92e664b3d9080556658ba63ce6")
        myCompositeDisposable.add(
            videoRequest
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ videoData ->
                    videoRequest.doOnSuccess { videoData}
                }){ Throwable ->
                    print(Throwable.toString())
                }
        )
        return videoRequest
    }
}







