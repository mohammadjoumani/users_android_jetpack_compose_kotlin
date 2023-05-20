package com.mmj.madarsofttask.domain.repository

import com.mmj.madarsofttask.core.generic.DataState
import com.mmj.madarsofttask.domain.model.User

interface UserRepository {

    suspend fun insert(user: User): DataState<Unit>

    suspend fun getUsers(): DataState<List<User>>
}