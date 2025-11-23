package com.alexius.weatherio

import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.alexius.weatherio.di.initKoin
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
fun main() {
    Napier.base(DebugAntilog())
    initKoin()
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "Weatherio",
        ) {
            val calculatedScreenSize = calculateWindowSizeClass()
            App(calculatedScreenSize.widthSizeClass)
        }
    }
}