package com.s2start.designsystem.components.textfield.utils.validations

import android.util.Patterns
import com.s2start.designsystem.components.textfield.utils.TextFieldStatus
import com.s2start.designsystem.components.textfield.utils.TextFieldValidationState
import com.s2start.designsystem.components.textfield.utils.ValidationTextField

class EmailValidation:ValidationTextField{
    override fun validate(textFieldValidationState: TextFieldValidationState): TextFieldStatus{
        val email = textFieldValidationState.textfieldState.value.text
        return if(email.isNotEmpty() && !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            TextFieldStatus.ERROR("EMAIL INVALIDO")
        } else {
            if(email.isEmpty()) TextFieldStatus.DEFAULT else TextFieldStatus.SUCCESS
        }
    }
}