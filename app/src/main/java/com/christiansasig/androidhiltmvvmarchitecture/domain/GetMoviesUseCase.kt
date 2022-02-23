package com.christiansasig.androidhiltmvvmarchitecture.domain

import com.christiansasig.androidhiltmvvmarchitecture.data.MovieRepository
import com.christiansasig.androidhiltmvvmarchitecture.data.database.entities.toDatabase
import com.christiansasig.androidhiltmvvmarchitecture.domain.model.Movie
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val repository: MovieRepository) {
    suspend operator fun invoke():List<Movie>{
        val movies = repository.getAllMoviesFromApi(1, "popularity.des")

        return if(movies.isNotEmpty()){
            repository.clearMovies()
            repository.insertMovies(movies.map { it.toDatabase() })
            movies
        }else{
            repository.getAllMoviesFromDatabase()
        }
    }
}