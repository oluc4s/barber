package com.s2start.home.data.repository

import com.s2start.domain.util.ModelResult
import com.s2start.home.domain.firebase.BarberFirebase
import com.s2start.home.domain.model.BarberModel
import com.s2start.home.domain.repository.BarberRepository

class BarberRepositoryImpl(
    private val barberFirebase: BarberFirebase
):BarberRepository {
    override suspend fun createBarber(account: BarberModel): ModelResult<String> {
        return  barberFirebase.createBarber(account)
    }

    override suspend fun getListBarber(): ModelResult<List<BarberModel>> {
        return  barberFirebase.getListBarber()
    }
}