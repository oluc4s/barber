package com.s2start.home.presentation.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.s2start.core.presentation.designsystem.R
import com.s2start.designsystem.components.screen.Screen
import org.koin.androidx.compose.koinViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.sp
import com.s2start.designsystem.AlpacaTheme
import com.s2start.designsystem.backgroundColorDark
import com.s2start.designsystem.urbanistFamily
import com.s2start.home.presentation.model.mockBarber
import com.s2start.home.presentation.route.HomeRoutes
import com.s2start.home.presentation.ui.components.BottomBar
import com.s2start.home.presentation.ui.components.CardBarber
import com.s2start.home.presentation.ui.components.CardResumeBarber
import com.s2start.home.presentation.ui.components.QuickActionButton
import com.s2start.home.presentation.ui.components.SectionTitle
import kotlinx.coroutines.Job

@Composable
fun HomeScreenRoot(
    viewModel: HomeViewModel = koinViewModel(),
    onNavigate: (HomeRoutes) -> Unit = {}
) {
    HomeScreen(
        viewModel.state,
        onNavigate = onNavigate
    )
}


@Composable
fun HomeScreen(
    state: HomeState,
    onNavigate: (HomeRoutes) -> Unit = {}
) {
    val listState = rememberLazyListState()
    var scrollDirection by remember { mutableStateOf("") }
    var previousFirstVisibleIndex by remember { mutableStateOf(0) }
    var showMenuTop by remember { mutableStateOf(true) }
    var previousScrollOffset by remember { mutableStateOf(0) }
    var job by remember { mutableStateOf<Job?>(null) }
    val search = remember { mutableStateOf<String>("") }
    val isSearching = search.value.isNotEmpty()
    val listBarber = state.barberResumeUi.filter {
        it.name.contains(search.value, ignoreCase = true)
    }.sortedBy { it.distance }

    Screen(
        topBar = { if(showMenuTop){ TopBar(state = state){ search.value = it} } },
        bottomBar = { BottomBar(onNavigate,HomeRoutes.HomeScreen) },
        containerColor = MaterialTheme.colorScheme.background
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = listState,
            ) {
            if(!isSearching){
                item { QuickActions() }
                item { SectionTitle("Meus Agendamentos"){ onNavigate(HomeRoutes.CutScreen) } }
                item { CardBarber(mockBarber) }
                item { SectionTitle("Barbearias Recomendadas"){ onNavigate(HomeRoutes.BarberShopsScreen) } }
            }else{
                item { SectionTitle("Barbearias"){ onNavigate(HomeRoutes.BarberShopsScreen) } }
            }

            items(listBarber,key = { it.barberId }){
                CardResumeBarber(it, onClickCard = {
                        onNavigate(HomeRoutes.BarberDetailScreen(it.barberId))
                    }
                )
            }
        }
    }
}

@Composable
fun QuickActions(){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp, horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ){
        QuickActionButton(R.drawable.ic_cut,"Corte")
        QuickActionButton(R.drawable.ic_brush,"Pintar")
        QuickActionButton(R.drawable.ic_beard,"Barba")
        QuickActionButton(R.drawable.ic_grid_add,"Mais")
    }
}



@Composable
private fun TopBar(state: HomeState,search: (String) -> Unit = {}) {
    val (search,setSearch) = remember { mutableStateOf(TextFieldValue()) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
            .background(backgroundColorDark)
            .padding(16.dp)
            .displayCutoutPadding()
    ) {
        Row (verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 20.dp)){
            Column {
                Text(
                    text = "Ola, ${state.authInfo?.displayName.orEmpty()}",
                    style = MaterialTheme.typography.titleMedium,
                    fontFamily = urbanistFamily,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
                    color = Color.White,
                    fontSize = 20.sp
                )
                Row (
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(
                        painter = painterResource(R.drawable.map_pin),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                    Text(
                        text = "Jardim Europa, BH",
                        fontFamily = urbanistFamily,
                        fontWeight = FontWeight.Normal,
                        color = Color.White
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            IconButton(
                onClick = { /* TODO */ },
                modifier = Modifier.size(36.dp).clip(CircleShape).background(Color(0xFF1A1B25)),
                colors = IconButtonDefaults.iconButtonColors(contentColor = Color.White)
            ) {
                Icon(painterResource(id = R.drawable.ic_bell), contentDescription = "Notifications",modifier = Modifier.size(20.dp))
            }
        }

        TextField(
            value = search,
            onValueChange = {
                setSearch(it)
                search(it.text)
            },
            placeholder = {
                Text(
                    text = "Pesquisar",
                    fontFamily = urbanistFamily,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding( bottom = 4.dp),
                    color = Color.White
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            colors = TextFieldDefaults.colors(
                disabledTextColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                focusedContainerColor = Color(0xFF1A1B25),
                unfocusedContainerColor = Color(0xFF1A1B25)
            ),
            shape = RoundedCornerShape(16.dp),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    null,
                    tint = Color.Gray,
                    modifier = Modifier.size(25.dp)
                )
            },
            trailingIcon = {
                Row (verticalAlignment = Alignment.CenterVertically){
                    VerticalDivider(
                        modifier = Modifier.height(26.dp).padding(end = 4.dp),
                        thickness = 0.5.dp
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_adjustments),
                        null,
                        tint = Color.Gray,
                        modifier = Modifier.size(25.dp)
                    )
                }
            }
        )
    }
}


@PreviewLightDark
@Composable
private fun HomeScreenPreview() {
    AlpacaTheme {
        HomeScreen(HomeState() )
    }
}


@Preview
@Composable
private fun HeaderPreview() {
    TopBar(state = HomeState() )
}