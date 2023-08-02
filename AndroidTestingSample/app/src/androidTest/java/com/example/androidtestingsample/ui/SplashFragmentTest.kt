package com.example.androidtestingsample.ui

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.androidtestingsample.R
import com.example.androidtestingsample.data.MOVIE_LIST
import com.example.androidtestingsample.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class SplashFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)




    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testSample() {
        launchFragmentInHiltContainer<SplashFragment>()
        Espresso.onView(ViewMatchers.withId(R.id.splashText)).check(ViewAssertions.matches(
            isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.splashButton)).check(ViewAssertions.matches(isDisplayed()))


        Espresso.onView(ViewMatchers.withId(R.id.splashText)).check(matches(withText(MOVIE_LIST[0].name)))

    }
}