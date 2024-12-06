package com.s2start.presentation.ui.login

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreenRoot(
    onSignUpClick: () -> Unit,
    viewModel: LoginViewModel = koinViewModel(),
) {
    val loginState by viewModel.loginState.collectAsStateWithLifecycle()
    LoginScreen(onSignUpClick,viewModel::onEvent, loginState = loginState)
}

@Composable
fun LoginScreen(
    onSignUpClick: () -> Unit,
    onEvent:(LoginEvent) -> Unit = {},
    loginState: LoginState
){
    val screenState = rememberScreenState()
    val email = rememberTextFieldValidation(validation = EmailValidation())
    val password = rememberTextFieldValidation(isSecurity = true, validation = PasswordValidation())
    val formValid = email.isSuccess() && password.isSuccess()
    val isLoadding = if(loginState as? LoginState.Loading != null) true else false
    val enabled = !isLoadding
    val isError = if(loginState as? LoginState.Error != null) true else false

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
            ButtonAlpaca(
                modifier = Modifier.padding(top = 12.dp),
                text = "Login",
                enabled = formValid,
                isLoading = isLoadding
            ){
                onEvent(LoginEvent.Login(
                    email.textfieldState.value.text,
                    password.textfieldState.value.text
                ))
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

@PreviewLightDark
@Composable
fun LoginScreenPreview(){
    LoginScreen({},{}, loginState = LoginState.Default)

}

@PreviewLightDark
@Composable
fun LoginScreenLoadingPreview(){
    LoginScreen({},{}, loginState = LoginState.Loading)
}