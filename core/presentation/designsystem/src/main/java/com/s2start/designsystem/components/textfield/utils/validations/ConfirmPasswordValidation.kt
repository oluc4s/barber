package com.s2start.designsystem.components.textfield.utils.validations

import com.s2start.designsystem.components.textfield.utils.TextFieldStatus
import com.s2start.designsystem.components.textfield.utils.TextFieldValidationState
import com.s2start.designsystem.components.textfield.utils.ValidationTextField

class ConfirmPasswordValidation(val passwordConfirm:TextFieldValidationState):ValidationTextField{
    override fun validate(textFieldValidationState: TextFieldValidationState): TextFieldStatus{
        val passwordConfirmText = textFieldValidationState.textfieldState.value.text
        val passwordText = passwordConfirm.textfieldState.value.text
        if(passwordConfirmText.isEmpty()) return TextFieldStatus.DEFAULT
        return if(passwordConfirmText == passwordText)  TextFieldStatus.SUCCESS else TextFieldStatus.ERROR("Senha não confere")
    }
}