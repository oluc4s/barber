package com.s2start.designsystem.components.textfield

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.s2start.core.presentation.designsystem.R
import com.s2start.designsystem.Blue
import com.s2start.designsystem.MutedAzure
import com.s2start.designsystem.SlatePurple
import com.s2start.designsystem.components.textfield.utils.TextFieldStatus
import com.s2start.designsystem.components.textfield.utils.TextFieldValidationState
import com.s2start.designsystem.components.textfield.utils.rememberTextFieldValidation
import com.s2start.designsystem.components.textfield.utils.validations.EmailValidation

@Composable
fun TextFieldAlpaca(
    modifier: Modifier = Modifier,
    textFieldState: TextFieldValidationState,
    label:String? = null,
    placeholder:String? = null,
    enabled:Boolean = true,
    error:Boolean = false
) {
    val isShowMarge = if(textFieldState.textfieldState.value.text.isNotEmpty()) 1.dp else 0.dp

    val colorMargin = textFieldState.status.value.takeIf { it is TextFieldStatus.ERROR }
        ?.let { if(textFieldState.textfieldState.value.text.isNotEmpty()) Color.Red else Color.Transparent }
        ?: if(textFieldState.textfieldState.value.text.isNotEmpty()) Blue else Color.Transparent

    val isEyeSecure = remember { mutableStateOf(true) }

    LaunchedEffect(textFieldState.textfieldState.value.text) {
        textFieldState.validation?.let {
            it.validate(textFieldState).let {
                textFieldState.setStatus(it)
            }
        }
    }

    Column (modifier){
        if(label != null){
            Text(
                text = label,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = MutedAzure,
                modifier = Modifier.padding(start = 2.dp, bottom = 2.dp)
            )
        }
        androidx.compose.material3.TextField(
            textFieldState.textfieldState.value,
            onValueChange = {
                textFieldState.setTextField(it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .border(isShowMarge, if(error) Color.Red else colorMargin, RoundedCornerShape(12.dp))
            ,
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainer,
                focusedContainerColor = MaterialTheme.colorScheme.surfaceContainer,
                disabledContainerColor = MaterialTheme.colorScheme.surfaceContainer,
                errorContainerColor = MaterialTheme.colorScheme.surfaceContainer,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            placeholder = {
                placeholder?.let {
                    Text(
                        text = placeholder,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            },
            visualTransformation = if(textFieldState.isSecurity && isEyeSecure.value) PasswordVisualTransformation() else  VisualTransformation.None,
            textStyle = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary,
                letterSpacing = 1.sp
            ),
            trailingIcon = {
                if(textFieldState.isSecurity){
                    IconButton(onClick = {
                        isEyeSecure.value = !isEyeSecure.value
                    }) {
                        Icon(
                            painter = painterResource(if(isEyeSecure.value) R.drawable.ic_eye_closed else R.drawable.ic_eye ),
                            null,
                            tint = SlatePurple,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            },
            enabled = enabled
        )
        Row (horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()){

            (textFieldState.status.value as? TextFieldStatus.ERROR)?.let { errorStatus ->
                if(textFieldState.textfieldState.value.text.isNotEmpty()) {
                    Text(
                        text = errorStatus.message,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Red,
                        modifier = Modifier.padding(start = 2.dp, bottom = 2.dp)
                    )
                }
            }
        }
    }
}


@Preview
@Composable
private fun TextFieldPreview() {
    val email = rememberTextFieldValidation(validation = EmailValidation())
    Column{
        TextFieldAlpaca(
            modifier = Modifier.padding(vertical = 12.dp),
            textFieldState = email,
            label = "Email",
            placeholder = "Digite um email",
            error = true
        )
        TextFieldAlpaca(
            modifier = Modifier.padding(vertical = 12.dp),
            textFieldState = email,
            label = "Email",
            placeholder = "Digite um email",
            error = false
        )
    }
}