package com.example.jetpackcomposenavigationexample.ui.features.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.jetpackcomposenavigationexample.base.Screens
import com.example.jetpackcomposenavigationexample.data.Movie
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navHostController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {

    val state = homeViewModel._movieList.collectAsStateWithLifecycle()

    Column(Modifier.fillMaxSize()) {
        LazyColumn() {
            items(state.value) {
                MovieCard(movie = it) {
                    navHostController.navigate(Screens.getStep2Screen(it.name))
                }
            }
        }
    }

}


@Composable
fun MovieCard(movie: Movie, onClick: (Movie) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                onClick(movie)
            }, elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )) {
        Column() {
            AsyncImage(
                model = movie.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier.padding(8.dp)) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = movie.category.uppercase(Locale.ROOT),
                    style = MaterialTheme.typography.labelMedium
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = movie.name, style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = movie.desc, style = MaterialTheme.typography.bodyMedium)
            }

        }
    }
}

@Preview
@Composable
fun MovieCardPreview() {
    val movies = listOf(
        Movie(
            "The Shawshank Redemption",
            "A man is sentenced to life in prison for the murder of his wife, but he never gives up hope.",
            "Drama",
            ""
        ),
        Movie(
            "The Godfather",
            "A family saga that spans three generations of the Corleone family under the patriarch Vito Corleone, focusing on the transformation of his youngest son, Michael Corleone, from reluctant family outsider to ruthless mafia boss.",
            "Crime Drama",
            ""
        ),
        Movie(
            "The Dark Knight",
            "When the menacing villain Joker wreaks havoc across Gotham City, Batman must accept one of his greatest challenges yet.",
            "Action, Crime, Drama",
            ""
        )
    )

    movies.forEach { movie ->
        MovieCard(movie = movie) {

        }
    }
}