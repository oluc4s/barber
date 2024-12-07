package com.s2start.auth.presentation.ui.register

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
import com.s2start.auth.domain.model.AccountModel
import com.s2start.core.presentation.designsystem.R
import com.s2start.designsystem.components.button.ButtonAlpaca
import com.s2start.designsystem.components.screen.Screen
import com.s2start.designsystem.components.screen.rememberScreenState
import com.s2start.designsystem.components.textfield.TextFieldAlpaca
import com.s2start.designsystem.components.textfield.utils.rememberTextFieldValidation
import com.s2start.designsystem.components.textfield.utils.validations.ConfirmPasswordValidation
import com.s2start.designsystem.components.textfield.utils.validations.EmailValidation
import com.s2start.designsystem.components.textfield.utils.validations.MinValidation
import com.s2start.designsystem.components.textfield.utils.validations.PasswordValidation
import com.s2start.designsystem.urbanistFamily
import com.s2start.tarefas.ui.onboarding.newaccount.RegisterState
import org.koin.androidx.compose.koinViewModel

@Composable
fun RegisterScreenRoot(
    viewModel: RegisterViewModel = koinViewModel(),
    onSignInClick: () -> Unit,
    onSuccessfulRegistration: () -> Unit,
) {
    val newAccountState by viewModel.registerState.collectAsStateWithLifecycle()
    RegisterScreen(viewModel::onEvent, registerState = newAccountState,onSignInClick,onSuccessfulRegistration)
}


@Composable
fun RegisterScreen(
    onEvent: (RegisterEvent) -> Unit,
    registerState: RegisterState,
    onSignInClick: () -> Unit,
    onSuccessfulRegistration: () -> Unit,
) {
    val screenState = rememberScreenState()
    val name = rememberTextFieldValidation(validation = MinValidation())
    val email = rememberTextFieldValidation(validation = EmailValidation())
    val password = rememberTextFieldValidation(isSecurity = true, validation = PasswordValidation())
    val passwordConfirm = rememberTextFieldValidation(isSecurity = true, validation = ConfirmPasswordValidation(password))
    val formValid = email.isSuccess() && password.isSuccess() && name.isSuccess() && passwordConfirm.isSuccess()
    val isLoadding = if(registerState as? RegisterState.Loading != null) true else false
    val emailError = if(registerState as? RegisterState.EmailError != null) true else false
    val enabled = !isLoadding

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
                    text = "Registrar",
                    fontFamily = urbanistFamily,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 32.sp
                )
                Column {
                    TextFieldAlpaca(
                        modifier = Modifier.padding(vertical = 12.dp),
                        textFieldState = name,
                        label = "Nome",
                        placeholder = "Digite um nome",
                        enabled = enabled
                    )
                    TextFieldAlpaca(
                        modifier = Modifier.padding(vertical = 12.dp),
                        textFieldState = email,
                        label = "Email",
                        placeholder = "Digite um email",
                        enabled = enabled,
                        error = emailError
                    )
                    TextFieldAlpaca(
                        modifier = Modifier.padding(vertical = 12.dp),
                        textFieldState = password,
                        label = "Senha",
                        placeholder = "Digite uma senha",
                        enabled = enabled
                    )
                    TextFieldAlpaca(
                        modifier = Modifier.padding(vertical = 12.dp),
                        textFieldState = passwordConfirm,
                        label = "Confirmar Senha",
                        placeholder = "Senha",
                        enabled = enabled
                    )
                }
                ButtonAlpaca(
                    modifier = Modifier.padding(top = 12.dp),
                    text = "Registrar",
                    enabled = formValid,
                    isLoading = isLoadding
                ){
                    onEvent(
                        RegisterEvent.CreateAccount(
                        AccountModel(
                            name = name.textfieldState.value.text,
                            email = email.textfieldState.value.text,
                            password = password.textfieldState.value.text
                            )
                        )
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                TextButton(onClick = { onSignInClick() }) {
                    Text(
                        text = buildAnnotatedString {
                            append("Ja tenho conta ?")
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) { "Login" }
                        },
                        fontFamily = urbanistFamily,
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.padding(vertical = 12.dp).fillMaxWidth(),
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
}

@PreviewLightDark
@Composable
fun NewAccountScreenPreview(){
    RegisterScreen(onSignInClick = {}, onSuccessfulRegistration = {}, onEvent = {}, registerState = RegisterState.Default)
}

@PreviewLightDark
@Composable
fun NewAccountScreenLoadingPreview(){
    RegisterScreen(onSignInClick = {}, onSuccessfulRegistration = {}, onEvent = {}, registerState = RegisterState.Loading)
}