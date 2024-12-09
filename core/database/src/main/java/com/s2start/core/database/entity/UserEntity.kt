package com.s2start.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity (
    @PrimaryKey
    val uid: String,
    val email: String,
    val displayName: String,
    val phoneNumber: String,
)