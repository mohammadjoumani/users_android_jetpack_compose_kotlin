package com.mmj.madarsofttask.presentation.adduser

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mmj.madarsofttask.core.generic.DataState
import com.mmj.madarsofttask.core.generic.UiText
import com.mmj.madarsofttask.domain.model.User
import com.mmj.madarsofttask.domain.usercase.AddUserUseCase
import com.mmj.madarsofttask.domain.usercase.validation.ValidateTextUseCase
import com.mmj.madarsofttask.presentation.util.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddUserViewModel @Inject constructor(
    private val addUserUseCase: AddUserUseCase,
    private val validateTextUseCase: ValidateTextUseCase
) : ViewModel() {

    private val _state = mutableStateOf(AddUserState())
    val state: State<AddUserState> = _state

    fun onEvent(event: AddUserEvent) {
        viewModelScope.launch {
            when (event) {
                is AddUserEvent.AddUser -> {
                    if (nameValidate() && jobTitleValidate()) {
                        addUser()
                    }
                }

                is AddUserEvent.NameChange -> {
                    _state.value = _state.value.copy(name = event.name)
                    nameValidate()
                }

                is AddUserEvent.JobTitleChanged -> {
                    _state.value = _state.value.copy(jobTitle = event.jobTitle)
                    jobTitleValidate()
                }

                is AddUserEvent.GenderChanged -> {
                    _state.value = _state.value.copy(gender = event.gender)
                }

                is AddUserEvent.AddCounter -> {
                    val age = _state.value.age + 1
                    _state.value = _state.value.copy(age = age)
                }

                is AddUserEvent.MinusCounter -> {
                    var age = _state.value.age
                    if (age > 18) {
                       age -= 1
                    }
                    _state.value = _state.value.copy(age = age)
                }
            }
        }
    }

    private suspend fun addUser() {
        _state.value = _state.value.copy(uiState = UIState.Loading)
        addUserUseCase.execute(
            User(
                name = _state.value.name,
                age = _state.value.age,
                gender = _state.value.gender,
                jobTitle = _state.value.jobTitle
            )
        ).let {
            when (it) {
                is DataState.Success -> {
                    _state.value = _state.value.copy(uiState = UIState.Success)
                }

                is DataState.Failure -> {
                    _state.value = _state.value.copy(uiState = UIState.Error)
                }
            }
        }
    }

    private suspend fun nameValidate(): Boolean {
        val nameResult = validateTextUseCase.execute(_state.value.name)
        _state.value = _state.value.copy(nameError = nameResult.errorMessage)
        return nameResult.successful
    }

    private suspend fun jobTitleValidate(): Boolean {
        val nameResult = validateTextUseCase.execute(_state.value.jobTitle)
        _state.value = _state.value.copy(jobTitleError = nameResult.errorMessage)
        return nameResult.successful
    }

}

sealed class AddUserEvent {
    object AddUser : AddUserEvent()
    data class NameChange(val name: String) : AddUserEvent()
    data class JobTitleChanged(val jobTitle: String) : AddUserEvent()
    data class GenderChanged(val gender: Int) : AddUserEvent()
    object AddCounter : AddUserEvent()
    object MinusCounter : AddUserEvent()
}

data class AddUserState(
    val uiState: UIState = UIState.Init,
    val name: String = "",
    val nameError: UiText? = null,
    val age: Int = 18,
    val gender: Int = 0,
    val jobTitle: String = "",
    val jobTitleError: UiText? = null
)