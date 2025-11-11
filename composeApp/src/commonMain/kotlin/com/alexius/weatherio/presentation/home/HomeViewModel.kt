package com.alexius.weatherio.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexius.weatherio.domain.models.Geolocation
import com.alexius.weatherio.presentation.home.models.HomeState
import com.alexius.weatherio.repository.GeolocationRepository
import com.alexius.weatherio.common.utils.toAppError
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val geolocationRepository: GeolocationRepository
) : ViewModel() {
    private val _homesState = MutableStateFlow(HomeState())
    val homeState = _homesState.asStateFlow()

    init {
        getGeolocation()
    }

    fun getGeolocation() {
        viewModelScope.launch {
            geolocationRepository.geolocation
                .collect {
                    _homesState.update { state ->
                        state.copy(
                            selectedLocation = it,
                            isLocationSelected = true
                        )
                    }
            }
        }
    }

    fun saveFavouriteLocation() {
        viewModelScope.launch {
            homeState.value.selectedLocation?.let {
                geolocationRepository.upsertGeolocation(it)
            }
        }
    }

    fun setSelectedLocation(geolocation: Geolocation) {
        _homesState.update {
            it.copy(
                selectedLocation = geolocation.copy(id = 1),
                isLocationSelected = true
            )
        }
    }

    fun fetchGeolocation(query: String) {
        viewModelScope.launch {
            _homesState.update { it.copy(isLoading = true) }
            geolocationRepository.fetchGeolocation(query).fold(
                onSuccess = { result ->
                    _homesState.update {
                        it.copy(
                            isLoading = false,
                            error = null,
                            geolocations = result
                        )
                    }
                },
                onFailure = { error ->
                    _homesState.update {
                        it.copy(
                            isLoading = false,
                            error = error.toAppError(),
                            geolocations = emptyList()
                        )
                    }

                }
            )
        }
    }
}