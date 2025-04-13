package com.s2start.core.data.firebase

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import com.s2start.domain.SessionStorage
import com.s2start.domain.util.ModelResult
import kotlinx.coroutines.tasks.await
import timber.log.Timber

class FirebaseModify(
    val firestore: FirebaseFirestore,
    val sessionStorage: SessionStorage
){
    suspend fun create(key:String,objects: Any): ModelResult<String> {
        return try {
            firestore.collection(key).add(objects).await()
                .run { ModelResult.success(this.id) }
        }catch (e:Exception) {
            Log.e("Firebase", "FirebaseModify create: ${e.message}")
            e.printStackTrace()
            ModelResult.error(Throwable(e))
        }
    }

    suspend inline fun <reified T:Any>get(key:String): ModelResult<List<T>> {
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
            Timber.tag("Firebase").e("FirebaseModify get: ${e.message}")
            e.printStackTrace()
            ModelResult.error(Throwable(e))
        }
    }


    suspend inline fun <reified T:Any>getList(key:String): ModelResult<List<T>> {
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
            Timber.tag("Firebase").e("FirebaseModify get: ${e.message}")
            e.printStackTrace()
            ModelResult.error(Throwable(e))
        }
    }

    suspend inline fun <reified T:Any>getListById(
        key:String,
        userId:String? = null
    ): ModelResult<List<T>> {
        val userId = userId ?: sessionStorage.get()?.uid ?: return ModelResult.error(Exception("fun getListById: Usuario nao encontrado"))
        return try {
            firestore.collection(key).whereEqualTo("userId", userId).get().await()
                .run {
                    val list:MutableList<T> = mutableListOf()
                    for (item in this) {
                        list.add(item.toObject<T>())
                    }
                    ModelResult.success(list.toList())
                }
        }catch (e:Exception) {
            Timber.tag("Firebase").e("FirebaseModify get: ${e.message}")
            e.printStackTrace()
            ModelResult.error(Throwable(e))
        }
    }
}