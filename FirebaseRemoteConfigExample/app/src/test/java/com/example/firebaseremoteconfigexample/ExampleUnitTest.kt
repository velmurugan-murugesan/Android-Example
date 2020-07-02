package com.example.firebaseremoteconfigexample

import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun compareDateTime() {
        val currentDateTime = 1587929461000
        val cal = Calendar.getInstance() //current date and time

        cal.add(Calendar.DAY_OF_MONTH, -5) //add a day

        val millis = cal.timeInMillis
        println(millis.compareTo(currentDateTime))

    }

    @Test
    fun timeChecker() {

       // val time: String = dateFormatter.format(today)
        /*var currentDateTime= LocalDateTime.now()
        var time = currentDateTime.format(DateTimeFormatter.ofPattern("HH:mm"))
        println(time)*/
        val c = Calendar.getInstance()
        //val time = c[Calendar.HOUR] + c[Calendar.MINUTE] + c[Calendar.SECOND]
        val time ="${c[Calendar.HOUR]}:${c[Calendar.MINUTE]}"

        c.set(Calendar.HOUR, 22)
        c.set(Calendar.MINUTE,49)
        val alertTime ="${c[Calendar.HOUR]}:${c[Calendar.MINUTE]}"

        //val alertTime = dateFormatter.format(today.time)
        println(alertTime)

        val dd = time.compareTo(alertTime)
        println(dd)

    }
}
