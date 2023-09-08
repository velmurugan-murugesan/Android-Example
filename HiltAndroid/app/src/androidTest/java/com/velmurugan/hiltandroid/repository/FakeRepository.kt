package com.velmurugan.hiltandroid.repository

import com.velmurugan.hiltandroid.Movie
import com.velmurugan.hiltandroid.data.MainRepository
import com.velmurugan.hiltandroid.module.RepositoriesModule
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import org.mockito.Mock
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RepositoriesModule::class]
)
abstract class FakeAnalyticsModule {

    @Singleton
    @Binds
    abstract fun bindAnalyticsService(
        fakeAnalyticsService: FakeRepository
    ): MainRepository
}


class FakeRepository @Inject constructor() : MainRepository {

    private var versionNumber = 4
    private var movieList = listOf<Movie>()
    override fun saveMovie(movieList: List<Movie>) {
        this.movieList = movieList
    }

    override suspend fun getAllMovies(): Response<List<Movie>> {
        return Response.success(movieList)
    }

    fun updateVersionNumber(version: Int) {
        versionNumber = version
    }

    override suspend fun getVersion(): Response<Int> {
        return Response.success(versionNumber)
    }
}