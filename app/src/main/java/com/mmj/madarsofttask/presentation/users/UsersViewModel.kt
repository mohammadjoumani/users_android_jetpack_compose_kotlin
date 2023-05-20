package com.mmj.madarsofttask.presentation.users

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mmj.madarsofttask.core.generic.DataState
import com.mmj.madarsofttask.domain.model.User
import com.mmj.madarsofttask.domain.usercase.GetUsersUseCase
import com.mmj.madarsofttask.presentation.util.UIState
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
        _state.value = _state.value.copy(uiState = UIState.Loading)
        getUsersUseCase.execute(Unit).let {
            when(it) {
                is DataState.Success -> {
                    if (it.data.isEmpty()) {
                        _state.value = _state.value.copy(uiState = UIState.Empty)
                    } else {
                        _state.value = _state.value.copy(uiState = UIState.Success, users = it.data)
                    }
                }
                is DataState.Failure -> {
                    _state.value = _state.value.copy(uiState = UIState.Error)
                }
            }
        }
    }
}

sealed class UsersEvent {
    object GetUsers: UsersEvent()
}

data class UsersState(
    val uiState: UIState = UIState.Init,
    val users: List<User> = listOf()
)