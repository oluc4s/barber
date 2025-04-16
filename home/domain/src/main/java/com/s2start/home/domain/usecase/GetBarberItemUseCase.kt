package com.s2start.home.domain.usecase

import com.s2start.domain.util.ModelResult
import com.s2start.home.domain.model.BarberModel
import com.s2start.home.domain.repository.BarberRepository

class GetBarberItemUseCase (
    private val barberRepository: BarberRepository
) {
    suspend operator fun invoke(barberId:String): ModelResult<BarberModel> = barberRepository.getBarber(barberId)
}
