package com.alexius.weatherio.presentation.home.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.PlatformContext
import com.alexius.weatherio.domain.models.Geolocation
import com.alexius.weatherio.presentation.home.models.HomeState
import com.alexius.weatherio.presentation.utils.NavigationType

@Composable
fun SearchLocationContent(
    navigationType: NavigationType,
    state: HomeState,
    search: String,
    context: PlatformContext,
    onSearchChange: (String) -> Unit,
    onFavoriteClick: (Geolocation) -> Unit,
    onSubmit: () -> Unit,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    val padding = if (navigationType == NavigationType.PERMANENT_NAVIGATION_DRAWER) 200.dp
    else 0.dp


}