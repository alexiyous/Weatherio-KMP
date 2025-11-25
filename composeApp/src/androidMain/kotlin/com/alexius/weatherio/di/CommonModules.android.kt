package com.alexius.weatherio.di

import com.alexius.weatherio.data.datasource.local.DatabaseFactory
import com.alexius.weatherio.domain.notification.NotificationManager
import com.alexius.weatherio.domain.widget.WidgetUpdater
import com.alexius.weatherio.domain.work.WorkScheduler
import com.alexius.weatherio.notification.AndroidNotificationManager
import com.alexius.weatherio.widget.AndroidWidgetUpdater
import com.alexius.weatherio.work.AndroidWorkScheduler
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import kotlinx.coroutines.CoroutineScope
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module
    get() = module {
        single <HttpClientEngine> { OkHttp.create() }
        single { DatabaseFactory(androidApplication()) }
        single<WorkScheduler> { AndroidWorkScheduler(androidApplication()) }
        single<NotificationManager> { AndroidNotificationManager(androidApplication()) }
        single<WidgetUpdater> { AndroidWidgetUpdater(androidApplication(), get<CoroutineScope>()) }
    }
