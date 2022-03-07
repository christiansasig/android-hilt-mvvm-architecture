package com.christiansasig.androidhiltmvvmarchitecture.domain

import com.christiansasig.androidhiltmvvmarchitecture.data.MovieRepository
import com.christiansasig.androidhiltmvvmarchitecture.domain.model.Movie
import javax.inject.Inject

class GetMovieUseCase @Inject constructor(private val repository: MovieRepository) {

    suspend fun getMovieFromDatabaseById(id: Int): Movie {
        return repository.getMovieFromDatabaseById(id)
    }
}