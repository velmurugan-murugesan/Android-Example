package com.velmurugan.tablayoutonjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.*
import com.velmurugan.tablayoutonjetpackcompose.ui.theme.TabLayoutOnJetpackComposeTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TabLayoutOnJetpackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainScreen() {
    val tabData = getTabList()
    val pagerState = rememberPagerState(pageCount = tabData.size)
    Column(modifier = Modifier.fillMaxSize()) {
        TabLayout(tabData, pagerState)
        TabContent(tabData, pagerState)
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabLayout(tabData: List<Pair<String, ImageVector>>, pagerState: PagerState) {

    val scope = rememberCoroutineScope()
   /* val tabColor = listOf(
        Color.Gray,
        Color.Yellow,
        Color.Blue,
        Color.Red
    )
*/
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        divider = {
                  Spacer(modifier =Modifier.height(5.dp))
        },
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                height = 5.dp,
                color = Color.White
            )
        },


        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        tabData.forEachIndexed { index, s ->
            Tab(selected = pagerState.currentPage == index, onClick = {
                scope.launch {
                    pagerState.animateScrollToPage(index)
                }
            },
                icon = {
                    Icon(imageVector = s.second, contentDescription = null)
                },
                text = {
                    Text(text = s.first)
                })
        }
    }
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabContent(tabData: List<Pair<String, ImageVector>>, pagerState: PagerState) {
    HorizontalPager(state = pagerState) { index ->
        when (index) {
            0 -> {
                HomeScreen()
            }

            1 -> {
                SearchScreen()
            }

            2 -> {
                FavoritesScreen()
            }

            3 -> {
                SettingsScreen()
            }
        }

    }

}


private fun getTabList(): List<Pair<String, ImageVector>> {
    return listOf(
        "Home" to Icons.Default.Home,
        "Search" to Icons.Default.Search,
        "Favorites" to Icons.Default.Favorite,
        "Settings" to Icons.Default.Settings,
    )
}

@OptIn(ExperimentalPagerApi::class)
@Preview()
@Composable
fun PreviewTab() {
    TabLayoutOnJetpackComposeTheme {
        val tabData = getTabList()
        val pagerState = rememberPagerState(pageCount = tabData.size)
        TabLayout(tabData, pagerState)
    }
}

@Preview()
@Composable
fun PreviewContent() {
    TabLayoutOnJetpackComposeTheme {
        MainScreen()
    }
}


