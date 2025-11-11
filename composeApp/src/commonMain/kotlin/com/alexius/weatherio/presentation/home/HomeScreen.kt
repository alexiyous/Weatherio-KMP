package com.alexius.weatherio.presentation.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alexius.weatherio.presentation.home.components.SearchLocationContent
import com.alexius.weatherio.presentation.utils.NavigationType
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigationType: NavigationType,
    homeViewModel: HomeViewModel = koinViewModel(),
) {
    val state by homeViewModel.homeState.collectAsStateWithLifecycle()
    var search by rememberSaveable { mutableStateOf("") }
    var showSearchLocation by rememberSaveable { mutableStateOf(state.selectedLocation != null) }

    SearchLocationContent(
        modifier = modifier,
        navigationType = navigationType,
        state = state,
        search = search,
        onSearchChange = { search = it },
        onFavouriteClick = {
            homeViewModel.saveFavouriteLocation(it)
            showSearchLocation = !showSearchLocation
        },
        onSubmit = { homeViewModel.fetchGeolocation(search) },
        onNavigateBack = {
            showSearchLocation = false
        }
    )
}