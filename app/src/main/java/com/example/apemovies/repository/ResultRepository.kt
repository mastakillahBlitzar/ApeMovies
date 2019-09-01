package com.example.apemovies.repository

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.os.AsyncTask
import android.util.Log
import com.example.apemovies.dao.ResultDao
import com.example.apemovies.data.Result
import com.example.apemovies.database.ResultDatabase

class ResultRepository(application: Application) {
    private val resultDao: ResultDao? = ResultDatabase.getInstance(application)?.resultDao()

    fun insert(result: Result?) {
        Log.d("saving", result.toString())
        if(resultDao != null) InsertAsyncTask(resultDao).execute(result)
    }

    fun getMovies(): LiveData<List<Result>> {
        return resultDao?.getOrderedMovies() ?: MutableLiveData<List<Result>>()
    }

    private class InsertAsyncTask(private val resultDao: ResultDao) :
        AsyncTask<Result, Void, Void>() {
        override fun doInBackground(vararg result: Result?): Void? {
            for (contact in result) {
                if (contact != null) resultDao.insert(contact)
            }
            return null
        }
    }
}