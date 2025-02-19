package com.s2start.home.presentation.ui.components

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.s2start.core.presentation.designsystem.R
import com.s2start.designsystem.yellow
import com.s2start.domain.Routes


@Preview
@Composable
fun BottomBar(
    onNavigate: (Routes) -> Unit = {},
    selectableRoute: Routes = Routes.CutScreen
){
    BottomNavigation(
        modifier = Modifier.navigationBarsPadding(),
        backgroundColor = MaterialTheme.colorScheme.background
    ) {
        menuItems.map { item ->
            val colorItem = if (selectableRoute == item.routes) yellow else MaterialTheme.colorScheme.primary

            BottomNavigationItem(
                selected = false,
                onClick = { onNavigate(item.routes) },
                icon = {
                    Icon(
                        modifier = Modifier.size(17.dp),
                        painter = painterResource(item.icon),
                        contentDescription = item.title,
                        tint = colorItem
                    )
                },
                label = {
                    Text(
                        text = item.title.orEmpty(),
                        fontWeight = FontWeight.Normal,
                        color = colorItem,
                        fontSize = 8.sp,
                        modifier = Modifier.padding(top = 6.dp)
                    )
                }
            )
        }
    }
}

private data class ItemMenu(
    val icon:Int,
    val title:String? = null,
    val isRounded:Boolean = false,
    val routes:Routes
)

private var menuItems = listOf(
    ItemMenu(R.drawable.ic_home, "Biblia", routes = Routes.HomeScreen),
    ItemMenu(R.drawable.ic_map, "Mapas", routes = Routes.BarberShopsScreen),
    ItemMenu(R.drawable.ic_menu_cut,"Corte", routes = Routes.CutScreen),
    ItemMenu(R.drawable.ic_message, "Mensagem", routes = Routes.ChatScreen),
    ItemMenu(R.drawable.ic_user, "User", routes = Routes.ProfileScreen)
)