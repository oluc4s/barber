package com.s2start.home.presentation.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.s2start.designsystem.components.button.ButtonAlpaca
import com.s2start.designsystem.components.screen.Screen
import com.s2start.domain.AuthInfo
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreenRoot(
    onLogoutClick: () -> Unit,
    viewModel: HomeViewModel = koinViewModel(),
) {
    val homeState by viewModel.homeState.collectAsStateWithLifecycle()

    when(val homeModel = homeState){
        is HomeState.UserData -> {
            HomeScreen(
                authInfo = homeModel.authInfo,
                onAction = { action ->
                when(action) { HomeAction.OnLogoutClick -> onLogoutClick() }
                viewModel.onAction(action)
            })
        }
        HomeState.Loading -> {

        }
    }

}


@Composable
fun HomeScreen(
    authInfo:AuthInfo,
    onAction: (HomeAction) -> Unit
) {
    Screen {
        Column(modifier = Modifier.padding(horizontal = 12.dp)) {
            Text(authInfo.email)
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
        AuthInfo("","","",""),
        onAction = {}
    )
}