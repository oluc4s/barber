package com.s2start.auth.domain.usecase

import com.s2start.auth.domain.repository.AuthRepository
import com.s2start.domain.util.ModelResult

class LoginUseCase (
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(email:String,password:String): ModelResult<String?> = authRepository.login(email,password)
}
