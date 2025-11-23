package com.alexius.weatherio.presentation.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationSearching
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alexius.weatherio.common.ui.EmptyContent
import com.alexius.weatherio.presentation.forecast.ForecastScreen
import com.alexius.weatherio.presentation.home.components.SearchLocationContent
import com.alexius.weatherio.presentation.utils.NavigationType
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import weatherio.composeapp.generated.resources.Res
import weatherio.composeapp.generated.resources.no_location_selected_message
import weatherio.composeapp.generated.resources.no_location_selected_title

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigationType: NavigationType,
    homeViewModel: HomeViewModel = koinViewModel(),
) {
    val state by homeViewModel.homeState.collectAsStateWithLifecycle()
    var search by rememberSaveable { mutableStateOf("") }
    var showSearchLocation by rememberSaveable(state.selectedLocation) { mutableStateOf(state.selectedLocation == null) }

    AnimatedVisibility(showSearchLocation,modifier = modifier) {
        Column {
            SearchLocationContent(
                modifier = modifier,
                navigationType = navigationType,
                state = state,
                search = search,
                onSearchChange = { search = it },
                onFavouriteClick = {
                    homeViewModel.saveFavouriteLocation(it)
                    showSearchLocation = false
                },
                onSubmit = { homeViewModel.fetchGeolocation(search) },
                onNavigateBack = {
                    state.selectedLocation?.let {
                        showSearchLocation = false
                    }
                }
            )

            EmptyContent(
                modifier = Modifier.fillMaxSize(),
                title = stringResource(Res.string.no_location_selected_title),
                subtitle = stringResource(Res.string.no_location_selected_message),
                image = Icons.Default.LocationSearching,
                navigationType = navigationType,
            )
        }

    }

    AnimatedVisibility(!showSearchLocation, modifier = modifier.fillMaxSize()) {
        state.selectedLocation?.let {
            ForecastScreen(
                modifier = modifier.statusBarsPadding(),
                onSearchClick = {
                    showSearchLocation = !showSearchLocation
                },
                navigationType = navigationType,
            )
        }
    }
}