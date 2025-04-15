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
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.sp
import com.s2start.designsystem.AlpacaTheme
import com.s2start.designsystem.backgroundColorDark
import com.s2start.designsystem.urbanistFamily
import com.s2start.designsystem.yellow
import com.s2start.home.presentation.ui.home.QuickActions

@Composable
fun DetailScreenRoot() {
    DetailScreen()
}

@Composable
fun DetailScreen() {
    Screen (
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
    ){
        Box{
            Image(
                painter = painterResource(R.drawable.im_details_image_demo),
                contentDescription = "Barbearia",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 330.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 12.dp)
                    .statusBarsPadding()
                ,
                    horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                IconButton(
                    onClick = {},
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.background.copy(alpha = 0.1f)),
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_arrow_prev_small),
                        contentDescription = "",
                        tint = Color.White
                    )
                }
                Text(
                    text = "Detalhes",
                    style = MaterialTheme.typography.titleMedium,
                    fontFamily = urbanistFamily,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
                    color = Color.White,
                    fontSize = 20.sp
                )
                IconButton(
                    onClick = {},
                    modifier = Modifier,
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_dot_menu),
                        contentDescription = "",
                        tint = Color.White
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize().offset(y = (-30).dp)
                .clip(RoundedCornerShape(topEnd = 32.dp, topStart = 32.dp))
                .background(backgroundColorDark)
        ){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(top = 20.dp)
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "Barbearia demo",
                    style = MaterialTheme.typography.titleMedium,
                    fontFamily = urbanistFamily,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 20.sp
                )
                Column{
                    Row(modifier = Modifier.padding(bottom = 8.dp)){
                        Icon(
                            painter = painterResource(R.drawable.ic_map_fill),
                            contentDescription = null,
                            tint = yellow,
                            modifier = Modifier.padding(end = 3.dp)
                        )
                        Text(
                            text = "123 km",
                            color = MaterialTheme.colorScheme.secondary,
                            style = MaterialTheme.typography.bodySmall,
                        )
                    }
                    Row(modifier = Modifier.padding(bottom = 8.dp)){
                        Icon(
                            painter = painterResource(R.drawable.ic_star),
                            contentDescription = null,
                            tint = yellow,
                            modifier = Modifier.padding(end = 3.dp)
                        )
                        Text(
                            text = "4.8",
                            color = yellow,
                            style = MaterialTheme.typography.bodySmall,
                        )
                    }
                }

                QuickActions()
            }
        }

    }
}

@PreviewLightDark
@Composable
private fun DetailScreenPreview() {
    AlpacaTheme{
        DetailScreen()
    }
}