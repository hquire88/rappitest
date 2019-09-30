package com.example.rappytest.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rappytest.R
import com.example.rappytest.adapter.MovieAdapter
import com.example.rappytest.adapter.SerieAdapter
import com.example.rappytest.model.HomeMoviesData
import com.example.rappytest.model.HomeSeriesData
import com.example.rappytest.network.movie.MovieAPI
import com.example.rappytest.network.serie.SeriesAPI
import com.example.rappytest.utils.Constant
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    private var myMovieAdapter: MovieAdapter? = null
    private var mySerieAdapter: SerieAdapter? = null
    val context: Context = this

    private var moviesData: HomeMoviesData = HomeMoviesData()
    private var seriesData: HomeSeriesData = HomeSeriesData()

    lateinit var option: Spinner
    lateinit var result: String
    var TypeCode: Long = 0

    private var myCompositeDisposable: CompositeDisposable? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        moviesData = intent.getSerializableExtra("homeMoviesData") as HomeMoviesData

        setSpinner()
        initRecyclerView()
        setMovieAdapter()

    }

    fun getMovies() {
        doAsync {
            MovieAPI().getMovies(context).subscribe({ movies ->
                uiThread {
                    moviesData = movies
                    setMovieAdapter()
                }
            }) { Throwable ->
                print(Throwable.toString())
            }
        }
    }

    private fun getSeries() {
        doAsync {
            SeriesAPI().getSeries(context).subscribe({ series ->
                uiThread {
                    seriesData = series
                    setSerieAdapter()
                }
            }) { Throwable ->
                print(Throwable.toString())
            }
        }
    }

    private fun setSerieAdapter() {
        val popular = seriesData.popular
        mySerieAdapter = SerieAdapter(popular)
        rv_popular.adapter = mySerieAdapter

        val topRated = seriesData.topRated
        mySerieAdapter = SerieAdapter(topRated)
        rv_top_rated.adapter = mySerieAdapter
    }

    private fun setMovieAdapter() {
        val popular = moviesData.popular
        myMovieAdapter = MovieAdapter(popular)
        rv_popular.adapter = myMovieAdapter

        val topRated = moviesData.topRated
        myMovieAdapter = MovieAdapter(topRated)
        rv_top_rated.adapter = myMovieAdapter

        val upcoming = moviesData.upcoming
        myMovieAdapter = MovieAdapter(upcoming)
        rv_upcoming.adapter = myMovieAdapter
    }


    private fun initRecyclerView() {

//Use a layout manager to position your items to look like a standard ListView//
        val popularLayoutManager: RecyclerView.LayoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv_popular.layoutManager = popularLayoutManager

        val topRatedLayoutManager: RecyclerView.LayoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv_top_rated.layoutManager = topRatedLayoutManager

        val upcomingLayoutManager: RecyclerView.LayoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv_upcoming.layoutManager = upcomingLayoutManager
    }

    override fun onDestroy() {
        super.onDestroy()
        myCompositeDisposable?.clear()
    }

    private fun setSpinner() {
        option = findViewById(R.id.spinner_type)
        result = "0"

        val options = Constant.HomeItemType.values().map { it.name }

        option.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, options)

        option.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                result = "Please select an option"
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                result = options.get(position)
                TypeCode = (position.toLong())

                if (Constant.HomeItemType.values()[position] == Constant.HomeItemType.Movie) {
                    tv_upcoming.visibility = (View.VISIBLE)
                    rv_upcoming.visibility = (View.VISIBLE)
                    getMovies()
                } else {
                    tv_upcoming.visibility = (View.GONE)
                    rv_upcoming.visibility = (View.GONE)
                    getSeries()
                }
            }
        }
    }
}