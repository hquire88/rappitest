package com.example.rappytest.network.movie

import android.content.Context
import com.example.rappytest.model.movies.RMovie
import com.example.rappytest.model.movies.RMoviesServiceData
import com.example.rappytest.model.HomeMoviesData
import com.example.rappytest.network.RetrofitClient
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Function3
import io.reactivex.schedulers.Schedulers

class MovieAPI {

    private var myCompositeDisposable: CompositeDisposable = CompositeDisposable()

    fun getMovies(context: Context): Single<HomeMoviesData> {
        val topRatedMovies = getTopRatedMovies(context)
        val popularMovies = getPopularMovies(context)
        val upcomingMovies = getUpcomingMovies(context)
        return Single.zip(
            topRatedMovies,
            popularMovies,
            upcomingMovies,
            Function3<RMoviesServiceData, RMoviesServiceData, RMoviesServiceData, HomeMoviesData>
            { topRated, popular, upcoming ->
                createHomeMoviesModel(topRated.results, popular.results, upcoming.results)
            })
    }

    fun createHomeMoviesModel(
        topRated: MutableList<RMovie>,
        popular: MutableList<RMovie>,
        upcoming: MutableList<RMovie>
    ): HomeMoviesData {
        val homeData = HomeMoviesData()
        homeData.topRated = topRated
        homeData.popular = popular
        homeData.upcoming = upcoming
        return homeData
    }

    fun getTopRatedMovies(context: Context): Single<RMoviesServiceData> {
        val retrofit = RetrofitClient.getClient(context)
        val requestInterface = retrofit.create(GetMoviesData::class.java)
        val topRatedMoviesRequest = requestInterface.topRated
        myCompositeDisposable.add(
            topRatedMoviesRequest
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ movieData ->
                    topRatedMoviesRequest.doOnSuccess { movieData.results }
                }){ Throwable ->
                    print(Throwable.toString())
                }
        )
        return topRatedMoviesRequest
    }

    fun getPopularMovies(context: Context): Single<RMoviesServiceData> {
        val retrofit = RetrofitClient.getClient(context)
        val requestInterface = retrofit.create(GetMoviesData::class.java)
        val popularMoviesRequest = requestInterface.popular
        myCompositeDisposable.add(
            popularMoviesRequest
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ movieData ->
                    popularMoviesRequest.doOnSuccess { movieData.results }
                }){ Throwable ->
                    print(Throwable.toString())
                }
        )
        return popularMoviesRequest
    }

    fun getUpcomingMovies(context: Context): Single<RMoviesServiceData> {
        val retrofit = RetrofitClient.getClient(context)
        val requestInterface = retrofit.create(GetMoviesData::class.java)
        val upcomingMoviesRequest = requestInterface.upcoming
        myCompositeDisposable.add(
            upcomingMoviesRequest
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ movieData ->
                    upcomingMoviesRequest.doOnSuccess { movieData.results }
                }){ Throwable ->
                    print(Throwable.toString())
                }
        )
        return upcomingMoviesRequest
    }
}







