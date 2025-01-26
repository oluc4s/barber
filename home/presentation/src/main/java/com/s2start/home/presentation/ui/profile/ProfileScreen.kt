package com.s2start.home.presentation.ui.profile


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import com.s2start.designsystem.components.screen.Screen
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
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
import com.s2start.domain.Routes
import com.s2start.home.presentation.ui.components.BottomBar

@Composable
fun ProfileScreenRoot(
    onNavigate: (Routes) -> Unit = {},
    onLogoutClick: () -> Unit
) {
    ProfileScreen(onNavigate = onNavigate,onLogoutClick = onLogoutClick)
}


@Composable
fun ProfileScreen(
    onNavigate: (Routes) -> Unit = {},
    onLogoutClick: () -> Unit = {}
    ) {
    Screen(
        topBar = { TopBar() },
        bottomBar = { BottomBar(onNavigate = onNavigate) },
        containerColor = MaterialTheme.colorScheme.background
    ) {
        Column(modifier = Modifier.padding(horizontal = 12.dp)){
            ItemMenu(icon = R.drawable.ic_graduation_hat_alt, item ="Curso de barbearia")
            ItemMenu(icon = R.drawable.ic_shield, item ="Termos de uso")
            ItemMenu(icon = R.drawable.ic_logout, item ="Sair", onClick = {
                onLogoutClick()
            })
        }
    }
}

@Composable
fun ItemMenu(icon:Int,item:String,onClick: () -> Unit = {}){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .clickable {
                onClick()
        },
        verticalAlignment = Alignment.CenterVertically
    ){
        Icon(
            painter = painterResource(id = icon),
            null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(20.dp)
        )
        Text(
            text = item,
            style = MaterialTheme.typography.titleSmall,
            fontFamily = urbanistFamily,
            fontWeight = FontWeight.Normal,
            color = MaterialTheme.colorScheme.primary,
            fontSize = 16.sp,
            modifier = Modifier.padding(horizontal = 12.dp)
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_next),
            null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(20.dp)
        )
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