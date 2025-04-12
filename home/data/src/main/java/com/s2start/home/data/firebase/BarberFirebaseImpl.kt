package com.s2start.home.data.firebase


import com.s2start.core.data.firebase.FirebaseModify
import com.s2start.domain.util.ModelResult
import com.s2start.home.domain.firebase.BarberFirebase
import com.s2start.home.domain.model.BarberModel

class BarberFirebaseImpl(
    private val firestoreModifier: FirebaseModify
    ):BarberFirebase {
    override suspend fun createBarber(account: BarberModel): ModelResult<String> {
        return firestoreModifier.create(key = COLLECTION_NAME,objects= account)
    }

    override suspend fun getListBarber(): ModelResult<List<BarberModel>> {
        return firestoreModifier.get<BarberModel>(key = COLLECTION_NAME)
    }

    companion object{
        const val COLLECTION_NAME = "barber"
    }
}