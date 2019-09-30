package com.example.rappytest.splash

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rappytest.R
import com.example.rappytest.ui.MainActivity
import com.example.rappytest.model.HomeMoviesData
import com.example.rappytest.network.movie.MovieAPI
import java.io.Serializable

class SplashMainActivity : AppCompatActivity() {

    private var homeMoviesData: HomeMoviesData = HomeMoviesData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_main)


        val background = object : Thread() {
            override fun run() {
                try {
                    val intent = Intent(baseContext, MainActivity::class.java)
                    getMovies()
                    intent.putExtra("homeMoviesData", homeMoviesData as Serializable)
                    Thread.sleep(1500)
                    startActivity(intent)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        background.start()
    }

    fun getMovies() {
        MovieAPI().getMovies(this).subscribe({ movies ->
            homeMoviesData = movies
        }) { Throwable ->
            print(Throwable.toString())
        }
    }

    fun hasNetwork(context: Context): Boolean? {
        var isConnected: Boolean? = false // Initial Value
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        if (activeNetwork != null && activeNetwork.isConnected)
            isConnected = true
        return isConnected
    }
}
