package com.example.jetpackcomposenavigationexample.ui.features.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcomposenavigationexample.data.ApiService
import com.example.jetpackcomposenavigationexample.data.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val apiService: ApiService) : ViewModel() {

    //private val _homeUiState = mutableStateOf<HomeUiState>(HomeUiState.Loading)
    private val _homeUiState = MutableStateFlow(HomeUiState())
    val homeUiState: StateFlow<HomeUiState> = _homeUiState

    val _movieList = flow {
        val res = apiService.getAllMovies()
        emit(res)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = emptyList()
    )

}

data class HomeUiState(
    val loading: Boolean = false,
    val movieList: List<Movie> = emptyList()
)