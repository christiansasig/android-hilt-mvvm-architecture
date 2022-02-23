package com.christiansasig.androidhiltmvvmarchitecture.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.christiansasig.androidhiltmvvmarchitecture.data.database.dao.MovieDao
import com.christiansasig.androidhiltmvvmarchitecture.data.database.entities.MovieEntity

@Database(entities = [MovieEntity::class], version = 1)
abstract class MovieDatabase: RoomDatabase() {

    abstract fun getMovieDao(): MovieDao
}