package com.velmurugan.hiltandroid

import android.util.Log
import org.junit.Test
import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val list = listOf<String>("Hello", "Test")
        println(list)
        val string1 = list.joinToString(", ")
        println(string1)
        val list2 = string1.split(", ")
        println(list2)
        assertEquals(4, 2 + 2)
    }
}