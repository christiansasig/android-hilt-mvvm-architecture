package com.christiansasig.androidhiltmvvmarchitecture.di

import android.content.Context
import com.christiansasig.androidhiltmvvmarchitecture.R
import com.christiansasig.androidhiltmvvmarchitecture.di.interceptor.AuthInterceptor
import com.christiansasig.androidhiltmvvmarchitecture.data.network.MovieApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideOkHttpClient(@ApplicationContext appContext: Context
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(appContext.getString(R.string.api_key)))
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(@ApplicationContext appContext: Context, okHttpClient: OkHttpClient):Retrofit{
        return Retrofit.Builder()
            .baseUrl(appContext.getString(R.string.server_url))
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideMovieApiClient(retrofit: Retrofit): MovieApiClient {
        return retrofit.create(MovieApiClient::class.java)
    }
}