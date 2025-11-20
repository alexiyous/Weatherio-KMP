package com.alexius.weatherio.presentation.forecast.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alexius.weatherio.domain.models.forecast.HourlyInfoItem
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun HourlyWeatherInfoItem(
    modifier: Modifier = Modifier,
    infoItem: HourlyInfoItem
) {
    Column(
        modifier = modifier.padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ){
        Text(
            text = "${infoItem.temperature} $DEGREE_SYMBOL",
            style = MaterialTheme.typography.bodySmall
        )
        Spacer(modifier = Modifier.height(8.dp))
        Icon(
            painter = painterResource(infoItem.weatherStatus.icon),
            contentDescription = "Hourly Weather: ${stringResource( infoItem.weatherStatus.info)}",
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = infoItem.time,
            style = MaterialTheme.typography.bodySmall
        )
    }
}