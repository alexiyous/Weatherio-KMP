package com.alexius.weatherio

import androidx.compose.ui.window.ComposeUIViewController
import com.alexius.weatherio.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { App() }