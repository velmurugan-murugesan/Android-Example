package com.velmurugan.hiltandroid

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.velmurugan.hiltandroid.data.MainRepositoryImpl
import com.velmurugan.hiltandroid.ui.home.HomeViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response


@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(JUnit4::class)
class HomeViewModelTest {

    private lateinit var calculator: MainRepositoryImpl
    private lateinit var viewModel: HomeViewModel

    private val testDispatcher = UnconfinedTestDispatcher()

    @get:Rule
    val instantTaskExecutionRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var mainRepository: MainRepositoryImpl

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
       /* calculator = mockk<MainRepositoryImpl>() {
            runBlocking {
            }
        }*/
        viewModel = HomeViewModel(mainRepository)
    }

    @Test
    fun testMainRespo() {
            runBlocking {
                Mockito.`when`(mainRepository.getAllMovies())
                    .thenReturn(Response.success(listOf<Movie>()))
               // whenever(calculator.getAllMovies()).thenReturn(Response.success(listOf<Movie>()))
                viewModel.fetchAllMovies()
                assertEquals(listOf<Movie>(),viewModel.movieList.getOrAwaitValue())
            }

    }

}