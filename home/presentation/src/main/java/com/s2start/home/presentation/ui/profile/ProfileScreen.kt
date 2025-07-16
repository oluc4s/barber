package com.s2start.home.presentation.ui.profile


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import com.s2start.designsystem.components.screen.Screen
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.s2start.core.presentation.designsystem.R
import com.s2start.designsystem.AlpacaTheme
import com.s2start.designsystem.urbanistFamily
import com.s2start.home.presentation.route.HomeRoutes
import com.s2start.home.presentation.ui.components.BottomBar
import com.s2start.home.presentation.ui.components.TopBar
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileScreenRoot(
    viewModel: ProfileViewModel = koinViewModel(),
    onNavigate: (HomeRoutes) -> Unit = {},
    onLogoutClick: () -> Unit
) {
    ProfileScreen(
        state = viewModel.state,
        onNavigate = onNavigate,
        onLogoutClick = onLogoutClick
    )
}


@Composable
fun ProfileScreen(
    state: ProfileState,
    onNavigate: (HomeRoutes) -> Unit = {},
    onLogoutClick: () -> Unit = {}
) {
    Screen(
        topBar = { TopBar("Configuracao") },
        bottomBar = { BottomBar(onNavigate = onNavigate, selectableRoute = HomeRoutes.ProfileScreen) },
        containerColor = MaterialTheme.colorScheme.background
    ) {
        Column(modifier = Modifier.padding(horizontal = 12.dp)) {
            Column {
                Row(modifier = Modifier.padding(vertical = 12.dp)) {
                    Image(
                        painter = painterResource(R.drawable.im_user_mock),
                        contentDescription = null,
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(50.dp)
                    )
                    Column {
                        Text(
                            text = state.authInfo?.displayName.orEmpty(),
                            style = MaterialTheme.typography.titleSmall,
                            fontFamily = urbanistFamily,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(horizontal = 12.dp)
                        )
                        Text(
                            text = state.authInfo?.email.orEmpty(),
                            style = MaterialTheme.typography.titleSmall,
                            fontFamily = urbanistFamily,
                            fontWeight = FontWeight.Light,
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 14.sp,
                            modifier = Modifier.padding(horizontal = 12.dp)
                        )
                    }
                }
                HorizontalDivider()
            }
            ItemMenu(icon = R.drawable.user_pen_edit, item = "Atualização Cadastral")
            ItemMenu(icon = R.drawable.payment_svgrepo_com, item = "Método de pagamento")
            ItemMenu(
                icon = R.drawable.ic_beard,
                hide = !state.isHasBarber,
                item = "Minhas Barbearias",
                onClick = {
                    onNavigate(HomeRoutes.MyBarberScreen)
                })
            ItemMenu(icon = R.drawable.ic_graduation_hat_alt, item = "Curso de barbearia")
            HorizontalDivider()
            ItemMenu(icon = R.drawable.ic_shield, item = "Politica de Privacidade")
            ItemMenu(icon = R.drawable.ic_document_add, item = "Termos de uso")
            ItemMenu(icon = R.drawable.ic_logout, item = "Sair", onClick = {
                onLogoutClick()
            })
        }
    }
}

@Composable
fun ItemMenu(icon: Int, item: String, hide: Boolean = false, onClick: () -> Unit = {}) {
    if (!hide) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .clickable {
                    onClick()
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
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
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_next),
                null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}


@PreviewLightDark
@Composable
private fun ProfileScreenPreview() {
    AlpacaTheme {
        ProfileScreen(state = ProfileState())
    }
}