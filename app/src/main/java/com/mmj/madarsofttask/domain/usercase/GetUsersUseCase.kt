package com.mmj.madarsofttask.domain.usercase

import com.mmj.madarsofttask.core.generic.DataState
import com.mmj.madarsofttask.core.generic.usecase.BaseUseCase
import com.mmj.madarsofttask.domain.model.User
import com.mmj.madarsofttask.domain.repository.UserRepository
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val userRepository: UserRepository
) : BaseUseCase<Unit, DataState<List<User>>> {

    override suspend fun execute(input: Unit): DataState<List<User>> {
        return userRepository.getUsers()
    }
}