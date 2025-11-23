package com.alexius.weatherio.presentation.forecast.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.alexius.weatherio.common.extension.compose.asStringCompose
import com.alexius.weatherio.domain.models.forecast.CurrentWeather
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import weatherio.composeapp.generated.resources.Res
import weatherio.composeapp.generated.resources.weather_status
import weatherio.composeapp.generated.resources.wind_speed

const val DEGREE_SYMBOL = "\u00B0C"

@Composable
fun CurrentWeatherItem(
    modifier: Modifier = Modifier,
    currentWeather: CurrentWeather
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
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
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            painter = painterResource(currentWeather.weatherStatus.icon),
            contentDescription = "Current Weather: ${stringResource( currentWeather.weatherStatus.info)}",
            modifier = Modifier.size(120.dp),
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = currentWeather.temperature.toString() + DEGREE_SYMBOL,
            style = MaterialTheme.typography.displayMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = stringResource(Res.string.weather_status, stringResource(currentWeather.weatherStatus.info)),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = stringResource(Res.string.wind_speed, currentWeather.windSpeed, currentWeather.windDirection.asStringCompose()),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}
