package com.s2start.designsystem.components.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BottomSheetLayout(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
){
    Column(modifier = Modifier.padding(top = 12.dp).displayCutoutPadding()) {
        Box(modifier = modifier
            .fillMaxWidth()
            .padding(top = 12.dp),
            contentAlignment = Alignment.Center
        ){
            Box(modifier = Modifier
                .width(38.dp)
                .height(4.dp)
                .clip(
                    RoundedCornerShape(2.dp)
                )
                .background(Color.Gray))
        }
        content()
    }
}