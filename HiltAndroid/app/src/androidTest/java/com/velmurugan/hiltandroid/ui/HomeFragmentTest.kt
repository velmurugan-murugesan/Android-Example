package com.velmurugan.hiltandroid.ui

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.velmurugan.hiltandroid.launchFragmentInHiltContainer
import com.velmurugan.hiltandroid.ui.home.HomeFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class HomeFragmentTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun init() {
        // Populate @Inject fields in test class
    }

    @Test
    fun displayTask_whenRepositoryHasData() {
        launchFragmentInHiltContainer<HomeFragment>()

    }

}