package com.example.apemovies.database

import android.arch.persistence.room.*
import android.content.Context
import com.example.apemovies.dao.ResultDao
import com.example.apemovies.data.Image
import com.example.apemovies.data.Result
import com.example.apemovies.data.Writer
import com.example.apemovies.utils.Converters

@Database(entities = [Result::class, Image::class, Writer::class], version = 2,exportSchema = true)
@TypeConverters(Converters::class)
abstract class ResultDatabase : RoomDatabase() {

    abstract fun resultDao(): ResultDao

    companion object {
        private const val DATABASE_NAME = "movies_database"

        @Volatile
        private var INSTANCE: ResultDatabase? = null

        fun getInstance(context: Context): ResultDatabase? {
            INSTANCE ?: synchronized(this) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    ResultDatabase::class.java,
                    DATABASE_NAME
                ).build()
            }
            return INSTANCE
        }
    }
}