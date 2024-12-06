package com.s2start.designsystem.components.textfield.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.TextFieldValue

@Composable
fun rememberTextFieldValidation(
    textfieldState: MutableState<TextFieldValue> = remember { mutableStateOf(TextFieldValue()) },
    status: MutableState<TextFieldStatus> = mutableStateOf(TextFieldStatus.DEFAULT),
    isSecurity:Boolean = false,
    validation: ValidationTextField? = null
): TextFieldValidationState = remember { TextFieldValidationState(textfieldState,status,isSecurity,validation)  }
