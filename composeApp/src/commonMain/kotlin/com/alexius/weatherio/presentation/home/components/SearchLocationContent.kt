package com.alexius.weatherio.presentation.home.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
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
import weatherio.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import weatherio.composeapp.generated.resources.country_search_input_field_text

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
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }

                    SearchBarDefaults.InputField(
                        query = search,
                        onQueryChange = onSearchChange,
                        onSearch = {
                            onSubmit()
                        },
                        expanded = expanded,
                        onExpandedChange = { expanded = it },
                        placeholder = { Text(text = stringResource(Res.string.country_search_input_field_text)) },
                        leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search Button") },
                        trailingIcon = { Icon(Icons.Default.MoreVert, contentDescription = "Setting Button") }
                    )
                }
            },
            expanded = expanded,
            onExpandedChange = { expanded = it }
        ) {
            Card {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {

                }
            }
        }
    }
}