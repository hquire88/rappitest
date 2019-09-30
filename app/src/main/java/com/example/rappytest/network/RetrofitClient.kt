package com.example.rappytest.network

import android.content.Context
import com.example.rappytest.utils.Constant
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    fun getClient(context: Context): Retrofit  {
        val cacheSize = (5 * 1024 * 1024).toLong()
        val mCache = Cache(context.cacheDir, cacheSize)

        val okHttpClient = OkHttpClient.Builder()
            .cache(mCache)
            .build()

        val ourInstance = Retrofit.Builder()
            .baseUrl(Constant().baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()

        return ourInstance
    }

}