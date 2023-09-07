package com.velmurugan.hiltandroid.ui

import android.os.Build
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.velmurugan.hiltandroid.data.MainRepository
import com.velmurugan.hiltandroid.launchFragmentInHiltContainer
import com.velmurugan.hiltandroid.repository.FakeRepository
import com.velmurugan.hiltandroid.ui.splash.SplashFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import javax.inject.Inject

@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
@Config(
    manifest = Config.NONE,
    sdk = [Build.VERSION_CODES.O_MR1],
    application = HiltTestApplication::class
)
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
        runTest {
            launchFragmentInHiltContainer<SplashFragment> {
                Espresso.onView(withText("Hello Home")).check(matches(isDisplayed()))

            }
            delay(3000)
            Espresso.onView(withText("Hello Home")).check(matches(isDisplayed()))
        }
    }

}