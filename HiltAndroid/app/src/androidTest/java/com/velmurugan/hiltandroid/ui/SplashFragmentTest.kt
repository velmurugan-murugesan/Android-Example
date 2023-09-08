package com.velmurugan.hiltandroid.ui

import android.view.View
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.velmurugan.hiltandroid.launchFragmentInHiltContainer
import com.velmurugan.hiltandroid.repository.FakeRepository
import com.velmurugan.hiltandroid.ui.splash.SplashFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.hamcrest.Matcher
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class SplashFragmentTest {

    @get:Rule
    val hiltAndroidRule = HiltAndroidRule(this)

    @Inject
    lateinit var fakeRepository: FakeRepository

    @Before
    fun setup() {
        hiltAndroidRule.inject()
    }

    @Test
    fun testSplashMessage() {
        fakeRepository.updateVersionNumber(3)

        launchFragmentInHiltContainer<SplashFragment>() {

        }
        Espresso.onView(withText("Hello Home")).check(matches(isDisplayed()))
    }


    @Test
    fun splashNavigationTest() {
        fakeRepository.updateVersionNumber(3)
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext())

        launchFragmentInHiltContainer<SplashFragment>() {
            navController.setGraph(com.velmurugan.hiltandroid.R.navigation.nav_graph)
            Navigation.setViewNavController(requireView(), navController)
        }
        viewWait(2000)
        assertEquals(navController.currentDestination?.id, com.velmurugan.hiltandroid.R.id.homeFragment)
    }

    fun viewWait(delay: Long) {
        onView(isRoot()).perform(waitFor(delay))
    }

    fun waitFor(delay: Long):
            ViewAction = object : ViewAction {
        override fun perform(
            uiController: UiController?, view: View?
        ) {
            uiController?.loopMainThreadForAtLeast(delay)
        }

        override fun getConstraints(): Matcher<View> = isRoot()

        override fun getDescription():
                String = "wait for " + delay + "milliseconds"
    }

}