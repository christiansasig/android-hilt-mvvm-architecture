package com.christiansasig.androidhiltmvvmarchitecture.data.network.model

import com.google.gson.annotations.SerializedName

data class MovieModel(
    @SerializedName("id") val id: Long,
    @SerializedName("title") val title: String?,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("vote_average") val voteAverage: Float?,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("overview") val overview: String?,
    @SerializedName("release_date") val releaseDate: String?,
    @SerializedName("original_title") val originalTitle: String?,
    @SerializedName("popularity") val popularity: Float?
)