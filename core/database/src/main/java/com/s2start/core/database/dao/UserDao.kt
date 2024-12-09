package com.s2start.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.s2start.core.database.entity.UserEntity

@Dao
interface UserDao {
    @Query("SELECT * FROM UserEntity limit 1")
    fun getUser(): UserEntity
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(post: UserEntity)
    @Update
    fun updateTodo(post: UserEntity)
    @Delete(entity = UserEntity::class)
    fun deleteTodo(post: UserEntity)
}