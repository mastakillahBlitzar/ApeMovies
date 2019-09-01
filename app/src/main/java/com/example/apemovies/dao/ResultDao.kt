package com.example.apemovies.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.example.apemovies.data.Result

@Dao
interface ResultDao {
    @Insert
    fun insert(result: Result)

    @Update
    fun update(vararg result: Result);

    @Delete
    fun delete(vararg result: Result)

    @Query("SELECT * FROM result")
    fun getOrderedMovies(): LiveData<List<Result>>
}