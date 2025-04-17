package com.s2start.home.presentation.ui.barbershops.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.google.accompanist.placeholder.material.placeholder
import com.s2start.designsystem.components.button.ButtonAlpaca
import com.s2start.designsystem.components.screen.Screen

@PreviewLightDark
@Composable
fun DetailScreenShimmer() {
    val scrollState = rememberScrollState()
    val imageHeight = 400.dp

    Screen(
        clearTopPadding = true
    ) {

        Box(modifier = Modifier.fillMaxSize()) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(imageHeight)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 12.dp)
                    .statusBarsPadding()
                    .zIndex(1f),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .placeholder(true)
                )
                Box(
                    modifier = Modifier
                        .height(24.dp)
                        .width(100.dp)
                        .placeholder(true)
                )
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .placeholder(true)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .padding(top = imageHeight - 30.dp)
                    .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                    .zIndex(0f)
            ) {
                Column(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .padding(top = 20.dp)
                        .padding(horizontal = 16.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .height(24.dp)
                            .fillMaxWidth(0.5f)
                            .placeholder(true)
                    )

                    repeat(2) {
                        Row(modifier = Modifier.padding(bottom = 8.dp)) {
                            Box(
                                modifier = Modifier
                                    .size(16.dp)
                                    .placeholder(true)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Box(
                                modifier = Modifier
                                    .height(16.dp)
                                    .fillMaxWidth(0.6f)
                                    .placeholder(true)
                            )
                        }
                    }

                    QuickActionsShimmer(modifier = Modifier.padding(vertical = 16.dp))
                    repeat(4) {
                        ButtonAlpaca(
                            text = "",
                            onClick = {},
                            modifier = Modifier
                                .padding(horizontal = 7.dp)
                                .padding(bottom = 12.dp)
                                .placeholder(visible = true)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun QuickActionsShimmer(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 6.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            repeat(4) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 4.dp)
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(12.dp))
                        .placeholder(true)
                )
            }
        }
        HorizontalDivider()
    }
}
