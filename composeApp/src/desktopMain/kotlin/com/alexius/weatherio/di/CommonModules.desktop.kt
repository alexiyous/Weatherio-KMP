package com.alexius.weatherio.di

import com.alexius.weatherio.data.datasource.local.DatabaseFactory
import com.alexius.weatherio.domain.notification.NotificationManager
import com.alexius.weatherio.domain.work.WorkScheduler
import com.alexius.weatherio.notification.DesktopNotificationManager
import com.alexius.weatherio.work.DesktopWorkScheduler
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module
    get() = module {
        single <HttpClientEngine> { OkHttp.create() }
        single { DatabaseFactory() }
        single<WorkScheduler> { DesktopWorkScheduler() }
        single<NotificationManager> { DesktopNotificationManager() }
    }
