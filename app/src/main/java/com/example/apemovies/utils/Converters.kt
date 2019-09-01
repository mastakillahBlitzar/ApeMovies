package com.example.apemovies.utils

import android.arch.persistence.room.TypeConverter
import com.example.apemovies.data.Image
import com.example.apemovies.data.Writer
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*


class Converters {

    @TypeConverter
    fun fromString(value: String): List<Writer> {
        val listType = object : TypeToken<List<Writer>>() {
        }.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: List<Writer>?): String {
        val gson = Gson()
        if(list == null) return gson.toJson(Collections.EMPTY_LIST)
        else return gson.toJson(list)
    }


    @TypeConverter
    fun fromImageString(value: String): Image {
        val objType = object : TypeToken<Image>() {
        }.type

        return Gson().fromJson(value, objType)
    }

    @TypeConverter
    fun fromImage(image: Image): String {
        val gson = Gson()
        return gson.toJson(image)
    }
}