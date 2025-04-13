package com.s2start.home.domain.firebase

import com.s2start.domain.util.ModelResult
import com.s2start.home.domain.model.BarberModel

interface BarberFirebase {
    suspend fun createBarber(account: BarberModel): ModelResult<String>
    suspend fun getListBarber(): ModelResult<List<BarberModel>>
    suspend fun getMyListBarber(): ModelResult<List<BarberModel>>
}