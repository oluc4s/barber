package com.s2start.core.data.auth

import kotlinx.serialization.Serializable

@Serializable
data class AuthInfoSerializable(
    val uid: String,
    val email: String,
    val displayName: String,
    val phoneNumber: String
)
