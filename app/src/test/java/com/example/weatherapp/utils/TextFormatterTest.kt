package com.example.weatherapp.utils

import com.google.common.truth.Truth.assertThat
import org.junit.Test

internal class TextFormatterTest {

    @Test
    fun `Capitalize first letter of location, returns capital L`() {
        val result = TextFormatter.capitalizeFirstLetter("london")

        assertThat(result[0]).isEqualTo('L')
    }
}