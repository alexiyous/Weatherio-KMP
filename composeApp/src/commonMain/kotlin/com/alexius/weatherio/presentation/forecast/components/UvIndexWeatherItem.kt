package com.alexius.weatherio.presentation.forecast.components

import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alexius.weatherio.common.utils.compose.neumorphicDown
import com.alexius.weatherio.domain.models.forecast.DailyWeatherInfo
import org.jetbrains.compose.resources.stringResource
import weatherio.composeapp.generated.resources.Res
import weatherio.composeapp.generated.resources.uv_index

@Composable
fun UvIndexWeatherItem(
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
            text = stringResource(Res.string.uv_index),
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.basicMarquee(),
        )
        Text(
            text = weatherInfo.uvIndex.toString(),
            style = MaterialTheme.typography.displayMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = stringResource(weatherInfo.weatherStatus.info),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.basicMarquee()
        )
    }
}
