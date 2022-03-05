package com.christiansasig.androidhiltmvvmarchitecture.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.christiansasig.androidhiltmvvmarchitecture.databinding.ActivityMainBinding
import com.christiansasig.androidhiltmvvmarchitecture.domain.model.Movie
import com.christiansasig.androidhiltmvvmarchitecture.ui.adapter.MovieAdapter
import com.christiansasig.androidhiltmvvmarchitecture.ui.viewmodel.LatestNewsUiState
import com.christiansasig.androidhiltmvvmarchitecture.ui.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val movieViewModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        movieViewModel.onCreate()

        movieViewModel.isLoading.observe(this) {
            binding.loading.isVisible = it
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                movieViewModel.uiState.collect {uiState ->
                    when (uiState) {
                        is LatestNewsUiState.Success -> showMovies(uiState.movies)
                        is LatestNewsUiState.Error -> showError(uiState.exception)
                    }
                }
            }
        }
    }

    private fun showMovies(movies: List<Movie>) {
        binding.movies.adapter = MovieAdapter(this, movies)
    }

    private fun showError(exception: Throwable) {
        exception.printStackTrace()
    }

}