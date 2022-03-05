package com.christiansasig.androidhiltmvvmarchitecture.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.christiansasig.androidhiltmvvmarchitecture.domain.GetMoviesUseCase
import com.christiansasig.androidhiltmvvmarchitecture.domain.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    private val _uiState = MutableStateFlow(LatestNewsUiState.Success(emptyList()))
    val uiState: StateFlow<LatestNewsUiState> = _uiState

    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)
            getMoviesUseCase().collect { movies ->
                isLoading.postValue(false)
                _uiState.value = LatestNewsUiState.Success(movies!!)
            }
        }
    }
}
sealed class LatestNewsUiState {
    data class Success(val movies: List<Movie>): LatestNewsUiState()
    data class Error(val exception: Throwable): LatestNewsUiState()
}