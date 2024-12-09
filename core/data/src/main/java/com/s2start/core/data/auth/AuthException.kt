package com.s2start.core.data.auth


class CredentialsException (
    message: String,
    val errorCode: Int? = null
) : Exception(message) {
    override fun toString(): String {
        return "CredentialsException(errorCode=$errorCode, message=${message ?: "No message"})"
    }
}

class InvalidUserException  (
    message: String,
    val errorCode: Int? = null
) : Exception(message) {
    override fun toString(): String {
        return "InvalidUserException(errorCode=$errorCode, message=${message ?: "No message"})"
    }
}