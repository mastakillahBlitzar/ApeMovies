package com.example.apemovies.interfaces

import com.example.apemovies.data.MovieList
import io.reactivex.Observable

class MoviesServiceImpl :MoviesService {

    override fun getMovies(apiKey: String, format: String, limit: Int, offset: Int, sort: String): Observable<MovieList> {
        val moviesService = MoviesService.create();
        return moviesService.getMovies(apiKey, format, limit, offset, sort);
    }

}