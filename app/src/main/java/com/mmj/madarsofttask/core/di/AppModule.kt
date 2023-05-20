package com.mmj.madarsofttask.core.di

import com.mmj.madarsofttask.data.datasource.local.dao.UserDao
import com.mmj.madarsofttask.data.repository.UserRepositoryImpl
import com.mmj.madarsofttask.domain.repository.UserRepository
import com.mmj.madarsofttask.domain.usercase.AddUserUseCase
import com.mmj.madarsofttask.domain.usercase.GetUsersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesUserRepository(
        userDao: UserDao
    ): UserRepository {
        return UserRepositoryImpl(userDao)
    }

    @Singleton
    @Provides
    fun providesAddUserUseCase(
        userRepository: UserRepository
    ): AddUserUseCase {
        return AddUserUseCase(userRepository)
    }

    @Singleton
    @Provides
    fun providesGetUsersUseCase(
        userRepository: UserRepository
    ): GetUsersUseCase {
        return GetUsersUseCase(userRepository)
    }
}