package com.example.apemovies.activities

import android.app.ActivityOptions
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.apemovies.R
import com.example.apemovies.adapters.MovieAdapter
import com.example.apemovies.data.MovieList
import com.example.apemovies.data.Result
import com.example.apemovies.interfaces.AsyncTaskDelegate
import com.example.apemovies.interfaces.EndlessRecyclerViewScrollListener
import com.example.apemovies.interfaces.MoviesClickListener
import com.example.apemovies.interfaces.MoviesServiceImpl
import com.example.apemovies.utils.InternetUtils
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.collections.ArrayList
import android.os.Build
import com.example.apemovies.database.ResultViewModel
import java.util.*

class MainActivity : AppCompatActivity(), AsyncTaskDelegate, MoviesClickListener {

    var limit = 15;
    var offset = 0;

    var movies: ArrayList<Result?> = arrayListOf()
    val movieAdapter = MovieAdapter(movies, this, this)
    var cachedResults: List<Result> = Collections.emptyList()

    private lateinit var resultViewModel: ResultViewModel

    override fun onMovieCardClick(item: Result?) {
        val intent = Intent(this@MainActivity, MovieDetailActivity::class.java)
        val gson = Gson();
        val s = gson.toJson(item);
        intent.putExtra("detail", s);
        if (Build.VERSION.SDK_INT > 20) {
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        } else {
            startActivity(intent);
        }
    }

    override fun onTaskEnd(isConnected: Boolean) {

        if (isConnected) {
            Log.d("calling", "calling");
            val moviesServiceImpl: MoviesServiceImpl = MoviesServiceImpl();

            moviesServiceImpl.getMovies("fabd330667dd05f87699a24f03decb5fba6f50f0", "json", limit, offset, "")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, { error ->
                    error.printStackTrace();
                    movies_spinner.visibility = View.GONE
                    Toast.makeText(
                        this, "Error consultando datos de peliculas",
                        Toast.LENGTH_SHORT
                    ).show()
                });
        } else {
            Toast.makeText(
                this, "No Internet connection",
                Toast.LENGTH_SHORT
            ).show()
            addObserver()
            movies_spinner.visibility = View.GONE
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimary)
        reload.setOnClickListener() {
            callService();
        };

        resultViewModel = run {
            ViewModelProviders.of(this).get(ResultViewModel::class.java)
        }

        movie_list.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager;
        movie_list.itemAnimator = DefaultItemAnimator() as RecyclerView.ItemAnimator

        movie_list.addOnScrollListener(object : EndlessRecyclerViewScrollListener() {
            override fun onLoadMore() {
                offset += limit;
                callService()
            }
        })
        movie_list.adapter = movieAdapter
        InternetUtils.taskDelegate = this;
        callService();
    }

    private fun addObserver() {
        val observer = Observer<List<Result>> { results ->
            if (results != null) {
                if(!cachedResults.equals(results)) {
                    cachedResults = results;
                    if (results.size == 0) {
                        loading_panel.visibility = View.VISIBLE
                    } else {
                        movies.addAll(results)
                        movieAdapter.notifyItemInserted(movies.size - 1)
                    }
                }
            } else {
                loading_panel.visibility = View.VISIBLE
            }
        }
        resultViewModel.results.observe(this, observer)
    }

    override fun onStart() {
        super.onStart()
    }

    private fun callService() {
        loading_panel.visibility = View.GONE
        movies_spinner.visibility = View.VISIBLE
        val internetTask = InternetUtils();
        internetTask.execute();
    }

    private fun handleResponse(movieList: MovieList) {
        println("result" + movieList.results)
        loading_panel.visibility = View.GONE

        movieList.results?.forEach {
            resultViewModel.saveMovie(it)
        }

        movies.addAll(movieList.results!!)
        movies_spinner.visibility = View.GONE
        movieAdapter.notifyItemInserted(movies.size - 1)
    }
}
