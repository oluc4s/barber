package com.s2start.home.presentation.ui.barbershops.create

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.s2start.designsystem.AlpacaTheme
import com.s2start.designsystem.MutedAzure
import com.s2start.designsystem.components.button.ButtonAlpaca
import com.s2start.designsystem.components.screen.Screen
import com.s2start.designsystem.components.textfield.TextFieldAlpaca
import com.s2start.designsystem.components.textfield.utils.getText
import com.s2start.designsystem.components.textfield.utils.rememberTextFieldValidation
import com.s2start.designsystem.components.textfield.utils.validations.EmailValidation
import com.s2start.designsystem.urbanistFamily
import com.s2start.designsystem.yellow
import com.s2start.domain.Routes
import com.s2start.home.domain.model.BarberModel
import com.s2start.home.presentation.ui.components.BottomBar
import com.s2start.home.presentation.ui.components.CardResumeBarber
import com.s2start.home.presentation.ui.components.TopBar
import org.koin.androidx.compose.koinViewModel


@Composable
fun BarberShopCreateScreenRoot(
    viewModel: BarberShopCreateViewModel = koinViewModel(),
    onNavigate: (Routes) -> Unit = {},
    onBack: () -> Unit = {}
) {
    BarberShopCreateScreen(
        state = viewModel.state,
        actions = viewModel::onAction,
        onNavigate = onNavigate,
        onBack = onBack
    )
}


@Composable
fun BarberShopCreateScreen(
    state: BarberShopCreateState,
    actions: (BarberShopCreateAction) -> Unit = {},
    onNavigate: (Routes) -> Unit = {},
    onBack: () -> Unit = {}
) {
    val name = rememberTextFieldValidation()
    val email = rememberTextFieldValidation(validation = EmailValidation())
    val street = rememberTextFieldValidation()
    val state = rememberTextFieldValidation()
    val city = rememberTextFieldValidation()
    val number = rememberTextFieldValidation()

    Screen (
        topBar = {
            TopBar(
            title = "Nova Barbearias",
            onBackButton = { onBack() }
            ) },
        containerColor = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .padding(bottom = 12.dp)
        ) {
            TextFieldAlpaca(
                modifier = Modifier.padding(vertical = 6.dp),
                textFieldState = name,
                label = "Nome da Barbearia",
                placeholder = "Digite o nome da Barbearia"
            )
            TextFieldAlpaca(
                modifier = Modifier.padding(vertical = 6.dp),
                textFieldState = email,
                label = "Email",
                placeholder = "Digite seu email"
            )
            TextFieldAlpaca(
                modifier = Modifier.padding(vertical = 6.dp),
                textFieldState = street,
                label = "Rua",
                placeholder = "Digite a rua"
            )
            TextFieldAlpaca(
                modifier = Modifier.padding(vertical = 6.dp),
                textFieldState = state,
                label = "Estado",
                placeholder = "Digite o Estado"
            )
            TextFieldAlpaca(
                modifier = Modifier.padding(vertical = 6.dp),
                textFieldState = city,
                label = "Cidade",
                placeholder = "Digite a Cidade"
            )
            TextFieldAlpaca(
                modifier = Modifier.padding(vertical = 6.dp),
                textFieldState = number,
                label = "Numero",
                placeholder = "Numero"
            )
            Spacer(modifier = Modifier.weight(1f))
            ButtonAlpaca(
                modifier = Modifier.padding(top = 12.dp),
                text = "Criar Conta"
            ){
                val address = "${street.getText}, ${state.getText}, ${city.getText},Numero ${number.getText}"

                actions.invoke(BarberShopCreateAction.CreateBarber(
                    BarberModel(
                        name = name.textfieldState.value.text,
                        services = "",
                        address = address,
                        image = 1
                    )
                ))
            }
        }
    }
}

@Composable
fun CheckBoxAlpaca(label:String,checked:Boolean,onCheckedChange: ((Boolean) -> Unit)?){
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = label,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = MutedAzure,
            modifier = Modifier.padding(start = 2.dp, bottom = 2.dp)
        )
        Spacer(modifier = Modifier.weight(1f))

        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
    }
}

//val name:String,
//val address:String,
//val image:Int,
//val services:String,


@PreviewLightDark
@Composable
private fun BarberShopsScreenPreview() {
    AlpacaTheme {
        BarberShopCreateScreen(state = BarberShopCreateState())
    }
}
