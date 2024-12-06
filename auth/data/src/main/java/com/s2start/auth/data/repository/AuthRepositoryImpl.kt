package com.s2start.auth.data.repository
import com.s2start.data.firebase.AuthFirebase
import com.s2start.auth.domain.model.AccountModel
import com.s2start.auth.domain.repository.AuthRepository
import com.s2start.domain.util.ModelResult

class AuthRepositoryImpl(
    private val authFirebase: AuthFirebase
): AuthRepository {
    override suspend fun login(email:String,password:String): ModelResult<String?> {
        return  authFirebase.login(email,password)
    }
    override suspend fun createAccount(account: AccountModel): ModelResult<Unit> {
        return authFirebase.createAccount(account)
    }
}