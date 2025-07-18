package com.s2start.home.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.s2start.core.presentation.designsystem.R
import com.s2start.designsystem.AlpacaTheme
import com.s2start.designsystem.urbanistFamily
import com.s2start.designsystem.yellow
import com.s2start.designsystem.yellowSecondary

@Composable
fun QuickActionButton(
    icon:Int,
    name:String,
    shape:ClipShape = ClipShape.CIRCLE
){
    Column (horizontalAlignment = Alignment.CenterHorizontally){
        TextButton({}) {
            Column (
                modifier = Modifier
                    .size(60.dp)
                    .checkClipShape(shape)
                    .background(yellowSecondary),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Icon(
                    painterResource(id = icon),
                    contentDescription = "Notifications",
                    modifier = Modifier.padding(14.dp).size(30.dp),
                    tint = yellow
                )
            }
        }
        Text(
            text = name,
            fontFamily = urbanistFamily,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(bottom = 4.dp, top = 4.dp),
            color = MaterialTheme.colorScheme.primary,
            fontSize = 14.sp
        )
    }
}
fun Modifier.checkClipShape(shape: ClipShape):Modifier{
    return when(shape){
        ClipShape.CIRCLE -> clip(CircleShape)
        ClipShape.RECTANGLE_BOUNDS -> clip(RoundedCornerShape(12.dp))
    }
}
enum class ClipShape{
    CIRCLE,RECTANGLE_BOUNDS
}

@PreviewLightDark
@Composable
fun QuickActionButtonPreview(){
    AlpacaTheme {
        QuickActionButton(R.drawable.ic_cut,"Corte")
        QuickActionButton(R.drawable.ic_cut,"Corte", shape = ClipShape.RECTANGLE_BOUNDS)
    }
}