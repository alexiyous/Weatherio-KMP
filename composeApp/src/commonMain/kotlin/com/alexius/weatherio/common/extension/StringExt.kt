package com.alexius.weatherio.common.extension

fun String.capitalizeFirst(): String {
    return this.lowercase().replaceFirstChar { it.uppercaseChar() }
}