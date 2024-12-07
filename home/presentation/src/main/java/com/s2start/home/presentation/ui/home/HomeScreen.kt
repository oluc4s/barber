package com.s2start.home.presentation.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.s2start.designsystem.components.button.ButtonAlpaca
import com.s2start.designsystem.components.screen.Screen
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreenRoot(
    onLogoutClick: () -> Unit,
    viewModel: HomeViewModel = koinViewModel(),
) {
    HomeScreen( onAction = { action ->
        when(action) {
            HomeAction.OnLogoutClick -> onLogoutClick()
            else -> Unit
        }
        viewModel.onAction(action)
    })
}


@Composable
fun HomeScreen(
    onAction: (HomeAction) -> Unit
) {
    Screen {
        Column(modifier = Modifier.padding(horizontal = 12.dp)) {
            Text("")
            ButtonAlpaca(text = "Logout") {
                onAction(HomeAction.OnLogoutClick)
            }
        }
    }
}


@Preview
@Composable
private fun RunOverviewScreenPreview() {
    HomeScreen(
        onAction = {}
    )
}