package com.alexius.weatherio.presentation.forecast

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alexius.weatherio.common.ui.ErrorEmptyScreen
import com.alexius.weatherio.common.ui.Loader
import com.alexius.weatherio.common.ui.toErrorEmptyState
import com.alexius.weatherio.presentation.utils.NavigationType
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ForecastScreen(
    modifier: Modifier = Modifier,
    forecastViewModel: ForecastViewModel = koinViewModel(),
    navigationType: NavigationType,
    onSearchClick: () -> Unit,
) {
    val state by forecastViewModel.forecastState.collectAsStateWithLifecycle()

    Box(
        modifier = modifier.fillMaxSize().padding(16.dp),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AnimatedContent(
                targetState = when {
                    state.isLoading -> "LOADING"
                    state.error != null -> "ERROR"
                    else -> "DATA"
                }
            ) {
                when (it) {
                    "LOADING" -> {
                        Loader(modifier = Modifier.fillMaxSize())
                    }
                    "ERROR" -> {
                        ErrorEmptyScreen(
                            modifier = Modifier.fillMaxSize().navigationBarsPadding(),
                            errorEmptyState = state.error!!.toErrorEmptyState(),
                            navigationType = navigationType,
                            buttonAction = { onSearchClick() }
                        )
                    }
                    "DATA" -> {
                        LazyColumn(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {

                        }
                    }
                }
            }
        }
    }
}