package com.alexius.weatherio.di

import com.alexius.weatherio.data.datasource.local.DatabaseFactory
import com.alexius.weatherio.domain.notification.NotificationManager
import com.alexius.weatherio.domain.work.WorkScheduler
import com.alexius.weatherio.notification.NativeNotificationManager
import com.alexius.weatherio.work.NativeWorkScheduler
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module
    get() = module {
        single<HttpClientEngine> { Darwin.create() }
        single { DatabaseFactory() }
        single<WorkScheduler> { NativeWorkScheduler() }
        single<NotificationManager> { NativeNotificationManager() }
    }
