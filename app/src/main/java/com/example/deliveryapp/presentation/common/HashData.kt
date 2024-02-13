package com.example.deliveryapp.presentation.common

import java.math.BigInteger
import java.security.MessageDigest

fun hashData(data: ByteArray): String {
    val md = MessageDigest.getInstance("SHA-512")
    val messageDigest = md.digest(data)
    val bigInt = BigInteger(1, messageDigest)
    return bigInt.toString(16)
}