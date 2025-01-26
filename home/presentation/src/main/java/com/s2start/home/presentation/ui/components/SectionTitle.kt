package com.s2start.home.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.s2start.designsystem.urbanistFamily
import com.s2start.designsystem.yellow

@Composable
fun SectionTitle(title: String){
    Row (
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp)
    ){
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontFamily = urbanistFamily,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
            color = MaterialTheme.colorScheme.primary,
            fontSize = 16.sp
        )

        Text(
            text = "See all",
            style = MaterialTheme.typography.titleMedium,
            fontFamily = urbanistFamily,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
            color = yellow,
            fontSize = 14.sp
        )
    }
}