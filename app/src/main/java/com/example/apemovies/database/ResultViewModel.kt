package com.example.apemovies.database

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.example.apemovies.data.Result
import com.example.apemovies.repository.ResultRepository

class ResultViewModel(application: Application): AndroidViewModel(application) {
    private val repository = ResultRepository(application)
    val results = repository.getMovies()

    fun saveMovie(result: Result?) {
        repository.insert(result)
    }
}