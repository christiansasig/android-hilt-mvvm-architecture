package com.christiansasig.androidhiltmvvmarchitecture.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.christiansasig.androidhiltmvvmarchitecture.databinding.ActivityMainBinding
import com.christiansasig.androidhiltmvvmarchitecture.ui.adapter.MovieAdapter
import com.christiansasig.androidhiltmvvmarchitecture.ui.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val movieViewModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        movieViewModel.onCreate()

        movieViewModel.movieModel.observe(this) {
            binding.movies.adapter = MovieAdapter(this, it)
        }
        movieViewModel.isLoading.observe(this) {
            binding.loading.isVisible = it
        }

        binding.viewContainer.setOnClickListener { movieViewModel.randomMovie() }

    }

}