package com.example.rappytest.network.serie

import android.content.Context
import com.example.rappytest.model.HomeSeriesData
import com.example.rappytest.model.series.RSeries
import com.example.rappytest.model.series.RSeriesServiceData
import com.example.rappytest.network.RetrofitClient
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Function3
import io.reactivex.schedulers.Schedulers

class SeriesAPI {

    private var myCompositeDisposable: CompositeDisposable = CompositeDisposable()

    fun getSeries(context: Context): Single<HomeSeriesData> {
        val topRatedseries = getTopRatedSeries(context)
        val popularSeries = getPopularSeries(context)
        val upcomingSeries = getAiringTodaySeries(context)
        return Single.zip(
            topRatedseries,
            popularSeries,
            upcomingSeries,
            Function3<RSeriesServiceData, RSeriesServiceData, RSeriesServiceData, HomeSeriesData>
            { topRated, popular, airing_today ->
                createHomeSeriesModel(topRated.results, popular.results, airing_today.results)
            })
    }

    fun createHomeSeriesModel(
        topRated: MutableList<RSeries>,
        popular: MutableList<RSeries>,
        airing_today: MutableList<RSeries>
    ): HomeSeriesData {
        val homeData = HomeSeriesData()
        homeData.topRated = topRated
        homeData.popular = popular
        homeData.airing_today = airing_today
        return homeData
    }

    fun getTopRatedSeries(context: Context): Single<RSeriesServiceData> {
        val retrofit = RetrofitClient.getClient(context)
        val requestInterface = retrofit.create(GetSeriesData::class.java)
        val topRatedSeriesRequest = requestInterface.topRated
        myCompositeDisposable.add(
            topRatedSeriesRequest
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ movieData ->
                    topRatedSeriesRequest.doOnSuccess { movieData.results }
                }){ Throwable ->
                    print(Throwable.toString())
                }
        )
        return topRatedSeriesRequest
    }

    fun getPopularSeries(context: Context): Single<RSeriesServiceData> {
        val retrofit = RetrofitClient.getClient(context)
        val requestInterface = retrofit.create(GetSeriesData::class.java)
        val popularSeriesRequest = requestInterface.popular
        myCompositeDisposable.add(
            popularSeriesRequest
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ movieData ->
                    popularSeriesRequest.doOnSuccess { movieData.results }
                }){ Throwable ->
                    print(Throwable.toString())
                }
        )
        return popularSeriesRequest
    }

    fun getAiringTodaySeries(context: Context): Single<RSeriesServiceData> {
        val retrofit = RetrofitClient.getClient(context)
        val requestInterface = retrofit.create(GetSeriesData::class.java)
        val upcomingSeriesRequest = requestInterface.airing_today
        myCompositeDisposable.add(
            upcomingSeriesRequest
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ movieData ->
                    upcomingSeriesRequest.doOnSuccess { movieData.results }
                }){ Throwable ->
                    print(Throwable.toString())
                }
        )
        return upcomingSeriesRequest
    }
}







