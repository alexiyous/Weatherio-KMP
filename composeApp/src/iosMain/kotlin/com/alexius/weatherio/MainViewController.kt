package com.alexius.weatherio

import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.ui.window.ComposeUIViewController
import com.alexius.weatherio.di.initKoin
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
fun MainViewController() = ComposeUIViewController(
    configure = {
        Napier.base(DebugAntilog())
        initKoin()
    }
) {
    val calculatedScreenSize = calculateWindowSizeClass()
    App(calculatedScreenSize.widthSizeClass)
}