package com.example.apemovies.data


import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "writer")
data class Writer(
    @PrimaryKey(autoGenerate = true)
    var writerId: Int,
    @Ignore
    @SerializedName("api_detail_url")
    val apiDetailUrl: String?,
    @Ignore
    @SerializedName("id")
    var id: Int?,
    @SerializedName("name")
    var name: String?,
    @Ignore
    @SerializedName("site_detail_url")
    val siteDetailUrl: String?
) {
    constructor(): this(0, "", 0, "", "")
}