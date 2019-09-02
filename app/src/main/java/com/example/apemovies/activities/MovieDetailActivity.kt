package com.example.apemovies.activities

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.transition.Slide
import android.view.Gravity
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import com.example.apemovies.R
import com.example.apemovies.data.Result
import com.example.apemovies.data.Writer
import com.example.apemovies.utils.ImageHandler
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_movie_detail.*


class MovieDetailActivity : AppCompatActivity() {

    companion object {
        var detail: Result? = null;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setAnimation()
        setContentView(R.layout.activity_movie_detail)
        val gson = Gson()
        detail = gson.fromJson<Result>(intent.getStringExtra("detail"), Result::class.java)
    }

    override fun onStart() {
        super.onStart()
        var writers = "";
        detail?.writers?.let {
            for (e: Writer? in it) {
                if (writers.equals(""))
                    writers = writers + e?.name
                else writers = writers + " / " + e?.name;
            }
        }
        detail_title.text = detail?.name;
        release_date.text = detail!!.releaseDate
        runtime.text = detail!!.runtime + " minutos"
        if (writers.equals("")) writers = "desconocido"
        movie_director.text = writers;
        if (detail!!.description.equals("") || detail!!.description == null) {
            summary_tv.visibility = View.GONE
            movie_resume.visibility = View.GONE
        } else {
            val summary = detail?.description?.replace(Regex( "<\\/?[\\w\\s]*>|<.+[\\W]>"), "")
            movie_resume.text = summary
        }

        val url = detail?.image?.mediumUrl
        loader.visibility = View.VISIBLE
        ImageHandler(movie_image, loader).execute(url)

    }

    fun setAnimation() {
        if (Build.VERSION.SDK_INT > 20) {
            val slide = Slide()
            slide.slideEdge = Gravity.LEFT
            slide.duration = 400
            slide.interpolator = AccelerateDecelerateInterpolator()
            window.exitTransition = slide
            window.enterTransition = slide
        }
    }

}
