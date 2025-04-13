package com.s2start.home.presentation.ui.barbershops.mylist

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
fun MyBarberScreenRoot(
    viewModel: MyBarberViewModel = koinViewModel(),
    onNavigate: (Routes) -> Unit = {},
    onBack: () -> Unit = {}
) {
    MyBarberScreen(
        state = viewModel.state,
        onNavigate = onNavigate,
        onBack = onBack
    )
}


@Composable
fun MyBarberScreen(
    state: MyBarberState,
    onNavigate: (Routes) -> Unit = {},
    onBack: () -> Unit = {}
) {
    val listBarber = state.barberResumeUi.sortedBy { it.distance }
    Screen (
        topBar = { TopBar(
            "Minhas Barbearias",
            onBackButton = { onBack() }
        ) },
        containerColor = MaterialTheme.colorScheme.background
    ) {

        Column {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
            ) {
                items(listBarber) {
                    CardResumeBarber(it)
                }
            }
        }

    }
}


@PreviewLightDark
@Composable
private fun MyBarberScreenPreview() {
    AlpacaTheme {
        MyBarberScreen(MyBarberState())
    }
}
