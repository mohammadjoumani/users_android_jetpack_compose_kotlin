package com.mmj.madarsofttask.presentation.users

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mmj.madarsofttask.core.generic.DataState
import com.mmj.madarsofttask.domain.model.User
import com.mmj.madarsofttask.domain.usercase.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {

    private val _state = mutableStateOf(UsersState())
    val state: State<UsersState> = _state

    init {
        onEvent(UsersEvent.GetUsers)
    }

    fun onEvent(event: UsersEvent) {
        viewModelScope.launch {
            when(event) {
                is UsersEvent.GetUsers -> {
                    getUsers()
                }
            }
        }
    }

    private suspend fun getUsers() {
        _state.value = _state.value.copy(
            isLoading = true,
            isSuccess = false,
            isError = false,
            isEmpty = false
        )
        getUsersUseCase.execute(Unit).let {
            when(it) {
                is DataState.Success -> {
                    if (it.data.isEmpty()) {
                        _state.value = _state.value.copy(
                            isLoading = false,
                            isSuccess = false,
                            isError = false,
                            isEmpty = true
                        )
                    } else {
                        _state.value = _state.value.copy(
                            isLoading = false,
                            isSuccess = true,
                            isError = false,
                            isEmpty = false,
                            users = it.data
                        )
                    }
                }
                is DataState.Failure -> {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        isSuccess = false,
                        isError = true,
                        isEmpty = false
                    )
                }
            }
        }
    }
}

sealed class UsersEvent {
    object GetUsers: UsersEvent()
}

data class UsersState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
    val isEmpty: Boolean = false,
    val users: List<User> = listOf()
)