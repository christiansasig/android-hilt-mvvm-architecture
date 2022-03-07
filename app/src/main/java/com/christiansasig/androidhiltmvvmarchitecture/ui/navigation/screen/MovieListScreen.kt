package com.christiansasig.androidhiltmvvmarchitecture.ui.navigation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.christiansasig.androidhiltmvvmarchitecture.R
import com.christiansasig.androidhiltmvvmarchitecture.domain.model.Movie
import com.christiansasig.androidhiltmvvmarchitecture.ui.navigation.Destinations
import com.christiansasig.androidhiltmvvmarchitecture.ui.theme.AndroidHiltMvvmArchitectureTheme
import com.christiansasig.androidhiltmvvmarchitecture.ui.viewmodel.MovieViewModel

@Composable
fun MovieListScreen(
    navController: NavController,
    viewModel: MovieViewModel = hiltViewModel()
) {
    viewModel.onCreate()
    val list by viewModel.movieModel.observeAsState(initial = emptyList())
    val loading by viewModel.isLoading.observeAsState(initial = true)
    ListScreen(navController, list, loading)
}

@Composable
fun ListScreen(
    navController: NavController,
    news: List<Movie>,
    loading: Boolean
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.movies_title)) },
            )
        }
    )
    {
        Box(contentAlignment = Alignment.Center) {
            LazyColumn {
                items(news) { item ->
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate("${Destinations.DETAIL_SCREEN}/${item.id}")
                            },
                    ) {
                        Column {
                            Image(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .aspectRatio(16f / 9f),
                                painter = rememberAsyncImagePainter(
                                    ImageRequest.Builder(LocalContext.current)
                                        .data(data = stringResource(R.string.server_image_url) + item.backdropPath)
                                        .apply(block = fun ImageRequest.Builder.() {
                                            placeholder(R.drawable.placeholder)
                                            error(R.drawable.placeholder)
                                        }).build()
                                ),
                                contentDescription = null,
                                contentScale = ContentScale.FillWidth
                            )
                            Column(Modifier.padding(8.dp)) {
                                Text(item.title, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                                Text(item.overview ?: "", maxLines = 3)
                            }
                        }
                    }
                }
            }
        }
        if (loading) { ShowProgressBar() }
    }
}


@Composable
fun ShowProgressBar() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}

@Preview(showBackground = true)
@Composable
fun ListPreview() {
    AndroidHiltMvvmArchitectureTheme {
        ListScreen(
            navController = rememberNavController(),
            news = arrayListOf(
                Movie(
                    1,
                    "Title", "Content description", 1f, "",
                    "test", "", "", 2f
                ),
                Movie(
                    2,
                    "Title", "Content description", 1f, "",
                    "test", "", "", 2f
                )
            ), true
        )
    }
}