package com.christiansasig.androidhiltmvvmarchitecture.ui.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.christiansasig.androidhiltmvvmarchitecture.ui.navigation.Destinations
import com.christiansasig.androidhiltmvvmarchitecture.ui.navigation.screen.DetailsScreen
import com.christiansasig.androidhiltmvvmarchitecture.ui.navigation.screen.ListPreview
import com.christiansasig.androidhiltmvvmarchitecture.ui.navigation.screen.MovieListScreen
import com.christiansasig.androidhiltmvvmarchitecture.ui.theme.AndroidHiltMvvmArchitectureTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidHiltMvvmArchitectureTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Destinations.LIST_SCREEN,
                    ) {
                        composable(Destinations.LIST_SCREEN) {
                            MovieListScreen(navController)
                        }
                        composable("${Destinations.DETAIL_SCREEN}/{id}") {
                            it.arguments?.getString("id")?.let { id ->
                                DetailsScreen(id.toInt(), navController)
                            }
                        }
                    }
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        AndroidHiltMvvmArchitectureTheme {
            ListPreview()
        }
    }
}