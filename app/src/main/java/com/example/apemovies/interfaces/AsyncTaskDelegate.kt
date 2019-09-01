package com.example.apemovies.interfaces

interface AsyncTaskDelegate {
    fun onTaskEnd(hasConnection: Boolean);
}