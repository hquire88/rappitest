package com.example.rappytest.model

import com.example.rappytest.model.series.RSeries
import java.io.Serializable

class HomeSeriesData : Serializable {

    var topRated : MutableList<RSeries> = mutableListOf()
    var popular : MutableList<RSeries> = mutableListOf()
    var airing_today : MutableList<RSeries> = mutableListOf()
}