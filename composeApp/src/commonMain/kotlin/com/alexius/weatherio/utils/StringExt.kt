package com.alexius.weatherio.utils

fun String.capitalizeFirst(): String {
    return this.lowercase().replaceFirstChar { it.uppercaseChar() }
}