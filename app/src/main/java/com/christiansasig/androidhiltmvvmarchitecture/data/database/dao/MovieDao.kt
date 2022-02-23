package com.christiansasig.androidhiltmvvmarchitecture.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.christiansasig.androidhiltmvvmarchitecture.data.database.entities.MovieEntity

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie_table ORDER BY title DESC")
    suspend fun getAll():List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(quotes:List<MovieEntity>)

    @Query("DELETE FROM movie_table")
    suspend fun deleteAll()
}