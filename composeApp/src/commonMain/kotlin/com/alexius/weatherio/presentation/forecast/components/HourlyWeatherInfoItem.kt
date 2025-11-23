package com.alexius.weatherio.presentation.forecast.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alexius.weatherio.common.utils.compose.neumorphicDown
import com.alexius.weatherio.domain.models.forecast.HourlyInfoItem
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun HourlyWeatherInfoItem(
    modifier: Modifier = Modifier,
    infoItem: HourlyInfoItem
) {
    Column(
        modifier = modifier
            .padding(6.dp)
            .neumorphicDown(
                shape = RoundedCornerShape(6.dp),
                shadowPadding = 2.dp,
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "${infoItem.temperature} $DEGREE_SYMBOL",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.height(8.dp))
        Icon(
            painter = painterResource(infoItem.weatherStatus.icon),
            contentDescription = "Hourly Weather: ${stringResource(infoItem.weatherStatus.info)}",
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = infoItem.time,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}