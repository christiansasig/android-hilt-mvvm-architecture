package com.christiansasig.androidhiltmvvmarchitecture.data.network

import com.christiansasig.androidhiltmvvmarchitecture.data.model.MovieModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class MovieService @Inject constructor(private val api: MovieApiClient) {

    suspend fun getMovies(page: Int, sortBy: String): List<MovieModel> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getMovies(page, sortBy)
                response.body()?.results ?: emptyList()
            }catch (e:Exception){
                e.printStackTrace()
                emptyList()
            }
        }
    }

}