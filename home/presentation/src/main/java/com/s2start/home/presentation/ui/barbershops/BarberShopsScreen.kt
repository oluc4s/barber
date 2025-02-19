package com.s2start.home.presentation.ui.barbershops

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.s2start.designsystem.AlpacaTheme
import com.s2start.designsystem.components.screen.Screen
import com.s2start.designsystem.urbanistFamily
import com.s2start.designsystem.yellow
import com.s2start.domain.Routes
import com.s2start.home.presentation.ui.components.BottomBar
import com.s2start.home.presentation.ui.components.CardResumeBarber
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
        val listBarber = state.barberResumeUi.sortedBy { it.distance }

        val singapore = LatLng(-19.8022711, -43.971175)
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(singapore, 17f)
        }
        val saoPaulo = LatLng(-19.8022711, -43.971175)
        val markerState = remember { MarkerState(position = saoPaulo) }
        val list = listOf("Lista","Mapa")
        val selected = remember { mutableStateOf(list.first()) }
        Column {
            Row (horizontalArrangement = Arrangement.SpaceBetween){
                list.map { item ->
                    val isSelected = item == selected.value
                    val colors = if(isSelected) yellow else MaterialTheme.colorScheme.secondary
                    val paddingDivider = if(isSelected) 3.dp else 1.dp
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .clickable { selected.value = item },
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        Text(
                            text = item,
                            fontFamily = urbanistFamily,
                            fontWeight = if(isSelected) FontWeight.Bold else FontWeight.Light,
                            modifier = Modifier.padding(bottom = 4.dp, top = 4.dp),
                            color = colors,
                            fontSize = 17.sp
                        )
                        HorizontalDivider(
                            color = colors,
                            thickness = paddingDivider,
                            modifier = Modifier.height(3.dp),
                        )
                    }
                }
            }
            when(selected.value){
                "Lista" -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        items(listBarber) {
                            CardResumeBarber(it)
                        }
                    }
                }
                "Mapa" -> {
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
