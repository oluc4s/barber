package com.s2start.data.firebase

import com.s2start.domain.model.AccountModel
import com.s2start.domain.util.ModelResult

interface AuthFirebase {
    suspend fun login(email:String,password:String): ModelResult<String?>
    suspend fun logOut():ModelResult<Unit>
    suspend fun createAccount(account: AccountModel):ModelResult<Unit>
}