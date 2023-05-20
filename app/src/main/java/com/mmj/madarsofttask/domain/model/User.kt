package com.mmj.madarsofttask.domain.model

data class User(
    val id: Int? = null,
    val name: String,
    val age: Int,
    val gender: Int,
    val jobTitle: String,
    val isExpanded: Boolean = false
)