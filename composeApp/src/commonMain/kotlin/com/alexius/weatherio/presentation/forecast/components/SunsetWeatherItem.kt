package com.alexius.weatherio.presentation.forecast.components

import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alexius.weatherio.domain.models.forecast.DailyWeatherInfo
import org.jetbrains.compose.resources.stringResource
import weatherio.composeapp.generated.resources.Res
import weatherio.composeapp.generated.resources.sunrise
import weatherio.composeapp.generated.resources.sunset
import weatherio.composeapp.generated.resources.uv_index

@Composable
fun SunsetWeatherItem(
    modifier: Modifier = Modifier,
    weatherInfo: DailyWeatherInfo
) {
    Card(modifier = modifier.padding(horizontal = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(Res.string.sunrise),
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.basicMarquee()
            )
            Text(
                text = weatherInfo.sunrise,
                style = MaterialTheme.typography.displayMedium
            )
            Text(
                text = stringResource(Res.string.sunset, weatherInfo.sunset),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.basicMarquee()
            )
        }
    }
}