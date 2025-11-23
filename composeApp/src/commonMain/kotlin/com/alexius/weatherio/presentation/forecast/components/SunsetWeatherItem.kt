package com.alexius.weatherio.presentation.forecast.components

import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alexius.weatherio.common.utils.TextResource
import com.alexius.weatherio.common.utils.WeatherInfo
import com.alexius.weatherio.common.utils.compose.neumorphicDown
import com.alexius.weatherio.domain.models.forecast.DailyWeatherInfo
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import weatherio.composeapp.generated.resources.Res
import weatherio.composeapp.generated.resources.sunrise
import weatherio.composeapp.generated.resources.sunset

@Composable
fun SunsetWeatherItem(
    modifier: Modifier = Modifier,
    weatherInfo: DailyWeatherInfo
) {
    Column(
        modifier = modifier
            .padding(horizontal = 8.dp)
            .neumorphicDown(
                shape = RoundedCornerShape(20.dp),
                shadowPadding = 4.dp,
            )
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(Res.string.sunrise),
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.basicMarquee()
        )
        Text(
            text = weatherInfo.sunrise,
            style = MaterialTheme.typography.displayMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = stringResource(Res.string.sunset, weatherInfo.sunset),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.basicMarquee()
        )
    }
}

@Preview()
@Composable
fun SunsetWeatherItemPreview() {
    val weatherInfo = DailyWeatherInfo(
        temperatureMax = 25.0,
        temperatureMin = 15.0,
        time = "2023-10-27",
        weatherStatus = WeatherInfo.getWeatherInfo(0),
        windDirection = TextResource.PlainText("North"),
        windSpeed = 10.0,
        sunrise = "06:30",
        sunset = "18:45",
        uvIndex = 5.0
    )
    SunsetWeatherItem(
        modifier = Modifier.fillMaxWidth(),
        weatherInfo = weatherInfo
    )
}
