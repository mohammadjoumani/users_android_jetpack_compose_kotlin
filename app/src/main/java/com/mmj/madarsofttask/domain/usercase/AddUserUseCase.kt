package com.mmj.madarsofttask.domain.usercase

import com.mmj.madarsofttask.core.generic.DataState
import com.mmj.madarsofttask.core.generic.usecase.BaseUseCase
import com.mmj.madarsofttask.domain.model.User
import com.mmj.madarsofttask.domain.repository.UserRepository
import javax.inject.Inject

class AddUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) : BaseUseCase<User, DataState<Unit>> {

    override suspend fun execute(input: User): DataState<Unit> {
        return userRepository.insert(input)
    }
}