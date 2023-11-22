package com.example.jetpackcomposenavigationexample.ui.features.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.jetpackcomposenavigationexample.data.MovieDetails
import java.util.Locale

@Composable
fun DetailsScreen(
    navHostController: NavHostController,
    title: String?,
    detailsViewModel: DetailsViewModel = hiltViewModel()
) {

    val movieDetails by detailsViewModel._movieDetails.collectAsStateWithLifecycle()
    Column(Modifier.fillMaxWidth().padding(8.dp)) {
        Text(text = title ?: "", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(4.dp))
        movieDetails?.let {
            MovieDetailsView(movieDetails = it)
        }
    }


}


@Composable
fun MovieDetailsView(movieDetails: MovieDetails) {
    LazyVerticalGrid(columns = GridCells.Fixed(2), contentPadding = PaddingValues(4.dp)) {
        items(movieDetails.images.size) {
            AsyncImage(model = movieDetails.images[it], contentDescription = null, modifier = Modifier.padding(4.dp))
        }
    }
    Spacer(modifier = Modifier.height(4.dp))
    Text(
        text = movieDetails.category.uppercase(Locale.getDefault()),
        style = MaterialTheme.typography.labelSmall
    )
    Spacer(modifier = Modifier.height(4.dp))
    Text(text = movieDetails.desc, style = MaterialTheme.typography.bodyMedium)

}

@Preview
@Composable
fun MovieDetailsPreview() {
    val movieDetails = MovieDetails("title", "desc", "new", listOf())
    MovieDetailsView(movieDetails = movieDetails)
}

