package com.s2start.auth.domain.usecase

import com.s2start.auth.domain.model.AccountModel
import com.s2start.auth.domain.repository.AuthRepository
import com.s2start.domain.util.ModelResult

class CreateAccountUseCase (
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(account: AccountModel): ModelResult<Unit> = authRepository.createAccount(account)
}
