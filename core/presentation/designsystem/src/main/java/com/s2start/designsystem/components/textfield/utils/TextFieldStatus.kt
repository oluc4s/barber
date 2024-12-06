package com.s2start.designsystem.components.textfield.utils

sealed class TextFieldStatus{
    object SUCCESS : TextFieldStatus()
    data class ERROR(val message: String) : TextFieldStatus()
    object DEFAULT : TextFieldStatus()
}
