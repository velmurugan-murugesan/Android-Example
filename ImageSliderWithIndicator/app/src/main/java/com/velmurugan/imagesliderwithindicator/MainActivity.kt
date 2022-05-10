package com.velmurugan.imagesliderwithindicator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import coil.compose.rememberImagePainter
import coil.size.Scale
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.velmurugan.imagesliderwithindicator.ui.theme.ImageSliderWithIndicatorTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalPagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImageSliderWithIndicatorTheme {
                // A surface container using the 'background' color from the theme
                val mainViewModel by viewModels<MainViewModel>()

                mainViewModel.getAllMovies()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    val state = rememberPagerState()
                    Column {
                        SliderView(state, mainViewModel)
                        Spacer(modifier = Modifier.padding(4.dp))
                        DotsIndicator(
                            totalDots = mainViewModel.movieListResponse.size,
                            selectedIndex = state.currentPage
                        )

                        LazyColumn() {
                            items(mainViewModel.movieListResponse) { item ->
                                MovieCard(movie = item)
                            }
                        }
                    }
                    LaunchedEffect(key1 = state.currentPage) {
                        delay(3000)
                        var newPosition = state.currentPage + 1
                        if (newPosition > mainViewModel.movieListResponse.size - 1) newPosition = 0
                        // scrolling to the new position.
                        state.animateScrollToPage(newPosition)
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun SliderView(state: PagerState, viewModel: MainViewModel) {

    val imageUrl =
        remember { mutableStateOf("") }
    HorizontalPager(
        state = state,
        count = viewModel.movieListResponse.size, modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()
    ) { page ->
        imageUrl.value = viewModel.movieListResponse[page].imageUrl

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(contentAlignment = Alignment.BottomCenter) {

                val painter = rememberImagePainter(data = imageUrl.value, builder = {
                    placeholder(R.drawable.placeholder)
                    scale(Scale.FILL)
                })
                Image(
                    painter = painter, contentDescription = "", Modifier
                        .padding(8.dp).clip(RoundedCornerShape(10.dp))
                        .fillMaxSize(), contentScale = ContentScale.Crop
                )

                Text(
                    text = viewModel.movieListResponse[page].name,
                    Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(8.dp)
                        .background(Color.LightGray.copy(alpha = 0.60F))
                        .padding(8.dp),
                    textAlign = TextAlign.Start,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
            }



        }


    }
}


@Composable
fun MovieCard(movie: Movies) {

    Card(modifier = Modifier
        .fillMaxWidth()
        .height(100.dp)) {
        Text(text = movie.name, Modifier.fillMaxWidth())

    }

}

@Preview(name = "Sliding Image Preview", showSystemUi = true)
@Composable
fun MovieCardPreview() {
    MovieCard(movie = Movies("Poco","Latest","",""))
}


@Composable
fun DotsIndicator(
    totalDots: Int,
    selectedIndex: Int
) {

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(), horizontalArrangement = Arrangement.Center
    ) {

        items(totalDots) { index ->
            if (index == selectedIndex) {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .clip(CircleShape)
                        .background(color = Color.DarkGray)
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .clip(CircleShape)
                        .background(color = Color.LightGray)
                )
            }

            if (index != totalDots - 1) {
                Spacer(modifier = Modifier.padding(horizontal = 2.dp))
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Preview(showSystemUi = true)
@Composable
fun SliderPreview() {
    Column {
        SliderView(state = rememberPagerState(), MainViewModel())
        DotsIndicator(
            totalDots = 5,
            selectedIndex = 1
        )
    }

}

