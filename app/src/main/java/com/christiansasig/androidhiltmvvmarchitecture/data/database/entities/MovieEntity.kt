package com.christiansasig.androidhiltmvvmarchitecture.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.christiansasig.androidhiltmvvmarchitecture.domain.model.Movie

@Entity(tableName = "movie_table")
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
@ColumnInfo(name = "id") val id: Int = 0,
@ColumnInfo(name = "title") val title: String,
@ColumnInfo(name = "poster_path") val posterPath: String?,
@ColumnInfo(name = "vote_average") val voteAverage: Float?,
@ColumnInfo(name = "backdrop_path") val backdropPath: String?,
@ColumnInfo(name = "overview") val overview: String?,
@ColumnInfo(name = "release_date") val releaseDate: String?,
@ColumnInfo(name = "original_title") val originalTitle: String?,
@ColumnInfo(name = "popularity") val popularity: Float?
)


fun Movie.toDatabase() = MovieEntity(title = title, posterPath =  posterPath, voteAverage = voteAverage, backdropPath = backdropPath, overview = overview, releaseDate = releaseDate, originalTitle = originalTitle, popularity = popularity)