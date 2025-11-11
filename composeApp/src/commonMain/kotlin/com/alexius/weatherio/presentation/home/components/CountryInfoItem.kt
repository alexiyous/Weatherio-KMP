package com.alexius.weatherio.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil3.request.ImageRequest
import com.alexius.weatherio.common.utils.compose.rememberImageRequest
import com.alexius.weatherio.domain.models.Geolocation
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

    Row(
        modifier
            .padding(16.dp)
            .background(
                color = backgroundColor
            ),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1f)
        ) {
            FlagImage(imageRequest = imageRequest)

            Spacer(Modifier.width(8.dp))

            Column {
                Text(
                    text = "${geolocation.name}, ${geolocation.countryName}",
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(Modifier.height(8.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = stringResource(Res.string.location_latitude, geolocation.latitude),
                        style = MaterialTheme.typography.bodyLarge,
                    )
                    Text(
                        text = stringResource(Res.string.location_longitude, geolocation.longitude),
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
            }
        }

        Spacer(Modifier.width(8.dp))

        IconButton(onClick = { onFavouriteClick(geolocation) }) {
            Icon(
                imageVector = Icons.Default.Bookmark,
                contentDescription = "Add to favorites",
            )
        }
    }
}