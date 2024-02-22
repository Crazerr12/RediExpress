package com.example.deliveryapp.presentation.common

import java.util.UUID

fun generateTrackNumber(): String {
    val generatedCode = UUID.randomUUID().toString()
    return "R-$generatedCode"
}