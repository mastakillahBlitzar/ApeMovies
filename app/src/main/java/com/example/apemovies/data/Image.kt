package com.example.apemovies.data


import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "image")
data class Image(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @SerializedName("icon_url")
    var iconUrl: String?,
    @Ignore
    @SerializedName("image_tags")
    val imageTags: String?,
    @SerializedName("medium_url")
    var mediumUrl: String?,
    @Ignore
    @SerializedName("original_url")
    val originalUrl: String?,
    @Ignore
    @SerializedName("screen_large_url")
    val screenLargeUrl: String?,
    @Ignore
    @SerializedName("screen_url")
    val screenUrl: String?,
    @Ignore
    @SerializedName("small_url")
    val smallUrl: String?,
    @Ignore
    @SerializedName("super_url")
    val superUrl: String?,
    @Ignore
    @SerializedName("thumb_url")
    val thumbUrl: String?,
    @Ignore
    @SerializedName("tiny_url")
    val tinyUrl: String?
) {
    constructor(): this(0, "", "", "","", "","", "", "", "", "")
}