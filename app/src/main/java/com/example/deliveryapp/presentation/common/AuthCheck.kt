package com.example.deliveryapp.presentation.common

fun String.checkEmail(): Boolean {
    val regex: Regex = Regex("""([a-z0-9]+)@([a-z0-9]{3,})\.([a-z]{2,3})""")
    return regex.matches(this)
}

fun String.checkPassword(confirmedPassword: String) = this == confirmedPassword