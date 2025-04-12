package com.s2start.home.data.firebase

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import com.s2start.domain.util.ModelResult
import com.s2start.home.domain.firebase.BarberFirebase
import com.s2start.home.domain.model.BarberModel
import kotlinx.coroutines.tasks.await

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


class FirebaseModify(val firestore: FirebaseFirestore){
    suspend fun create(key:String,objects: Any):ModelResult<String> {
        return try {
            firestore.collection(key).add(objects).await()
                .run { ModelResult.success(this.id) }
        }catch (e:Exception) {
            Log.e("Firebase", "FirebaseModify create: ${e.message}")
            e.printStackTrace()
            ModelResult.error(Throwable(e))
        }
    }

    suspend inline fun <reified T:Any>get(key:String):ModelResult<List<T>> {
        return try {
            firestore.collection(key).get().await()
                .run {
                    val list:MutableList<T> = mutableListOf()
                    for (item in this) {
                        list.add(item.toObject<T>())
                    }
                    ModelResult.success(list)
                }
        }catch (e:Exception) {
            Log.e("Firebase", "FirebaseModify get: ${e.message}")
            e.printStackTrace()
            ModelResult.error(Throwable(e))
        }
    }
}