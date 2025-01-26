package com.s2start.home.presentation.ui.profile


import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import com.s2start.designsystem.components.screen.Screen
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.s2start.core.presentation.designsystem.R
import com.s2start.designsystem.AlpacaTheme
import com.s2start.designsystem.urbanistFamily
import com.s2start.home.presentation.ui.components.BottomBar

@Composable
fun ProfileScreenRoot() {
    ProfileScreen()
}


@Composable
fun ProfileScreen() {
    Screen(
        topBar = { TopBar() },
        bottomBar = { BottomBar() },
        containerColor = MaterialTheme.colorScheme.background
    ) {

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(){
    TopAppBar(
        title = {
            Text(
                text = "Configuracao",
                style = MaterialTheme.typography.titleSmall,
                fontFamily = urbanistFamily,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary
            )
        },
        actions = {
            IconButton(onClick = {

            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(20.dp)
                )
            }
            IconButton(onClick = {

            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_dot_menu),
                    null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(20.dp)
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    )
}

@PreviewLightDark
@Composable
private fun ProfileScreenPreview() {
    AlpacaTheme {
        ProfileScreen()
    }
}