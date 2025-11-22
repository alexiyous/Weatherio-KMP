package com.alexius.weatherio.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.alexius.weatherio.common.network.HttpClientFactory
import com.alexius.weatherio.data.datasource.local.DatabaseFactory
import com.alexius.weatherio.data.datasource.local.GeolocationDatabase
import com.alexius.weatherio.data.datasource.remote.geolocation.GeolocationRemoteApiService
import com.alexius.weatherio.data.datasource.remote.geolocation.GeolocationRemoteApiServiceImpl
import com.alexius.weatherio.data.datasource.repository.GeolocationRepositoryImpl
import com.alexius.weatherio.repository.GeolocationRepository
import com.alexius.weatherio.common.utils.provideExternalCoroutineScope
import com.alexius.weatherio.data.datasource.remote.forecast.ForecastRemoteApiService
import com.alexius.weatherio.data.datasource.remote.forecast.ForecastRemoteApiServiceImpl
import com.alexius.weatherio.data.datasource.repository.ForecastRepositoryImpl
import com.alexius.weatherio.presentation.forecast.ForecastViewModel
import com.alexius.weatherio.presentation.home.HomeViewModel
import com.alexius.weatherio.repository.ForecastRepository
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val commonModule = module {
    single {
        get<DatabaseFactory>().create()
            .setDriver(BundledSQLiteDriver())
            .build()
    }
    single { get<GeolocationDatabase>().geolocationDao() }
    single { HttpClientFactory.create(get()) }
    single { provideExternalCoroutineScope() }
    singleOf(::GeolocationRemoteApiServiceImpl) bind GeolocationRemoteApiService::class
    singleOf(::GeolocationRepositoryImpl) bind GeolocationRepository::class
    singleOf(::ForecastRemoteApiServiceImpl) bind ForecastRemoteApiService::class
    singleOf(::ForecastRepositoryImpl) bind ForecastRepository::class

    viewModelOf(::HomeViewModel)
    viewModelOf(::ForecastViewModel)
}