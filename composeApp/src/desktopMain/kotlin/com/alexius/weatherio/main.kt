package com.alexius.weatherio

import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.alexius.weatherio.di.initKoin
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.jetbrains.compose.resources.painterResource
import weatherio.composeapp.generated.resources.Res
import weatherio.composeapp.generated.resources.weatherio_playstore

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
fun main() {
    Napier.base(DebugAntilog())
    initKoin()
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "Weatherio",
            icon = painterResource(Res.drawable.weatherio_playstore)
        ) {
            val calculatedScreenSize = calculateWindowSizeClass()
            App(calculatedScreenSize.widthSizeClass)
        }
    }
}