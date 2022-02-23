package com.christiansasig.androidhiltmvvmarchitecture.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.christiansasig.androidhiltmvvmarchitecture.databinding.ActivityMainBinding
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

        movieViewModel.movieModel.observe(this, Observer {
            binding.tvQuote.text = it.title
            binding.tvAuthor.text = it.backdropPath
        })
        movieViewModel.isLoading.observe(this, Observer {
            binding.loading.isVisible = it
        })

        binding.viewContainer.setOnClickListener { movieViewModel.randomMovie() }

    }

}