package com.example.rappytest.ui

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.rappytest.R
import com.example.rappytest.model.VideoServiceData
import com.example.rappytest.model.movies.RMovie
import com.example.rappytest.model.series.RSeries
import com.example.rappytest.network.movie.VideoAPI
import com.example.rappytest.utils.Constant
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerSupportFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


class DetailActivity : AppCompatActivity(), YouTubePlayer.OnInitializedListener {

    private val RECOVERY_DIALOG_REQUEST = 1
    val context: Context = this

    private var movie: RMovie? = null
    private var serie: RSeries? = null
    private var typeVideo: String = ""
    private var videoData: VideoServiceData? = null
    private var videoUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)


        typeVideo = intent.getStringExtra("typeVideo")
        if (typeVideo == "movie") {
            movie = intent.getSerializableExtra("movie") as RMovie

        } else {
            serie = intent.getSerializableExtra("serie") as RSeries
        }

        setMovieDetail()
        getVideoData()
    }

    override fun onInitializationSuccess(
        provider: YouTubePlayer.Provider,
        youTubePlayer: YouTubePlayer,
        wasRestored: Boolean
    ) {
        if (!wasRestored) {
            youTubePlayer.cueVideo(videoUrl)
        }
    }

    private fun getVideoData() {
        if (typeVideo == "movie") {
            doAsync {
                movie?.let {
                    VideoAPI().getVideoData(typeVideo, it.id, context).subscribe({ video ->
                        uiThread {
                            videoData = video
                            setUpVideo()
                        }
                    }) { Throwable ->
                        print(Throwable.toString())
                    }
                }
            }
        } else {
            doAsync {
                serie?.let {
                    VideoAPI().getVideoData(typeVideo, it.id, context).subscribe({ video ->
                        uiThread {
                            videoData = video
                            setUpVideo()
                        }
                    }) { Throwable ->
                        print(Throwable.toString())
                    }
                }
            }
        }

    }

    private fun setUpVideo() {
        this.videoData?.let {
            videoUrl = it.results[0].key
            val youTubePlayerFragment =
                supportFragmentManager.findFragmentById(R.id.yv_Detail2) as YouTubePlayerSupportFragment?
            youTubePlayerFragment?.initialize("AIzaSyCZZBLhwpSLdKCm4U25C97zPJy1ZHMdl5c", this)
        }
    }

    override fun onInitializationFailure(
        provider: YouTubePlayer.Provider,
        youTubeInitializationResult: YouTubeInitializationResult
    ) {
        if (youTubeInitializationResult.isUserRecoverableError) {
            youTubeInitializationResult.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show()
        } else {
            val errorMessage = String.format(
                "There was an error initializing the YouTubePlayer (%1\$s)",
                youTubeInitializationResult.toString()
            )
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
        }
    }

    private fun setMovieDetail() {
        if (typeVideo == "movie") {
            this.movie?.let {
                tv_movie_title.text = it.title
                tv_release_date.text = it.release_date
                tv_vote_average.text = it.vote_average.toString()
                tv_original_language.text = it.original_language
                tv_overview.text = it.overview

                //Set Images
                if (it.backdrop_path != "") {
                    val imagesUrl = Constant().imageUrl + it.backdrop_path
                    Picasso.get().load(imagesUrl).into(iv_detail_movie)
                }
            }
        } else {
            this.serie?.let {
                tv_movie_title.text = it.name
                tv_release_date.text = it.release_date
                tv_vote_average.text = it.vote_average.toString()
                tv_original_language.text = it.original_language
                tv_overview.text = it.overview

                //Set Images
                if (it.backdrop_path != "") {

                    val imagesUrl = Constant().imageUrl + it.backdrop_path
                    Picasso.get().load(imagesUrl).into(iv_detail_movie)
                }
            }
        }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
}
