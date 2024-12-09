package com.s2start.auth.presentation.ui.login

import android.util.Log
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
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
import com.s2start.designsystem.AlpacaTheme
import com.s2start.designsystem.components.button.ButtonAlpaca
import com.s2start.designsystem.components.screen.Screen
import com.s2start.designsystem.components.screen.rememberScreenState
import com.s2start.designsystem.components.textfield.TextFieldAlpaca
import com.s2start.designsystem.components.textfield.utils.TextFieldStatus
import com.s2start.designsystem.components.textfield.utils.rememberTextFieldValidation
import com.s2start.designsystem.components.textfield.utils.validations.EmailValidation
import com.s2start.designsystem.components.textfield.utils.validations.PasswordValidation
import com.s2start.designsystem.urbanistFamily
import com.s2start.ui.ObserveAsEvents
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreenRoot(
    onSignUpClick: () -> Unit,
    onLoginSuccess: () -> Unit,
    onRecoverClick: () -> Unit,
    viewModel: LoginViewModel = koinViewModel(),
) {
    val loginState by viewModel.loginState.collectAsStateWithLifecycle()

    ObserveAsEvents(viewModel.events) { event -> when(event) {
            LoginNotification.LoginSuccess -> {
              onLoginSuccess()
            }
            LoginNotification.AccountNotFound -> {

            }
        }
    }
    LoginScreen(
        onSignUpClick = onSignUpClick,
        onRecoverClick = onRecoverClick,
        onEvent = viewModel::onEvent,
        loginState = loginState,
        onLoginSuccess = onLoginSuccess,
        events = viewModel.events
    )
}

@Composable
fun LoginScreen(
    onSignUpClick: () -> Unit,
    onEvent:(LoginEvent) -> Unit = {},
    onRecoverClick: () -> Unit,
    onLoginSuccess: () -> Unit,
    loginState: LoginState,
    events: Flow<LoginNotification>
){
    val screenState = rememberScreenState()
    val email = rememberTextFieldValidation(validation = EmailValidation())
    val password = rememberTextFieldValidation(isSecurity = true, validation = PasswordValidation())
    val (rememberAccount,setRememberAccount) = remember { mutableStateOf(false) }
    val formValid = email.isSuccess() && password.isSuccess()
    val isLoadding = if(loginState as? LoginState.Loading != null) true else false
    val enabled = !isLoadding
    val isError = if(loginState as? LoginState.Error != null) true else false

    LaunchedEffect(Unit) {
        events.collectLatest{
          when(it){
              LoginNotification.LoginSuccess -> {
                  onLoginSuccess()
              }
              LoginNotification.AccountNotFound -> {
                  Log.e("LaunchedEffect","esse e antes Email e senha invalido")
                  password.setStatus(TextFieldStatus.ERROR("Email e senha invalido"))
              }
          }
        }
    }

    Screen(
        screenState = screenState,
        containerColor = MaterialTheme.colorScheme.background
    ) {
        Column (Modifier.padding(12.dp)){
            Row {
                IconButton({
                    onEvent(LoginEvent.NoWantLogin)
                }) {
                    Icon(
                        painter = painterResource(R.drawable.ic_close),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Entrar",
                fontFamily = urbanistFamily,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                fontSize = 32.sp
            )
            Text(
                text = "Entre para ter uma experiência completa com tarefas",
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
                    placeholder = "Email",
                    enabled = enabled
                )
                TextFieldAlpaca(
                    modifier = Modifier.padding(vertical = 12.dp),
                    textFieldState = password,
                    label = "Senha",
                    placeholder = "Senha",
                    enabled = enabled
                )
            }
            if(isError){
                Text(
                    text = "Email e senha invalido",
                    fontFamily = urbanistFamily,
                    fontWeight = FontWeight.Normal,
                    color = Color.Red,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Checkbox(
                        rememberAccount,
                        onCheckedChange = { setRememberAccount(it) }
                    )
                    Text(
                        text = "Lembrar senha",
                        fontFamily = urbanistFamily,
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colorScheme.secondary,
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                TextButton(onClick = {
                    onRecoverClick()
                }) {
                    Text(
                        text = "Esqueceu a senha?",
                        fontFamily = urbanistFamily,
                        fontWeight = FontWeight.Normal,
                        color = Color.Blue,
                        textAlign = TextAlign.Center
                    )
                }

            }
            ButtonAlpaca(
                modifier = Modifier.padding(top = 12.dp),
                text = "Login",
                enabled = formValid,
                isLoading = isLoadding
            ){
                onEvent(
                    LoginEvent.Login(
                        email.textfieldState.value.text,
                        password.textfieldState.value.text
                    )
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            TextButton(onClick = {
                onSignUpClick()
            }) {
                Text(
                    text = buildAnnotatedString {
                        append("Voce deseja cadastrar?")
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("Cadastrar")
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

//@PreviewLightDark
//@Composable
//fun LoginScreenPreview(){
//    AlpacaTheme{
//        LoginScreen({},{},{}, loginState = LoginState.Default)
//    }
//}
//
//@PreviewLightDark
//@Composable
//fun LoginScreenLoadingPreview(){
//    AlpacaTheme {
//        LoginScreen({},{},{},{}, loginState = LoginState.Loading)
//    }
//}