package com.velmurugan.hiltandroid.ui

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.velmurugan.hiltandroid.Movie
import com.velmurugan.hiltandroid.data.MainRepository
import com.velmurugan.hiltandroid.launchFragmentInHiltContainer
import com.velmurugan.hiltandroid.repository.FakeRepository
import com.velmurugan.hiltandroid.ui.home.HomeFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class HomeFragmentTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)


    @Inject
    lateinit var fakeRepository: MainRepository
    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun displayTask_whenRepositoryHasData() {
        val list = getMovieList()
        fakeRepository.saveMovie(list)
        launchFragmentInHiltContainer<HomeFragment> {

        }
        //viewWait(2000)
        onView(withText(list.first().name)).check(matches(isDisplayed()))
    }

    private fun getMovieList() = listOf<Movie>(Movie("Poco","",""), Movie("Fire","",""))
    fun viewWait(delay: Long) {
        onView(ViewMatchers.isRoot()).perform(waitFor(delay))
    }

    fun waitFor(delay: Long):
            ViewAction = object : ViewAction {
        override fun perform(
            uiController: UiController?, view: View?
        ) {
            uiController?.loopMainThreadForAtLeast(delay)
        }

        override fun getConstraints(): Matcher<View> = ViewMatchers.isRoot()

        override fun getDescription():
                String = "wait for " + delay + "milliseconds"
    }
}