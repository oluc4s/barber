package com.s2start.home.data.firebase


import com.s2start.core.data.firebase.FirebaseModify
import com.s2start.domain.SessionStorage
import com.s2start.domain.util.ModelResult
import com.s2start.home.domain.firebase.BarberFirebase
import com.s2start.home.domain.model.BarberModel

class BarberFirebaseImpl(
    private val firestoreModifier: FirebaseModify,
    val sessionStorage: SessionStorage
):BarberFirebase {
    override suspend fun createBarber(account: BarberModel): ModelResult<String> {
        val userId = sessionStorage.get()?.uid ?: return ModelResult.error(Exception("fun createBarber: Usuario nao encontrado"))
        return firestoreModifier.create(key = COLLECTION_NAME,objects=  account.copy(userId = userId))
    }

    override suspend fun getListBarber(): ModelResult<List<BarberModel>> {
        return firestoreModifier.getList<BarberModel>(key = COLLECTION_NAME)
    }

    override suspend fun getMyListBarber(): ModelResult<List<BarberModel>> {
        return firestoreModifier.getListById<BarberModel>(key = COLLECTION_NAME)
    }

    companion object{
        const val COLLECTION_NAME = "barber"
    }
}