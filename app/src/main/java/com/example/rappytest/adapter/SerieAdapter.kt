package com.example.rappytest.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rappytest.R
import com.example.rappytest.ui.DetailActivity
import com.example.rappytest.model.series.RSeries
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie.view.*
import java.io.Serializable

class SerieAdapter(val movieList: MutableList<RSeries>) : RecyclerView.Adapter<SerieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.activity_movie, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movieList[position]
        val imageUrl = "https://image.tmdb.org/t/p/w500"
        val imagesUrlSetted = imageUrl + movie.poster_path

        holder.movie_view.tv_detail_title.text = movie.name
        Picasso.get().load(imagesUrlSetted).into(holder.movie_view.imageView_movie)

        holder.movie_view.setOnClickListener {
            val activity = it.context as Activity
            val intent = Intent(it.context, DetailActivity::class.java)
            intent.putExtra("serie", movie as Serializable)
            intent.putExtra("typeVideo", "tv")
            it.context.startActivity(intent)
            activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
    }

    class MovieViewHolder(val movie_view: View) : RecyclerView.ViewHolder(movie_view)
}
