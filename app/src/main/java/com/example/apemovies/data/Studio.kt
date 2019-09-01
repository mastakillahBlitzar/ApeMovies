package com.example.apemovies.data


import com.google.gson.annotations.SerializedName

data class Studio(
    @SerializedName("api_detail_url")
    val apiDetailUrl: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("site_detail_url")
    val siteDetailUrl: String?
)