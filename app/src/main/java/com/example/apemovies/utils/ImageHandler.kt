package com.example.apemovies.utils

import android.graphics.Bitmap
import android.os.AsyncTask
import android.widget.ImageView
import android.graphics.BitmapFactory
import android.util.Log
import android.view.View
import android.widget.ProgressBar


class ImageHandler(imageView: ImageView, loader: ProgressBar) : AsyncTask<String, Void, Bitmap>() {

    val imgView = imageView
    val ldr = loader

    override fun doInBackground(vararg urls: String?): Bitmap? {
        val urldisplay = urls[0]
        var myImage: Bitmap? = null
        try {
            val `in` = java.net.URL(urldisplay).openStream()
            myImage = BitmapFactory.decodeStream(`in`)
        } catch (e: Exception) {
            Log.e("Error", e.message)
            e.printStackTrace()
        }

        return myImage
    }

    override fun onPostExecute(result: Bitmap?) {
        ldr.visibility = View.GONE
        imgView.setImageBitmap(result)
    }
}