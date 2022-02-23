package com.christiansasig.androidhiltmvvmarchitecture.domain

import com.christiansasig.androidhiltmvvmarchitecture.data.MovieRepository
import com.christiansasig.androidhiltmvvmarchitecture.domain.model.Movie
import javax.inject.Inject

class GetRandomMovieUseCase @Inject constructor(private val repository: MovieRepository) {

    suspend operator fun invoke(): Movie? {
        val quotes = repository.getAllMoviesFromDatabase()
        if (!quotes.isNullOrEmpty()) {
            val randomNumber = (quotes.indices).random()
            return quotes[randomNumber]
        }
        return null
    }
}