package com.example.apemovies.application

import android.app.Application
import android.content.Context
import android.support.v4.content.ContextCompat
import com.example.apemovies.R

class MoviesApplication : Application() {

    companion object {
        var ctx: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
    }
}