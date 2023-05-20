package com.mmj.madarsofttask.domain.model

import com.mmj.madarsofttask.core.generic.UiText

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: UiText? = null
)

