package com.s2start.home.presentation.ui.barbershops.mylist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.s2start.designsystem.AlpacaTheme
import com.s2start.designsystem.components.screen.Screen
import com.s2start.home.presentation.route.HomeRoutes
import com.s2start.home.presentation.ui.components.CardResumeBarber
import com.s2start.home.presentation.ui.components.TopBar
import org.koin.androidx.compose.koinViewModel


@Composable
fun MyBarberScreenRoot(
    viewModel: MyBarberViewModel = koinViewModel(),
    onNavigate: (HomeRoutes) -> Unit = {},
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
    onNavigate: (HomeRoutes) -> Unit = {},
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
                    CardResumeBarber(it){
                        onNavigate(HomeRoutes.BarberDetailScreen(it.barberId))
                    }
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
