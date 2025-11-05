package com.alexius.weatherio

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.alexius.weatherio.di.initKoin

fun main() {
    initKoin()
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "Weatherio",
        ) {
            App()
        }
    }
}