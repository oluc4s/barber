package com.s2start.home.presentation.ui.detail

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewLightDark

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
        }
    ){
        Image(
            painter = painterResource(R.drawable.im_details_image_demo),
            contentDescription = "Barbearia",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(330.dp)


        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(topEnd = 32.dp, topStart = 32.dp))
                .background(Color.White)
                .offset(y = (-30).dp)

        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text("asdsa")
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun DetailScreenPreview() {
    DetailScreen()
}