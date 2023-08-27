package com.velmurugan.hiltandroid

import com.velmurugan.hiltandroid.data.ApiService
import com.velmurugan.hiltandroid.data.MainRepositoryImpl
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

@RunWith(JUnit4::class)
class RepositoryTest {


    @Mock
    lateinit var apiService: ApiService

    lateinit var mainRepositoryImpl: MainRepositoryImpl


    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)

        mainRepositoryImpl = MainRepositoryImpl(apiService)
    }
    @Test
    fun tedst() {

        runBlocking {

            Mockito.`when`(apiService.getAllMovies())
                .thenReturn(Response.success(listOf<Movie>()))

            val respo = mainRepositoryImpl.getAllMovies()
            TestCase.assertEquals(listOf<Movie>(), respo.body())
        }


    }





}