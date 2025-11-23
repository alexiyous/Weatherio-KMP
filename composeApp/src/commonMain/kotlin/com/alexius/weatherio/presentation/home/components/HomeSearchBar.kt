package com.alexius.weatherio.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
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
        modifier = modifier
            .fillMaxWidth()
            .dropShadow(
                shape = RoundedCornerShape(24.dp),
                shadow = Shadow(
                    color = Color.Black.copy(0.25f),
                    offset = DpOffset(4.dp, 4.dp),
                    radius = 10.dp,
                    spread = 0.dp,
                    blendMode = BlendMode.SrcOver
                )
            ),
        inputField = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                IconButton(onClick = {
                    onNavigateBack()
                    expanded = false
                }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }

                SearchBarDefaults.InputField(
                    modifier = Modifier.fillMaxWidth(),
                    query = search,
                    onQueryChange = onSearchChange,
                    onSearch = {
                        onSubmit()
                    },
                    expanded = expanded,
                    onExpandedChange = { expanded = it },
                    placeholder = { Text(text = stringResource(Res.string.country_search_input_field_text)) },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search Icon") },
                )
            }
        },
        expanded = expanded,
        onExpandedChange = { expanded = it },
        colors = SearchBarDefaults.colors(
            dividerColor = MaterialTheme.colorScheme.outlineVariant
        )
    ){
        content()
    }
}
