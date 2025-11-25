package com.alexius.weatherio.widget

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.Button
import androidx.glance.ButtonDefaults
import androidx.glance.ColorFilter
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.LocalSize
import androidx.glance.action.ActionParameters
import androidx.glance.action.actionStartActivity
import androidx.glance.action.clickable
import androidx.glance.appwidget.CircularProgressIndicator
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.SizeMode
import androidx.glance.appwidget.action.ActionCallback
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.appwidget.provideContent
import androidx.glance.layout.Alignment
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.size
import androidx.glance.layout.width
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.alexius.weatherio.MainActivity
import com.alexius.weatherio.R
import com.alexius.weatherio.repository.ForecastRepository
import com.alexius.weatherio.repository.GeolocationRepository
import com.alexius.weatherio.widget.components.WeatherWidgetContentDetails
import com.alexius.weatherio.widget.components.WeatherWidgetLayout
import com.alexius.weatherio.widget.models.CurrentWeatherWidgetData
import com.alexius.weatherio.widget.ui.WidgetTheme
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class WeatherWidget : GlanceAppWidget(), KoinComponent {

    private val forecastRepository: ForecastRepository by inject()
    private val geolocationRepository: GeolocationRepository by inject()

    override val sizeMode: SizeMode = SizeMode.Exact

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        WeatherWidgetDataStore.updateWidgetData(context, forecastRepository, geolocationRepository)

        val store = context.weatherWidgetStore
        val weatherStateFlow = store.data.map { prefs ->
            WeatherWidgetDataStore.prefsToWidgetData(prefs)
        }

        provideContent {
            val weatherState by weatherStateFlow.collectAsState(initial = null)

            WidgetTheme {
                WeatherWidgetContent(weatherState)
            }
        }
    }
}

@Composable
private fun WeatherWidgetContent(currentWeather: CurrentWeatherWidgetData?) {
    val size = LocalSize.current
    val width = size.width
    val height = size.height

    val showDetails = width >= 200.dp && height >= 200.dp
    val useRowLayout = height < 160.dp

    val iconSize = if (useRowLayout || !showDetails) 50.dp else 100.dp
    val textSize = if (useRowLayout || !showDetails) 30.sp else 60.sp


    val action = actionStartActivity(MainActivity::class.java)

    WeatherWidgetLayout(
        useRowLayout = useRowLayout,
        action = action,
        currentWeather = currentWeather
    ) {
        if (currentWeather != null) {
            WeatherWidgetContentDetails(
                currentWeather = currentWeather,
                iconSize = iconSize,
                textSize = textSize,
                useRowLayout = useRowLayout,
                showDetails = showDetails,
                refreshCallback = actionRunCallback<RefreshCallback>()
            )
        } else {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Button(
                    text = "Refresh",
                    modifier = GlanceModifier.fillMaxWidth().height(32.dp),
                    onClick = actionRunCallback<RefreshCallback>(),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = ColorProvider(R.color.widget_primary),
                        contentColor = ColorProvider(R.color.widget_onPrimary)
                    )
                )
            }
        }
    }
}

private class RefreshCallback : ActionCallback, KoinComponent {

    private val forecastRepository: ForecastRepository by inject()
    private val geolocationRepository: GeolocationRepository by inject()

    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters
    ) {
        WeatherWidgetDataStore.updateWidgetData(context, forecastRepository, geolocationRepository)
        WeatherWidget().update(context, glanceId)
    }
}
