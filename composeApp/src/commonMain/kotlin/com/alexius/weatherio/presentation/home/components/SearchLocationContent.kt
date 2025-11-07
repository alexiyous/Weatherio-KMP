package com.alexius.weatherio.presentation.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.PlatformContext
import com.alexius.weatherio.domain.models.Geolocation
import com.alexius.weatherio.presentation.home.models.HomeState
import com.alexius.weatherio.presentation.utils.NavigationType

@OptIn(ExperimentalMaterial3Api::class)
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

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = padding),
        contentAlignment = Alignment.Center
    ) {
        SearchBar(
            inputField = {
                Row {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.,
                            contentDescription = "Back"
                        )
                    }
                }
            },
            expanded = expanded,
            onExpandedChange = { expanded = it }
        ) {}
    }
}