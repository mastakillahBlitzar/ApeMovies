package com.example.apemovies.interfaces

import com.example.apemovies.data.MovieList
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface MoviesService {


    @GET("movies/")
    fun getMovies(@Query("api_key") apiKey: String,
                  @Query("format") format: String,
                  @Query("limit") limit: Int,
                  @Query("offset") offset: Int,
                  @Query("sort") sort: String): Observable<MovieList>

    companion object Factory {
        fun create(): MoviesService {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://comicvine.gamespot.com/api/")
                .build()

            return retrofit.create(MoviesService::class.java);
        }
    }
}