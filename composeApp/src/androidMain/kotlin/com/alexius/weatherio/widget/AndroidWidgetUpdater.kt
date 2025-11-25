package com.alexius.weatherio.widget

import android.content.Context
import androidx.glance.appwidget.updateAll
import com.alexius.weatherio.domain.widget.WidgetUpdater
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class AndroidWidgetUpdater(
    private val context: Context,
    private val coroutineScope: CoroutineScope
) : WidgetUpdater {
    override fun updateAllWidgets() {
        coroutineScope.launch {
            WeatherWidget().updateAll(context)
        }
    }
}