package com.s2start.home.presentation.ui.barbershops

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.s2start.designsystem.AlpacaTheme
import com.s2start.designsystem.components.screen.Screen
import com.s2start.domain.Routes
import com.s2start.home.presentation.ui.components.BottomBar
import com.s2start.home.presentation.ui.components.TopBar
import org.koin.androidx.compose.koinViewModel


@Composable
fun BarberShopsScreenRoot(
    viewModel: BarberShopsViewModel = koinViewModel(),
    onNavigate: (Routes) -> Unit = {}
) {
    BarberShopsScreen(
        viewModel.state,
        onNavigate = onNavigate
    )
}


@Composable
fun BarberShopsScreen(
    state: BarberShopsState,
    onNavigate: (Routes) -> Unit = {}
) {
    val context = LocalContext.current
    Screen (
        topBar = { TopBar("Barbearias") },
        bottomBar = { BottomBar(onNavigate,selectableRoute = Routes.BarberShopsScreen) },
        containerColor = MaterialTheme.colorScheme.background
    ) {
        val singapore = LatLng(-19.8022711, -43.971175)
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(singapore, 17f)
        }
        val saoPaulo = LatLng(-19.8022711, -43.971175)
        val markerState = remember { MarkerState(position = saoPaulo) }

        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState
        ){
            Marker(
                state = markerState,
                title = "São Paulo",
                snippet = "Bem-vindo a São Paulo!"
            )
        }
    }
}




@PreviewLightDark
@Composable
private fun BarberShopsScreenPreview() {
    AlpacaTheme {
        BarberShopsScreen(BarberShopsState())
    }
}
