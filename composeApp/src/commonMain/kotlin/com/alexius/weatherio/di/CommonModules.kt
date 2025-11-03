package com.alexius.weatherio.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.alexius.weatherio.data.datasource.local.DatabaseFactory
import org.koin.core.module.Module
import org.koin.dsl.module

expect val platformModule: Module

val commonModule = module {
    single {
        get<DatabaseFactory>().create()
            .setDriver(BundledSQLiteDriver())
            .build()
    }
}