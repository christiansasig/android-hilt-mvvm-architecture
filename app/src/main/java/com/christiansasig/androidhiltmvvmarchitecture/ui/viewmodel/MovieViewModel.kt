package com.christiansasig.androidhiltmvvmarchitecture.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.christiansasig.androidhiltmvvmarchitecture.domain.GetMoviesUseCase
import com.christiansasig.androidhiltmvvmarchitecture.domain.GetMovieUseCase
import com.christiansasig.androidhiltmvvmarchitecture.domain.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val getMovieUseCase: GetMovieUseCase
) : ViewModel() {

    val movieModel = MutableLiveData<List<Movie>>()
    val isLoading = MutableLiveData<Boolean>()
    val movie = MutableLiveData<Movie>()

    fun onCreate() {
        viewModelScope.launch(Dispatchers.IO) {
            isLoading.postValue(true)
            val result = getMoviesUseCase()
            if (!result.isNullOrEmpty()) {
                movieModel.postValue(result)
                isLoading.postValue(false)
            }
        }
    }

    fun getMovieById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            movie.postValue( getMovieUseCase.getMovieFromDatabaseById(id))
        }
    }
}