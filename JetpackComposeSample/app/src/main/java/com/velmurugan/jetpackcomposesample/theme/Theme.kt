package com.velmurugan.jetpackcomposesample.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.velmurugan.jetpackcomposesample.R

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)
val movieAppColors =
    darkColors(primary = Purple500, primaryVariant = Purple700, secondary = Teal200)
private val Poppins = FontFamily(
    Font(R.font.poppins_light),
    Font(R.font.poppins_medium, FontWeight.W500),
    Font(R.font.poppins_bold, FontWeight.W600),
    Font(R.font.poppins_black, FontWeight.W700)
)
val MovieTypography = Typography(
    h2 = TextStyle(fontFamily = Poppins, fontWeight = FontWeight.W700, fontSize = 22.sp),
    body1 = TextStyle(fontFamily = Poppins, fontWeight = FontWeight.W500, fontSize = 18.sp),
    subtitle1 = TextStyle(fontFamily = Poppins, fontWeight = FontWeight.W500, fontSize = 20.sp),
    caption = TextStyle(fontFamily = Poppins, fontWeight = FontWeight.W400, fontSize = 16.sp)
)
@Composable
fun MovieAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(colors = movieAppColors, typography = MovieTypography, content = content)
}