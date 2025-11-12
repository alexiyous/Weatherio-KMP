package com.alexius.weatherio.presentation.home.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.alexius.weatherio.common.ui.ErrorEmptyScreen
import com.alexius.weatherio.common.ui.Loader
import com.alexius.weatherio.common.ui.toErrorEmptyState
import com.alexius.weatherio.domain.models.home.Geolocation
import com.alexius.weatherio.presentation.home.models.HomeState
import com.alexius.weatherio.presentation.utils.NavigationType
import com.alexius.weatherio.common.utils.AppError
import com.alexius.weatherio.common.utils.compose.rememberImageRequest
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import weatherio.composeapp.generated.resources.Res
import weatherio.composeapp.generated.resources.location_latitude
import weatherio.composeapp.generated.resources.location_longitude

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchLocationContent(
    navigationType: NavigationType,
    state: HomeState,
    search: String,
    onSearchChange: (String) -> Unit,
    onFavouriteClick: (Geolocation) -> Unit,
    onSubmit: () -> Unit,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val padding = if (navigationType == NavigationType.PERMANENT_NAVIGATION_DRAWER) 200.dp
    else 0.dp

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = padding),
        contentAlignment = Alignment.Center
    ) {
        HomeSearchBar(
            onNavigateBack = onNavigateBack,
            search = search,
            onSearchChange = onSearchChange,
            onSubmit = onSubmit
        ) {
            AnimatedContent(
                targetState = when {
                    state.isLoading -> "LOADING"
                    state.error != null -> "ERROR"
                    else -> "DATA"
                }
            ) { targetState ->
                when (targetState) {
                    "LOADING" -> {
                        Loader(modifier = Modifier.fillMaxSize())
                    }

                    "ERROR" -> {
                        ErrorEmptyScreen(
                            modifier = Modifier.fillMaxSize().navigationBarsPadding(),
                            errorEmptyState = state.error!!.toErrorEmptyState(),
                            navigationType = navigationType,
                            buttonAction = { onSubmit() }
                        )
                    }

                    "DATA" -> {
                        LazyColumn {
                            items(state.geolocations) {
                                CountryInfoItem(
                                    geolocation = it,
                                    backgroundColor = if (it.id == state.selectedLocation?.id) MaterialTheme.colorScheme.primary
                                    else Color.Unspecified,
                                    onFavouriteClick = { geo ->
                                        onFavouriteClick(geo)
                                    },
                                )
                                HorizontalDivider()
                            }
                        }
                    }
                }

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun SearchLocationContentPreview() {
    MaterialTheme {
        SearchLocationContent(
            navigationType = NavigationType.BOTTOM_NAVIGATION,
            state = HomeState(
                geolocations = listOf(
                    Geolocation(
                        name = "London",
                        id = 1,
                        countryName = "United Kingdom",
                        countryCode = "GB",
                        flagUrl = "https://flagcdn.com/w320/gb.png",
                        countryId = 1,
                        latitude = 10.0,
                        longitude = 10.0,
                        timeZone = "Europe/London",
                        elevation = 35.0,
                    )
                ),
                error = AppError.ServerError(
                    httpCode = 500,
                    message = "defef"
                ),
                isLoading = true
            ),
            search = "London",
            onSearchChange = {},
            onFavouriteClick = {},
            onSubmit = {},
            onNavigateBack = {})
    }
}