package com.example.rappytest.model.series

import java.io.Serializable

class RSeriesServiceData(
    var page: Int? = 0,
    var total_results: Int? = 0,
    var total_pages: Int = 0,
    var results: MutableList<RSeries> = mutableListOf()

) : Serializable {

    fun map(data: Map<String, Any>) {

        page = data["page"] as Int
        total_results = data["total_results"] as Int
        total_pages = data["total_pages"] as Int
        results = data["results"] as MutableList<RSeries>
    }
}