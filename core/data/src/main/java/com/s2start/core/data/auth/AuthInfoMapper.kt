package com.s2start.core.data.auth

import com.s2start.domain.AuthInfo

fun AuthInfo.toAuthInfoSerializable(): AuthInfoSerializable {
    return AuthInfoSerializable(
        uid = uid,
        email = email,
        displayName = displayName,
        phoneNumber = phoneNumber
    )
}

fun AuthInfoSerializable.toAuthInfo(): AuthInfo {
    return AuthInfo(
        uid = uid,
        email = email,
        displayName = displayName,
        phoneNumber = phoneNumber
    )
}