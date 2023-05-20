package com.mmj.madarsofttask.data.repository

import com.mmj.madarsofttask.core.generic.DataState
import com.mmj.madarsofttask.data.datasource.local.dao.UserDao
import com.mmj.madarsofttask.data.datasource.local.mapper.mapFromDomain
import com.mmj.madarsofttask.data.datasource.local.mapper.mapFromListEntity
import com.mmj.madarsofttask.domain.model.User
import com.mmj.madarsofttask.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : UserRepository {
    override suspend fun insert(user: User): DataState<Unit> {
        return try {
            DataState.Success(userDao.insert(user.mapFromDomain()))
        } catch (exception: Exception) {
            DataState.Failure(exception.message)
        }
    }

    override suspend fun getUsers(): DataState<List<User>> {
        return try {
            DataState.Success(userDao.getUsers().mapFromListEntity())
        } catch (exception: Exception) {
            DataState.Failure(exception.message)
        }
    }

}