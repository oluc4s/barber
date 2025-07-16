package com.s2start.home.presentation.ui.barbershops.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.s2start.core.presentation.designsystem.R
import com.s2start.designsystem.components.button.ButtonAlpaca
import com.s2start.designsystem.components.screen.Screen
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuItemColors
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import com.s2start.designsystem.AlpacaTheme
import com.s2start.designsystem.backgroundColorDark
import com.s2start.designsystem.urbanistFamily
import com.s2start.designsystem.yellow
import com.s2start.designsystem.yellowSecondary
import com.s2start.home.presentation.model.DropdownItem
import com.s2start.home.presentation.model.mockBarberResume
import com.s2start.home.presentation.route.HomeRoutes
import com.s2start.home.presentation.ui.components.ClipShape
import com.s2start.home.presentation.ui.components.QuickActionButton
import com.s2start.home.presentation.ui.components.SectionTitle
import com.s2start.home.presentation.ui.components.checkClipShape
import io.ktor.http.parameters
import io.ktor.http.parametersOf
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun DetailScreenRoot(
    barberId:String,
    viewModel:DetailsViewModel = koinViewModel{ parametersOf(barberId) },
    onBack: () -> Unit = {}
) {
    val state: DetailsState = viewModel.state

    when{
        state.isLoadding -> {
            DetailScreenShimmer()
        }
        else -> {
            DetailScreen(
                state = state,
                onBack = onBack
            )
        }
    }
}

@Composable
fun DetailScreen(
    state: DetailsState,
    onBack: () -> Unit = {}
) {
    val scrollState = rememberScrollState()
    val imageAlpha by remember { derivedStateOf { 1f - (scrollState.value / 1000f).coerceIn(0f, 1f) } }
    val imageHeight = 400.dp

    val menuItems = if(state.authInfo?.uid == state.barberResumeUi?.userId) {
        listOf(
            DropdownItem("Editar", onClick = {}),
            DropdownItem("Excluir", onClick = {})
        ) } else { listOf() }

    Screen(
        bottomBar = {
            ButtonAlpaca(
                text = "Agendar",
                onClick = {},
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(bottom = 12.dp)
            )
        },
        clearTopPadding = true
    ) {

        Box(modifier = Modifier.fillMaxSize()) {

            Image(
                painter = painterResource(R.drawable.im_details_image_demo),
                contentDescription = "Barbearia",
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(imageHeight)
                    .graphicsLayer { alpha = imageAlpha }
                    .align(Alignment.TopCenter)
            )

            HeaderDetails(onBack = onBack, menuItems = menuItems)

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .padding(top = imageHeight - 30.dp)
                    .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                    .background(backgroundColorDark)
                    .zIndex(0f)
            ) {
                Column(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .padding(top = 20.dp)
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = state.barberResumeUi?.name.orEmpty(),
                        style = MaterialTheme.typography.titleMedium,
                        fontFamily = urbanistFamily,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 20.sp
                    )

                    Column {
                        Row(modifier = Modifier.padding(bottom = 8.dp)) {
                            Icon(
                                painter = painterResource(R.drawable.ic_map_fill),
                                contentDescription = null,
                                tint = yellow,
                                modifier = Modifier.padding(end = 3.dp)
                            )
                            Text(
                                text = state.barberResumeUi?.address.orEmpty(),
                                color = MaterialTheme.colorScheme.secondary,
                                style = MaterialTheme.typography.bodySmall,
                            )
                        }
                        Row(modifier = Modifier.padding(bottom = 8.dp)) {
                            Icon(
                                painter = painterResource(R.drawable.ic_star),
                                contentDescription = null,
                                tint = yellow,
                                modifier = Modifier.padding(end = 3.dp)
                            )
                            Text(
                                text = "4.8 ",
                                color = yellow,
                                style = MaterialTheme.typography.bodySmall,
                            )
                            Text(
                                text = "(3,279 reviews)",
                                color = MaterialTheme.colorScheme.secondary,
                                style = MaterialTheme.typography.bodySmall,
                            )
                        }
                    }

                    QuickActionsDetails(modifier = Modifier.padding(vertical = 16.dp))
                    SectionTitle("Barbeiros")
                    Row {
                        repeat(4){
                            UserBarber()
                        }
                    }

                    Spacer(modifier = Modifier.height(500.dp))
                }
            }
        }
    }
}
@Composable
private fun HeaderDetails(
    menuItems: List<DropdownItem>? = null,
    onBack: () -> Unit = {}
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 12.dp)
            .statusBarsPadding()
            .zIndex(1f),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onBack) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.background.copy(alpha = 0.1f))
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_arrow_prev_small),
                    contentDescription = "",
                    tint = Color.White
                )
            }
        }
        Text(
            text = "Detalhes",
            style = MaterialTheme.typography.titleMedium,
            fontFamily = urbanistFamily,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            fontSize = 20.sp
        )
        if (!menuItems.isNullOrEmpty()) {
            var expanded by remember { mutableStateOf(false) }

            Box(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                IconButton(onClick = { expanded = !expanded }) {
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.background.copy(alpha = 0.1f))
                    ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_dot_menu),
                        contentDescription = "More options",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                    }
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    containerColor = MaterialTheme.colorScheme.background
                ) {
                    menuItems.map {
                        val leadingIcon: (@Composable () -> Unit)? = it.icon?.let { iconRes ->
                            {
                                Icon(
                                    painter = painterResource(id = iconRes),
                                    contentDescription = null
                                )
                            }
                        }
                        DropdownMenuItem(
                            text = { Text(text = it.label, color = MaterialTheme.colorScheme.primary) },
                            onClick = {
                                expanded = false
                                it.onClick.invoke()
                            },
                            leadingIcon = leadingIcon
                        )
                    }
                }
            }
        } else { Box{} }

    }
}
@Composable
private fun UserBarber(){
    Column (
        modifier = Modifier
            .padding(horizontal = 6.dp)
            .shadow(1.dp, RoundedCornerShape(7.dp))
            .clip(RoundedCornerShape(12.dp))
            .border(width = 1.dp, color = Color.Gray, RoundedCornerShape(12.dp))
            .widthIn(min = 70.dp)
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Image(
            painter = painterResource(id = R.drawable.im_user_mock),
            contentDescription = "Notifications",
            modifier = Modifier
                .padding(7.dp)
                .clip(CircleShape)
                .padding(horizontal = 2.dp)
                .size(50.dp),
        )
        Text(
            text = "Matheus",
            style = MaterialTheme.typography.titleMedium,
            fontFamily = urbanistFamily,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
            color = MaterialTheme.colorScheme.secondary,
            fontSize = 12.sp
        )
    }
}

@Composable
private fun QuickActionsDetails(modifier: Modifier = Modifier){
    Column (modifier = modifier){
        Row (
            modifier = Modifier
                .fillMaxWidth().padding(bottom = 6.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            QuickActionButton(R.drawable.ic_message,"Menssagem",ClipShape.RECTANGLE_BOUNDS)
            QuickActionButton(R.drawable.ic_phone_call,"Ligar",ClipShape.RECTANGLE_BOUNDS)
            QuickActionButton(R.drawable.map_pin,"Mapa",ClipShape.RECTANGLE_BOUNDS)
            QuickActionButton(R.drawable.ic_shared,"Compartilhar",ClipShape.RECTANGLE_BOUNDS)
        }
        HorizontalDivider()
    }
}



@PreviewLightDark
@Composable
private fun DetailScreenPreview() {
    AlpacaTheme{
        DetailScreen(DetailsState(barberResumeUi = mockBarberResume))
    }
}