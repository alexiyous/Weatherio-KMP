package com.alexius.weatherio

import android.app.Application
import com.alexius.weatherio.di.initKoin
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.android.ext.koin.androidContext

class Weatherio: Application() {
    override fun onCreate() {
        super.onCreate()
        Napier.base(DebugAntilog())
        initKoin{
            androidContext(this@Weatherio)
        }
    }
}