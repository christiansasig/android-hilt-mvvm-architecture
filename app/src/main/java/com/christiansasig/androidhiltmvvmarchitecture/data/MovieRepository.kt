package com.christiansasig.androidhiltmvvmarchitecture.data

import com.christiansasig.androidhiltmvvmarchitecture.data.database.dao.MovieDao
import com.christiansasig.androidhiltmvvmarchitecture.data.database.entities.MovieEntity
import com.christiansasig.androidhiltmvvmarchitecture.data.network.model.MovieModel
import com.christiansasig.androidhiltmvvmarchitecture.domain.model.Movie
import com.christiansasig.androidhiltmvvmarchitecture.domain.model.toDomain
import com.christiansasig.androidhiltmvvmarchitecture.data.network.MovieService

import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val api: MovieService,
    private val movieDao: MovieDao
) {

    suspend fun getAllMoviesFromApi(page: Int, sortBy: String): List<Movie> {
        val response: List<MovieModel> = api.getMovies(page, sortBy)
        return response.map { it.toDomain() }
    }

    suspend fun getAllMoviesFromDatabase():List<Movie>{
        val response: List<MovieEntity> = movieDao.getAll()
        return response.map { it.toDomain() }
    }

    suspend fun getMovieFromDatabaseById(id: Int):Movie{
        val response: MovieEntity = movieDao.getById(id)
        return response.toDomain()
    }

    suspend fun insertMovies(entities:List<MovieEntity>){
        movieDao.insertAll(entities)
    }

    suspend fun clearMovies(){
        movieDao.deleteAll()
    }
}