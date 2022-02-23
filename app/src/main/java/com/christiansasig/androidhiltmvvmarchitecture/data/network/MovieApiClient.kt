package com.christiansasig.androidhiltmvvmarchitecture.data.network

import com.christiansasig.androidhiltmvvmarchitecture.data.model.Data
import com.christiansasig.androidhiltmvvmarchitecture.data.model.MovieModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiClient {
    @GET("discover/movie")
    suspend fun getMovies(@Query("page") page: Int,
                          @Query("sort_by") sort_by: String): Response<Data>

}