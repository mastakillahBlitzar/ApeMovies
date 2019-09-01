package com.example.apemovies.data


import com.google.gson.annotations.SerializedName

data class MovieList(
    @SerializedName("error")
    val error: String?,
    @SerializedName("limit")
    val limit: Int?,
    @SerializedName("number_of_page_results")
    val numberOfPageResults: Int?,
    @SerializedName("number_of_total_results")
    val numberOfTotalResults: Int?,
    @SerializedName("offset")
    val offset: Int?,
    @SerializedName("results")
    val results: List<Result?>?,
    @SerializedName("status_code")
    val statusCode: Int?
)