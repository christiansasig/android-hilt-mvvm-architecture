package com.christiansasig.androidhiltmvvmarchitecture.data.network.model

import com.google.gson.annotations.SerializedName

data class Data(@SerializedName("results") val results: List<MovieModel>?)