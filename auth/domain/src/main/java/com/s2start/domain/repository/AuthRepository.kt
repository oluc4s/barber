package com.s2start.domain.repository

import com.s2start.domain.model.AccountModel
import com.s2start.domain.util.ModelResult


interface AuthRepository {
    suspend fun login(email:String,password:String): ModelResult<String?>
    suspend fun createAccount(account: AccountModel):ModelResult<Unit>
}