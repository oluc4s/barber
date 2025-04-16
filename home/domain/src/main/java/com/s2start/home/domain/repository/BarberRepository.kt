package com.s2start.home.domain.repository

import com.s2start.domain.util.ModelResult
import com.s2start.home.domain.model.BarberModel

interface BarberRepository {
    suspend fun createBarber(account: BarberModel): ModelResult<String>
    suspend fun getListBarber(): ModelResult<List<BarberModel>>
    suspend fun getMyListBarber(): ModelResult<List<BarberModel>>
    suspend fun getBarber(barberId:String): ModelResult<BarberModel>
}