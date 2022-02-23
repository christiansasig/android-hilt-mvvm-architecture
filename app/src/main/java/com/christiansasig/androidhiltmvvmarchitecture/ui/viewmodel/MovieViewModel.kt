package com.christiansasig.androidhiltmvvmarchitecture.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.christiansasig.androidhiltmvvmarchitecture.domain.GetMoviesUseCase
import com.christiansasig.androidhiltmvvmarchitecture.domain.GetRandomMovieUseCase
import com.christiansasig.androidhiltmvvmarchitecture.domain.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val getRandomMovieUseCase: GetRandomMovieUseCase
) : ViewModel() {

    val movieModel = MutableLiveData<Movie>()
    val isLoading = MutableLiveData<Boolean>()

    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getMoviesUseCase()

            if (!result.isNullOrEmpty()) {
                movieModel.postValue(result[0])
                isLoading.postValue(false)
            }
        }
    }

    fun randomMovie() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val quote = getRandomMovieUseCase()
            if (quote != null) {
                movieModel.postValue(quote)
            }
            isLoading.postValue(false)
        }
    }
}