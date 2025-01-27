package com.s2start.home.presentation.ui.chat

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
import com.s2start.designsystem.components.screen.Screen
import com.s2start.designsystem.urbanistFamily
import com.s2start.domain.Routes
import com.s2start.home.presentation.ui.components.BottomBar
import com.s2start.home.presentation.ui.components.TopBar
import com.s2start.home.presentation.ui.home.HomeViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun ChatScreenRoot(
    viewModel: ChatViewModel = koinViewModel(),
    onNavigate: (Routes) -> Unit = {}
) {
    ChatScreen(
        viewModel.state,
        onNavigate = onNavigate
    )
}


@Composable
fun ChatScreen(
    state: ChatState,
    onNavigate: (Routes) -> Unit = {}
) {
    val item = ChatData("Marcones","Vou ir terca feira as 19:00",R.drawable.im_user_mock,"19:00")
    Screen (
        topBar = { TopBar("Chat") },
        bottomBar = { BottomBar(onNavigate) },
        containerColor = MaterialTheme.colorScheme.background
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(10){
                ChatItem(item)
            }
        }
    }
}


data class ChatData(
    val name:String,
    val description:String,
    val image:Int,
    val time:String
)

@Composable
private fun ChatItem(chatData:ChatData){
    Column (modifier = Modifier.padding(12.dp)){
        Row {
            Image(
                painter = painterResource(chatData.image),
                contentDescription = null,
                modifier = Modifier.clip(CircleShape).size(50.dp),
            )
            Column (modifier = Modifier.padding(horizontal = 12.dp)){
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = chatData.name,
                        fontFamily = urbanistFamily,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 4.dp, top = 4.dp),
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 17.sp
                    )
                    Text(
                        text = chatData.time,
                        fontFamily = urbanistFamily,
                        fontWeight = FontWeight.Light,
                        modifier = Modifier.padding(bottom = 4.dp, top = 4.dp),
                        color = MaterialTheme.colorScheme.secondary,
                        fontSize = 12.sp
                    )
                }

                Text(
                    text = chatData.description,
                    fontFamily = urbanistFamily,
                    fontWeight = FontWeight.Light,
                    modifier = Modifier.padding(bottom = 4.dp, top = 4.dp),
                    color = MaterialTheme.colorScheme.secondary,
                    fontSize = 14.sp
                )
                HorizontalDivider()
            }
        }


    }
}


@PreviewLightDark
@Composable
private fun HomeScreenPreview() {
    AlpacaTheme {
        ChatScreen(ChatState())
    }
}
