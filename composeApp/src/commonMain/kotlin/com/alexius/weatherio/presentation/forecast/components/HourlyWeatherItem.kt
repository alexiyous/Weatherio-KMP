package com.alexius.weatherio.presentation.forecast.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.alexius.weatherio.common.utils.TimeUtils
import com.alexius.weatherio.common.utils.compose.neumorphicDown
import com.alexius.weatherio.common.utils.compose.neumorphicUp
import com.alexius.weatherio.domain.models.forecast.Hourly
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
    Column(
        modifier = modifier
            .fillMaxWidth()
            .neumorphicUp(
                shape = RoundedCornerShape(20.dp),
                shadowPadding = 4.dp,
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(Res.string.today),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = TimeUtils.formatUnixToCustom(Clock.System.now().toEpochMilliseconds() / 1000),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        HorizontalDivider(
            thickness = 2.dp,
            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
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
