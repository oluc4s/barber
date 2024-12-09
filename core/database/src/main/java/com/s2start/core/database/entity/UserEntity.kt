package com.s2start.core.database.entity

import androidx.room.Entity

@Entity
data class UserEntity (
    val uid: String,
    val email: String,
    val displayName: String,
    val phoneNumber: String
)