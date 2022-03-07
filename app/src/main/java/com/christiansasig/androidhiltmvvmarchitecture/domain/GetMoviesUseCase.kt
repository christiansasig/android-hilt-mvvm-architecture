package com.christiansasig.androidhiltmvvmarchitecture.domain

import com.christiansasig.androidhiltmvvmarchitecture.data.MovieRepository
import com.christiansasig.androidhiltmvvmarchitecture.data.database.entities.toDatabase
import com.christiansasig.androidhiltmvvmarchitecture.domain.model.Movie
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val repository: MovieRepository) {
    suspend operator fun invoke():List<Movie>{
        val dogs = repository.getAllMoviesFromDatabase()
        return dogs.ifEmpty {
            val moviesApi = repository.getAllMoviesFromApi(1, "popularity.des")
            repository.clearMovies()
            repository.insertMovies(moviesApi.map { it.toDatabase() })
            repository.getAllMoviesFromDatabase()
        }
    }
}