package com.s2start.designsystem.components.textfield.utils

import androidx.compose.runtime.MutableState
import androidx.compose.ui.text.input.TextFieldValue

data class TextFieldValidationState(
    val textfieldState: MutableState<TextFieldValue>,
    var status: MutableState<TextFieldStatus>,
    val isSecurity:Boolean = false,
    var validation: ValidationTextField?
){
    val isValid = !(status.value is TextFieldStatus.ERROR)
    fun setTextField(textfield: TextFieldValue){
        textfieldState.value = textfield
    }
    fun setText(text:String){
        textfieldState.value = TextFieldValue(text)
    }
    fun setStatus(tfStatus: TextFieldStatus){
        status.value = tfStatus
    }
    fun isSuccess()=  status.value == TextFieldStatus.SUCCESS

    fun clear(){
        status.value = TextFieldStatus.DEFAULT
        textfieldState.value = TextFieldValue()
    }
}