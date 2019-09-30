package com.example.rappytest.utils

class Constant {

    enum class HomeItemType private constructor(val homeItemNumber: Int) {
        Movie(0),
        Serie(1)
    }

    enum class GenresTypeId private constructor(val genresTypeNumber: Int) {
        Action(28),
        Adventure(12),
        Animation(16),
        Comedy(35),
        Crime(80),
        Documentary(99),
        Drama(18),
        Family(10751),
        Fantasy(14),
        History(36),
        Horror(27),
        Music(10402),
        Mystery(9648),
        Romance(10749),
        Science(878),
        TVMovie(10770),
        Thriller(53),
        War(10752),
        Western(37)
    }

    val baseURL = "https://api.themoviedb.org/3/"
    val imageUrl = "https://image.tmdb.org/t/p/w500"
}