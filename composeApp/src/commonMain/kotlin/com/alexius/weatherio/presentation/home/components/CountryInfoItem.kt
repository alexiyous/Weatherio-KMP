package com.alexius.weatherio.presentation.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.alexius.weatherio.common.utils.compose.neumorphicDown
import com.alexius.weatherio.common.utils.compose.rememberImageRequest
import com.alexius.weatherio.domain.models.home.Geolocation
import org.jetbrains.compose.resources.stringResource
import weatherio.composeapp.generated.resources.Res
import weatherio.composeapp.generated.resources.location_latitude
import weatherio.composeapp.generated.resources.location_longitude

@Composable
fun CountryInfoItem(
    modifier: Modifier = Modifier,
    geolocation: Geolocation,
    backgroundColor: Color,
    onFavouriteClick: (Geolocation) -> Unit
) {
    val imageRequest = rememberImageRequest(url = geolocation.flagUrl)
    val bgColor =
        if (backgroundColor == Color.Unspecified) MaterialTheme.colorScheme.surface else backgroundColor
    val contentColor =
        if (backgroundColor == Color.Unspecified) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onPrimary

    Row(
        modifier
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .clip(RoundedCornerShape(20.dp))
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = ripple(),
                onClick = { onFavouriteClick(geolocation) }
            )
            .neumorphicDown(
                shape = RoundedCornerShape(20.dp),
                shadowPadding = 4.dp,
            )
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1f)
        ) {
            FlagImage(imageRequest = imageRequest)

            Spacer(Modifier.width(8.dp))

            Column {
                Text(
                    text = "${geolocation.name}, ${geolocation.countryName}",
                    style = MaterialTheme.typography.titleLarge,
                    color = contentColor
                )
                Spacer(Modifier.height(8.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = stringResource(Res.string.location_latitude, geolocation.latitude),
                        style = MaterialTheme.typography.bodyLarge,
                        color = contentColor.copy(alpha = 0.7f)
                    )
                    Text(
                        text = stringResource(Res.string.location_longitude, geolocation.longitude),
                        style = MaterialTheme.typography.bodyLarge,
                        color = contentColor.copy(alpha = 0.7f)
                    )
                }
            }
        }
    }
}
