package com.s2start.designsystem.components.textfield.utils.validations

import com.s2start.designsystem.components.textfield.utils.TextFieldStatus
import com.s2start.designsystem.components.textfield.utils.TextFieldValidationState
import com.s2start.designsystem.components.textfield.utils.ValidationTextField

class MinValidation(var min:Int = 2):ValidationTextField{
    override fun validate(textFieldValidationState: TextFieldValidationState): TextFieldStatus{
        val text = textFieldValidationState.textfieldState.value.text
        if(text.isEmpty()) return TextFieldStatus.DEFAULT
        return if(text.isNotEmpty() && text.count() < min)  TextFieldStatus.ERROR("Digite pelo menos $min caracteres") else  TextFieldStatus.SUCCESS
    }
}