package com.christiansasig.androidhiltmvvmarchitecture.domain.model

import androidx.room.ColumnInfo
import com.christiansasig.androidhiltmvvmarchitecture.data.database.entities.MovieEntity
import com.christiansasig.androidhiltmvvmarchitecture.data.model.MovieModel

data class Movie (
val title: String?,
val posterPath: String?,
val voteAverage: Float?,
val backdropPath: String?,
val overview: String?,
val releaseDate: String?,
val originalTitle: String?,
val popularity: Float?
)

//extension function/Funcion de extension
fun MovieModel.toDomain() = Movie(title, posterPath, voteAverage, backdropPath, overview, releaseDate, originalTitle, popularity)
fun MovieEntity.toDomain() = Movie(title, posterPath, voteAverage,backdropPath, overview, releaseDate, originalTitle, popularity)