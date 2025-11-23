package com.alexius.weatherio.work

import android.content.Context
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.alexius.weatherio.domain.work.WorkScheduler
import java.util.concurrent.TimeUnit

class AndroidWorkScheduler(
    private val context: Context
) : WorkScheduler {
    override fun scheduleForecastSync() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val workRequest = PeriodicWorkRequestBuilder<ForecastWorker>(
            30, TimeUnit.MINUTES
        )
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            "ForecastSync",
            ExistingPeriodicWorkPolicy.UPDATE,
            workRequest
        )
    }

    override fun cancelForecastSync() {
        WorkManager.getInstance(context).cancelUniqueWork("ForecastSync")
    }
}
