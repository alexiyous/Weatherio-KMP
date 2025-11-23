package com.alexius.weatherio.presentation.forecast

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alexius.weatherio.common.ui.ErrorEmptyScreen
import com.alexius.weatherio.common.ui.Loader
import com.alexius.weatherio.common.ui.toErrorEmptyState
import com.alexius.weatherio.common.utils.compose.rememberImageRequest
import com.alexius.weatherio.presentation.forecast.components.CurrentWeatherItem
import com.alexius.weatherio.presentation.forecast.components.HourlyWeatherItem
import com.alexius.weatherio.presentation.forecast.components.LineGraph
import com.alexius.weatherio.presentation.forecast.components.SunsetWeatherItem
import com.alexius.weatherio.presentation.forecast.components.UvIndexWeatherItem
import com.alexius.weatherio.presentation.home.components.FlagImage
import com.alexius.weatherio.presentation.utils.NavigationType
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ForecastScreen(
    modifier: Modifier = Modifier,
    forecastViewModel: ForecastViewModel = koinViewModel(),
    navigationType: NavigationType,
    onSearchClick: () -> Unit,
) {
    val state by forecastViewModel.forecastState.collectAsStateWithLifecycle()

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AnimatedContent(
                targetState = when {
                    state.isLoading -> "LOADING"
                    state.error != null -> "ERROR"
                    else -> "DATA"
                }
            ) {
                when (it) {
                    "LOADING" -> {
                        Loader(modifier = Modifier.fillMaxSize())
                    }
                    "ERROR" -> {
                        ErrorEmptyScreen(
                            modifier = Modifier.fillMaxSize().navigationBarsPadding(),
                            errorEmptyState = state.error!!.toErrorEmptyState(),
                            navigationType = navigationType,
                            buttonAction = { onSearchClick() }
                        )
                    }
                    "DATA" -> {
                        LazyColumn(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {
                            item {
                                state.weather?.let { weather ->
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = spacedBy(16.dp)
                                    ) {
                                        CurrentWeatherItem(
                                            currentWeather = weather.currentWeather
                                        )

                                        state.dailyWeatherInfo?.let { dailyWeatherInfo ->
                                            Row(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .height(IntrinsicSize.Min),
                                                horizontalArrangement = Arrangement.spacedBy(16.dp),
                                            ) {
                                                SunsetWeatherItem(weatherInfo = dailyWeatherInfo, modifier = Modifier.fillMaxHeight().weight(1f))
                                                UvIndexWeatherItem(weatherInfo = dailyWeatherInfo, modifier = Modifier.fillMaxHeight().weight(1f))
                                            }
                                        }

                                        HourlyWeatherItem(
                                            hourly = weather.hourly
                                        )
                                    }
                                    Spacer(Modifier.height(16.dp))
                                }
                            }
                            item {
                                state.weather?.let { weather ->
                                    LineGraph(
                                        dataPoints = weather.hourly.hourlyInfoItem,
                                        xValueMapper = { it.time.take(2) },
                                        yValueMapper = { it.temperature.toFloat() },
                                        modifier = Modifier.fillMaxWidth().height(250.dp),
                                        graphTitle = "Temperature Over Time",
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
        
        // Location Selector Button
        state.selectedLocation?.let { location ->
            Row(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .statusBarsPadding()
                    .padding(16.dp)
                    .dropShadow(
                        shape = RoundedCornerShape(16.dp),
                        shadow = Shadow(
                            color = Color.Black.copy(0.25f),
                            offset = DpOffset(4.dp, 4.dp),
                            radius = 10.dp,
                            spread = 0.dp,
                            blendMode = BlendMode.SrcOver
                        )
                    )
                    .clip(RoundedCornerShape(16.dp))
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                    )
                    .clickable(
                        interactionSource = MutableInteractionSource(),
                        indication = ripple(),
                        onClick = { onSearchClick() }
                    )
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                FlagImage(
                    modifier = Modifier.size(24.dp),
                    imageRequest = rememberImageRequest(url = location.flagUrl)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = location.name, color = MaterialTheme.colorScheme.onSurface)
            }
        }
    }
}
