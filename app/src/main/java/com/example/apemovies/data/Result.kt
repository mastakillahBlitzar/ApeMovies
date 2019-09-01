package com.example.apemovies.data


import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = "result")
data class Result(
    @PrimaryKey(autoGenerate = true)
    var resId: Int,
    @Ignore
    @SerializedName("api_detail_url")
    val apiDetailUrl: String?,
    @Ignore
    @SerializedName("box_office_revenue")
    val boxOfficeRevenue: String?,
    @Ignore
    @SerializedName("budget")
    val budget: String?,
    @Ignore
    @SerializedName("date_added")
    val dateAdded: String?,
    @Ignore
    @SerializedName("date_last_updated")
    val dateLastUpdated: String?,
    @Ignore
    @SerializedName("deck")
    val deck: String?,
    @SerializedName("description")
    var description: String?,
    @Ignore
    @SerializedName("distributor")
    val distributor: Any?,
    @Ignore
    @SerializedName("has_staff_review")
    val hasStaffReview: Any?,
    @SerializedName("id")
    var id: Int?,
    @SerializedName("image")
    var image: Image?,
    @SerializedName("name")
    var name: String?,
    @Ignore
    @SerializedName("producers")
    val producers: Any?,
    @Ignore
    @SerializedName("rating")
    val rating: String?,
    @SerializedName("release_date")
    var releaseDate: String?,
    @SerializedName("runtime")
    var runtime: String?,
    @SerializedName("site_detail_url")
    var siteDetailUrl: String?,
    @Ignore
    @SerializedName("studios")
    val studios: List<Studio?>?,
    @Ignore
    @SerializedName("total_revenue")
    val totalRevenue: String?,
    @SerializedName("writers")
    @android.support.annotation.Nullable
    var writers: List<Writer?>?
) {
    constructor(): this(0, "","","","","","","","","",null,null,"","","","","","",null, "", Collections.emptyList())
}