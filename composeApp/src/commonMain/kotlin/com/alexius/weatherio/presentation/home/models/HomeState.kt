package com.alexius.weatherio.presentation.home.models

import com.alexius.weatherio.domain.models.Geolocation
import com.alexius.weatherio.utils.AppError

data class HomeState(
    val isLocationSelected: Boolean = false,
    val selectedLocation: Geolocation? = null,
    val query: String = "",
    val isLoading: Boolean = false,
    val error: AppError? = null,
    val geolocations: List<Geolocation> = emptyList()
)
