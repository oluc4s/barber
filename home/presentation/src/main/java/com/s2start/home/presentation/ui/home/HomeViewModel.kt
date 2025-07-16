package com.s2start.home.presentation.ui.home

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import com.s2start.domain.SessionStorage
import com.s2start.domain.util.ModelResult.Companion.onSuccess
import com.s2start.home.domain.usecase.GetListBarberUseCase
import com.s2start.home.domain.usecase.GetMyListBarberUseCase
import com.s2start.home.presentation.model.SessionConfiguration
import com.s2start.home.presentation.model.mockBarberResumeList
import com.s2start.home.presentation.model.toUiListModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.net.URL

class HomeViewModel(
    private val applicationScope: CoroutineScope,
    private val sessionStorage: SessionStorage,
    private val application: Context,
    private val getListBarberUseCase: GetListBarberUseCase,
    private val getMyListBarberUseCase: GetMyListBarberUseCase

): ViewModel() {
    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(application)

    private val _userLocation = MutableStateFlow<LatLng?>(null)
    val userLocation: StateFlow<LatLng?> = _userLocation.asStateFlow()



    var state by mutableStateOf(HomeState())
        private set


    init {
        viewModelScope.launch {
            sessionStorage.get()?.let {
                state = state.copy(authInfo = it)
            }

            getMyListBarberUseCase.invoke().onSuccess {
                viewModelScope.launch {
                    sessionStorage.set(SessionConfiguration(it.isNotEmpty()), SessionConfiguration::class)
                }
            }

            getListBarberUseCase.invoke().onSuccess {
                state = state.copy(barberResumeUi = it.toUiListModel().toMutableList())
            }
        }
    }

    private fun logout() {
        applicationScope.launch {
            sessionStorage.set(null)
        }
    }


    fun getUserLocation() {
        if (ContextCompat.checkSelfPermission(application, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                location?.let {
                    val userLatLng = LatLng(it.latitude, it.longitude)
                    _userLocation.value = userLatLng
                    Log.e("LOCALIZATION","${userLatLng.toString()}")
                    updateBarberDistances(userLatLng)
                }
            }
        }
    }


    private fun fetchBarberCoordinates() {
        viewModelScope.launch(Dispatchers.IO) {
            val updatedList = mockBarberResumeList.map { barber ->
                val coordinates = getCoordinatesFromAddress(barber.address)
                barber.copy(latitude = coordinates?.first ?: 0.0, longitude = coordinates?.second ?: 0.0)
            }
            state = state.copy(barberResumeUi = updatedList.toMutableList())
        }
    }
    private fun getCoordinatesFromAddress(address: String): Pair<Double, Double>? {
        val apiKey = "AIzaSyCJ6vZIi0CDim1feYIX4BaiJ2rObKQwd0g"
        val url = "https://maps.googleapis.com/maps/api/geocode/json?address=${Uri.encode(address)}&key=$apiKey"

        return try {
            val result = URL(url).readText()
            val jsonObject = JSONObject(result)
            val results = jsonObject.getJSONArray("results")

            // Verifique se o array "results" contém itens
            if (results.length() > 0) {
                val location = results.getJSONObject(0).getJSONObject("geometry").getJSONObject("location")
                val lat = location.getDouble("lat")
                val lng = location.getDouble("lng")
                Pair(lat, lng)
            } else {
                Log.e("Geocoding", "No results found for address: $address")
                null
            }
        } catch (e: Exception) {
            Log.e("Geocoding", "Error fetching coordinates: ${e.message}")
            e.printStackTrace()
            null
        }
    }


    private fun updateBarberDistances(userLatLng: LatLng) {
        val updatedList = state.barberResumeUi.map { barber ->
            val results = FloatArray(1)
            Location.distanceBetween(
                userLatLng.latitude, userLatLng.longitude,
                barber.latitude, barber.longitude,
                results
            )
            Log.e("DISTANCIA","DISTANCIA: ${(results[0] / 1000)}")
            barber.copy(distance = (results[0] / 1000).toDouble())
        }.sortedBy { it.distance }

        state = state.copy(barberResumeUi = updatedList.toMutableList())
    }
    fun onAction(action: HomeAction) {
        when(action) {
            HomeAction.OnLogoutClick -> logout()
            else -> Unit
        }
    }
}