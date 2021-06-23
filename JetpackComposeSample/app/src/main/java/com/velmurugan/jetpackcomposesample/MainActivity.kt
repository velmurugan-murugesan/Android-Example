package com.velmurugan.jetpackcomposesample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.velmurugan.jetpackcomposesample.theme.MovieAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            MovieAppTheme {
                displayName()
            }

        }
    }
    
    @Preview("Name")
    @Composable()
    fun displayName() {
        Column {
            Text(text = "Velmurugan", style = MaterialTheme.typography.h2)
            Text(text = "Velmurugan", style = MaterialTheme.typography.subtitle1)
            Text(text = "Velmurugan", style = MaterialTheme.typography.body1)
            Text(text = "Velmurugan", style = MaterialTheme.typography.caption)

        }



    }
}