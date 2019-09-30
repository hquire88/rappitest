package com.example.rappytest.model

import com.example.rappytest.model.movies.RMovie
import java.io.Serializable

class HomeMoviesData: Serializable {

    var topRated : MutableList<RMovie> = mutableListOf()
    var popular : MutableList<RMovie> = mutableListOf()
    var upcoming : MutableList<RMovie> = mutableListOf()
}