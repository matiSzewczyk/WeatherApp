package com.example.weatherapp.utils

import java.util.*

object TextFormatter {

    fun capitalizeFirstLetter(text: String): String {
        return text.replaceFirstChar {
            if (it.isLowerCase()) {
                it.titlecase(Locale.getDefault())
            } else {
                it.toString()
            }
        }
    }
}