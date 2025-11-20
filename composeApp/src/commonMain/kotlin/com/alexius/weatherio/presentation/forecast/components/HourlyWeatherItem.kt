package com.alexius.weatherio.presentation.forecast.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alexius.weatherio.common.utils.TimeUtils
import com.alexius.weatherio.domain.models.forecast.Hourly
import com.alexius.weatherio.domain.models.forecast.HourlyInfoItem
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import weatherio.composeapp.generated.resources.Res
import weatherio.composeapp.generated.resources.today
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
@Composable
fun HourlyWeatherItem(
    modifier: Modifier = Modifier,
    hourly: Hourly
) {
    Card(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(Res.string.today),
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = TimeUtils.formatUnixToCustom(Clock.System.now().toEpochMilliseconds() / 1000),
                style = MaterialTheme.typography.bodyMedium
            )
        }
        HorizontalDivider(
            thickness = 2.dp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        LazyRow(
            modifier = Modifier.padding(16.dp)
        ) {
            items(hourly.hourlyInfoItem) {
                HourlyWeatherInfoItem(infoItem = it)
            }
        }
    }
}