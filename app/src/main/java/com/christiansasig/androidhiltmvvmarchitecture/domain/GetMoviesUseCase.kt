package com.christiansasig.androidhiltmvvmarchitecture.domain

import com.christiansasig.androidhiltmvvmarchitecture.data.MovieRepository
import com.christiansasig.androidhiltmvvmarchitecture.data.database.entities.toDatabase
import com.christiansasig.androidhiltmvvmarchitecture.domain.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val repository: MovieRepository) {
    operator fun invoke(): Flow<List<Movie>?> {
        return flow {
            var movies = repository.getAllMoviesFromApi(1, "popularity.des")

            if(movies.isNotEmpty()){
                repository.clearMovies()
                repository.insertMovies(movies.map { it.toDatabase() })
            }else{
                movies = repository.getAllMoviesFromDatabase()
            }
            emit(movies)
        }.flowOn(Dispatchers.IO)
    }
}