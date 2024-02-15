package com.example.deliveryapp.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.deliveryapp.R

val robotoFonts = FontFamily(
    Font(
        resId = R.font.roboto_black,
        weight = FontWeight.Black
    ),
    Font(
        resId = R.font.roboto_bold,
        weight = FontWeight.Bold
    ),
    Font(
        resId = R.font.roboto_bold,
        weight = FontWeight.SemiBold
    ),
    Font(
        resId = R.font.roboto_bold,
        weight = FontWeight.ExtraBold
    ),
    Font(
        resId = R.font.roboto_medium,
        weight = FontWeight.Medium
    ),
    Font(
        resId = R.font.roboto_regular,
        weight = FontWeight.Normal
    )
)

// Set of Material typography styles to start with
val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = robotoFonts,
        fontWeight = FontWeight.Black,
        fontSize = 128.sp
    ),
    displayMedium = TextStyle(
        fontFamily = robotoFonts,
        fontWeight = FontWeight.Black,
        fontSize = 96.sp
    ),
    displaySmall = TextStyle(
        fontFamily = robotoFonts,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 64.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = robotoFonts,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 36.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = robotoFonts,
        fontWeight = FontWeight.Bold,
        fontSize = 36.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = robotoFonts,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    ),
    titleLarge = TextStyle(
        fontFamily = robotoFonts,
        fontWeight = FontWeight.Medium,
        fontSize = 24.sp
    ),
    titleMedium = TextStyle(
        fontFamily = robotoFonts,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp
    ),
    titleSmall = TextStyle(
        fontFamily = robotoFonts,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = robotoFonts,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = robotoFonts,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),
    bodySmall = TextStyle(
        fontFamily = robotoFonts,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    labelLarge = TextStyle(
        fontFamily = robotoFonts,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    labelMedium = TextStyle(
        fontFamily = robotoFonts,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp
    ),
    labelSmall = TextStyle(
        fontFamily = robotoFonts,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp
    )
)