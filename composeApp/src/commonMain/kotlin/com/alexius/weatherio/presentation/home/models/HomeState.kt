package com.alexius.weatherio.presentation.home.models

import com.alexius.weatherio.domain.models.Geolocation
import com.alexius.weatherio.common.utils.AppError

data class HomeState(
    val selectedLocation: Geolocation? = null,
    val isLoading: Boolean = false,
    val error: AppError? = null,
    val geolocations: List<Geolocation> = emptyList()
)
