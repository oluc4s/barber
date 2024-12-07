package com.s2start.auth.presentation.ui.recover

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.s2start.core.presentation.designsystem.R
import com.s2start.designsystem.components.button.ButtonAlpaca
import com.s2start.designsystem.components.screen.Screen
import com.s2start.designsystem.components.screen.rememberScreenState
import com.s2start.designsystem.components.textfield.TextFieldAlpaca
import com.s2start.designsystem.components.textfield.utils.rememberTextFieldValidation
import com.s2start.designsystem.components.textfield.utils.validations.EmailValidation
import com.s2start.designsystem.components.textfield.utils.validations.PasswordValidation
import com.s2start.designsystem.urbanistFamily
import com.s2start.ui.ObserveAsEvents
import org.koin.androidx.compose.koinViewModel

@Composable
fun RecoverScreenRoot(
    onSignInClick: () -> Unit,
    onRecoverSuccess: () -> Unit
) {
    RecoverScreen(onSignInClick,onRecoverSuccess)
}

@Composable
fun RecoverScreen(
    onSignInClick: () -> Unit,
    onRecoverSuccess: () -> Unit
){
    val screenState = rememberScreenState()
    val email = rememberTextFieldValidation(validation = EmailValidation())
    val formValid = email.isSuccess()
//    val isLoadding = if(loginState as? LoginState.Loading != null) true else false
//    val enabled = !isLoadding
//    val isError = if(loginState as? LoginState.Error != null) true else false

    Screen(
        screenState = screenState,
        containerColor = MaterialTheme.colorScheme.background
    ) {
        Column (Modifier.padding(12.dp)){
            Row {
                IconButton({
                    onSignInClick()
                }) {
                    Icon(
                        painter = painterResource(R.drawable.ic_arrow_prev_small),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Recupere Sua Conta",
                fontFamily = urbanistFamily,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                fontSize = 32.sp
            )
            Text(
                text = "Digite abaixo o e-mail cadastrado, e enviaremos instruções para redefinir sua senha.",
                fontFamily = urbanistFamily,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.padding(vertical = 12.dp),
            )
            Column {
                TextFieldAlpaca(
                    modifier = Modifier.padding(vertical = 12.dp),
                    textFieldState = email,
                    label = "Email",
                    placeholder = "Digite seu e-mail"
                )
            }
            ButtonAlpaca(
                modifier = Modifier.padding(top = 12.dp),
                text = "Enviar E-mail de Recuperação",
                enabled = formValid,
                isLoading = false
            ){

            }
            Spacer(modifier = Modifier.weight(1f))
            TextButton(onClick = {
                onSignInClick()
            }) {
                Text(
                    text = buildAnnotatedString {
                        append("Voltar para ")
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("Login")
                        }
                    },
                    fontFamily = urbanistFamily,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier
                        .padding(vertical = 12.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
fun RecoverScreenPreview(){
    RecoverScreen({},{})

}

@PreviewLightDark
@Composable
fun RecoverScreenLoadingPreview(){
    RecoverScreen({},{})
}