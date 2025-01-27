package com.s2start.home.presentation.ui.cut

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.s2start.core.presentation.designsystem.R
import com.s2start.designsystem.AlpacaTheme
import com.s2start.designsystem.components.screen.Screen
import com.s2start.designsystem.urbanistFamily
import com.s2start.domain.Routes
import com.s2start.home.presentation.ui.components.BottomBar
import com.s2start.home.presentation.ui.components.CardBarber
import com.s2start.home.presentation.ui.components.TopBar
import org.koin.androidx.compose.koinViewModel


@Composable
fun CutScreenRoot(
    viewModel: CutViewModel = koinViewModel(),
    onNavigate: (Routes) -> Unit = {}
) {
    CutScreen(
        viewModel.state,
        onNavigate = onNavigate
    )
}


@Composable
fun CutScreen(
    state: CutState,
    onNavigate: (Routes) -> Unit = {}
) {
    Screen (
        topBar = { TopBar("Corte") },
        bottomBar = { BottomBar(onNavigate) },
        containerColor = MaterialTheme.colorScheme.background
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(10){
                CardBarber()
            }
        }
    }
}




@PreviewLightDark
@Composable
private fun CutScreenPreview() {
    AlpacaTheme {
        CutScreen(CutState())
    }
}
