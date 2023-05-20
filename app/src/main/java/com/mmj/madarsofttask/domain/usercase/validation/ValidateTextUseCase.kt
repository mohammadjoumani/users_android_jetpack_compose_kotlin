package com.mmj.madarsofttask.domain.usercase.validation

import com.mmj.madarsofttask.core.generic.UiText
import com.mmj.madarsofttask.R
import com.mmj.madarsofttask.core.functions.isNumber
import com.mmj.madarsofttask.core.generic.usecase.BaseUseCase
import com.mmj.madarsofttask.domain.model.ValidationResult
import javax.inject.Inject

class ValidateTextUseCase @Inject constructor() : BaseUseCase<String, ValidationResult> {

    override suspend fun execute(input: String): ValidationResult {
        if (input.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(resId = R.string.strTheFieldCanNotBeBlank),
            )
        }

        return ValidationResult(
            successful = true,
            errorMessage = null
        )
    }
}