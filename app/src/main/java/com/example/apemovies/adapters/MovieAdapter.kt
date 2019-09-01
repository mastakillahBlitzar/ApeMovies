package com.example.apemovies.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.telecom.Call
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.apemovies.R
import com.example.apemovies.data.MovieList
import com.example.apemovies.interfaces.MoviesClickListener
import kotlinx.android.synthetic.main.movie_list_view.view.*
import android.widget.AdapterView.OnItemClickListener
import com.example.apemovies.data.Result
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import kotlinx.android.synthetic.main.activity_movie_detail.view.*
import java.lang.Exception


class MovieAdapter(private val movieList: List<Result?>, val context: Context, val listener: MoviesClickListener): RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, p1: Int) {
        bind(holder, movieList.get(p1))
    }

    override fun getItemCount(): Int {
        return this.movieList!!.size!!;
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.movie_list_view, parent, false));
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val movieTitle = view.movie_title;
        val movieYear = view.movie_year;
        val cardWrap = view.card_wrap;
        val thumb = view.thumb
        val icon = view.icon
    }

    fun bind(holder: ViewHolder, item: Result?) {
        holder?.movieTitle?.text = item?.name
        holder?.movieYear?.text = item?.deck + " - " + item?.releaseDate
        holder?.cardWrap.setOnClickListener(View.OnClickListener {
            listener.onMovieCardClick(item);
        });
        holder?.icon.text = item?.name!!.substring(0, 1)!!.toUpperCase()
        Picasso.get()
            .load(item?.image!!.iconUrl)
            .resize(50, 50)
            .centerCrop()
            .transform(CropCircleTransformation())
            .into(holder.thumb, object: Callback{
                override fun onSuccess() {
                    holder?.thumb.visibility = View.VISIBLE
                }

                override fun onError(e: Exception?) {
                }
            })
    }
}