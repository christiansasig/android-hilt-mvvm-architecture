package com.christiansasig.androidhiltmvvmarchitecture.data.model

import com.google.gson.annotations.SerializedName

data class Data(@SerializedName("results") val results: List<MovieModel>?)