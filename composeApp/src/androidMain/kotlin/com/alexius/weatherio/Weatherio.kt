package com.alexius.weatherio

import android.app.Application
import com.alexius.weatherio.di.initKoin
import org.koin.android.ext.koin.androidContext

class Weatherio: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin{
            androidContext(this@Weatherio)
        }
    }
}