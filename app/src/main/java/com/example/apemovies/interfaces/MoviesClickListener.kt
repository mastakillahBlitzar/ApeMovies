package com.example.apemovies.interfaces

import com.example.apemovies.data.Result

interface MoviesClickListener {
    fun onMovieCardClick(item: Result?);
}