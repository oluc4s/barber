package com.s2start.home.presentation.ui.cut

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.s2start.designsystem.AlpacaTheme
import com.s2start.designsystem.components.screen.Screen
import com.s2start.home.presentation.model.mockBarberList
import com.s2start.home.presentation.route.HomeRoutes
import com.s2start.home.presentation.ui.components.BottomBar
import com.s2start.home.presentation.ui.components.CardBarber
import com.s2start.home.presentation.ui.components.TopBar
import org.koin.androidx.compose.koinViewModel


@Composable
fun CutScreenRoot(
    viewModel: CutViewModel = koinViewModel(),
    onNavigate: (HomeRoutes) -> Unit = {}
) {
    CutScreen(
        viewModel.state,
        onNavigate = onNavigate
    )
}


@Composable
fun CutScreen(
    state: CutState,
    onNavigate: (HomeRoutes) -> Unit = {}
) {
    Screen (
        topBar = { TopBar("Corte") },
        bottomBar = { BottomBar(onNavigate,selectableRoute = HomeRoutes.CutScreen) },
        containerColor = MaterialTheme.colorScheme.background
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(mockBarberList){
                CardBarber(it)
            }
        }
    }
}




@PreviewLightDark
@Composable
private fun CutScreenPreview() {
    AlpacaTheme {
        CutScreen(CutState())
    }
}
