package com.s2start.designsystem.components.textfield.utils.validations

import com.s2start.designsystem.components.textfield.utils.TextFieldStatus
import com.s2start.designsystem.components.textfield.utils.TextFieldValidationState
import com.s2start.designsystem.components.textfield.utils.ValidationTextField

class PasswordValidation : ValidationTextField {

    override fun validate(textFieldValidationState: TextFieldValidationState): TextFieldStatus {
        val password = textFieldValidationState.textfieldState.value.text

        return when {
            !hasDigit(password) -> TextFieldStatus.ERROR("A senha deve conter pelo menos um número")
            !hasLetter(password) -> TextFieldStatus.ERROR("A senha deve conter pelo menos uma letra")
            !hasUpperCase(password) -> TextFieldStatus.ERROR("A senha deve conter pelo menos uma letra maiúscula")
            !hasSpecialCharacter(password) -> TextFieldStatus.ERROR("A senha deve conter pelo menos um símbolo especial")
            else -> TextFieldStatus.SUCCESS
        }
    }

    private fun hasDigit(password: String) = password.any { it.isDigit() }
    private fun hasLetter(password: String) = password.any { it.isLetter() }
    private fun hasUpperCase(password: String) = password.any { it.isUpperCase() }
    private fun hasSpecialCharacter(password: String) = Regex("[^a-zA-Z0-9]").containsMatchIn(password)
}