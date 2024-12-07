package com.s2start.auth.data.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.userProfileChangeRequest
import com.s2start.data.firebase.AuthFirebase
import com.s2start.auth.domain.model.AccountModel
import com.s2start.domain.AuthInfo
import com.s2start.domain.SessionStorage
import com.s2start.domain.util.ModelResult
import kotlinx.coroutines.tasks.await


class AuthFirebaseImpl(
    private val firebaseAuth: FirebaseAuth,
    private val sessionStorage: SessionStorage
): AuthFirebase {
    override suspend fun login(email: String, password: String): ModelResult<String?> {
        return try {
            firebaseAuth.signInWithEmailAndPassword(email, password)
                .await()
                .run {
                    this.user?.let {
                        sessionStorage.set(
                            AuthInfo(
                                email = it.email.orEmpty(),
                                uid = it.uid,
                                displayName = it.displayName.orEmpty(),
                                phoneNumber = it.phoneNumber.orEmpty()
                            )
                        )
                        ModelResult.success(it.uid)
                    }?: run  {
                        ModelResult.error(java.lang.Exception("Usuario nao encontrado"))
                    }
                }
        }catch (e:Exception){
            ModelResult.error(throwable = e)
        }
    }

    override suspend fun logOut(): ModelResult<Unit> {
        firebaseAuth.signOut()
        return  firebaseAuth.currentUser?.let {
            ModelResult.success(Unit)
        } ?: run { ModelResult.error(Exception("Usuario ainda existe")) }
    }

    override suspend fun createAccount(account: AccountModel): ModelResult<Unit> {
        return try {
            val profileUpdates = userProfileChangeRequest { displayName = account.name }
            firebaseAuth.createUserWithEmailAndPassword(account.email, account.password)
                .await()
                .run { firebaseAuth.currentUser?.updateProfile(profileUpdates)?.await().run { ModelResult.success(Unit) } }
        } catch (e:FirebaseAuthException){
            ModelResult.error(throwable = e)
        } catch (e: Exception){
            ModelResult.error(throwable = e)
        }
    }
}