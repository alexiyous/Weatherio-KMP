package com.alexius.weatherio.presentation.home.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
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
import org.jetbrains.compose.resources.stringResource
import weatherio.composeapp.generated.resources.Res
import weatherio.composeapp.generated.resources.country_search_input_field_text

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeSearchBar(
    modifier: Modifier = Modifier,
    onNavigateBack: () -> Unit,
    search: String,
    onSearchChange: (String) -> Unit,
    onSubmit: () -> Unit,
    content: @Composable () -> Unit
) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    SearchBar(
        modifier = modifier,
        inputField = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(
                    onClick = onNavigateBack) {
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
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search Icon") },
                    trailingIcon = { Icon(Icons.Default.MoreVert, contentDescription = "Setting Icon") }
                )
            }
        },
        expanded = expanded,
        onExpandedChange = { expanded = it }
    ){
        content()
    }
}