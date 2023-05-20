package com.mmj.madarsofttask.data.datasource.local.dao

import androidx.room.*
import com.mmj.madarsofttask.data.datasource.local.entity.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(categoryEntity: UserEntity)

    @Update
    suspend fun update(categoryEntity: UserEntity)

    @Delete
    suspend fun delete(categoryEntity: UserEntity)

    @Query("Select * From Users")
    suspend fun getUsers(): List<UserEntity>
}