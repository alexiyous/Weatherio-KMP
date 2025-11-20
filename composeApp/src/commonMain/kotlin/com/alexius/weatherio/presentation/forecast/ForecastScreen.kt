package com.alexius.weatherio.presentation.forecast

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alexius.weatherio.common.ui.ErrorEmptyScreen
import com.alexius.weatherio.common.ui.Loader
import com.alexius.weatherio.common.ui.toErrorEmptyState
import com.alexius.weatherio.presentation.forecast.components.CurrentWeatherItem
import com.alexius.weatherio.presentation.forecast.components.HourlyWeatherItem
import com.alexius.weatherio.presentation.forecast.components.SunsetWeatherItem
import com.alexius.weatherio.presentation.forecast.components.UvIndexWeatherItem
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
        modifier = modifier.fillMaxSize().padding(16.dp),
        contentAlignment = Alignment.Center,
    ) {
        Column(
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
                                    Column(verticalArrangement = spacedBy(16.dp)) {
                                        CurrentWeatherItem(
                                            currentWeather = weather.currentWeather
                                        )

                                        state.dailyWeatherInfo?.let { dailyWeatherInfo ->
                                            Row(
                                                modifier = Modifier
                                                    .fillMaxWidth(),
                                                horizontalArrangement = Arrangement.SpaceBetween,
                                            ) {
                                                SunsetWeatherItem(weatherInfo = dailyWeatherInfo)
                                                UvIndexWeatherItem(weatherInfo = dailyWeatherInfo)
                                            }
                                        }

                                        HourlyWeatherItem(
                                            hourly = weather.hourly
                                        )
                                    }
                                    Spacer(Modifier.height(16.dp))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}