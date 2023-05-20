package com.mmj.madarsofttask.data.datasource.local.mapper

import com.mmj.madarsofttask.data.datasource.local.entity.UserEntity
import com.mmj.madarsofttask.domain.model.User


fun UserEntity.mapFromEntity() = User(
    id = this.id,
    name = this.name,
    age = this.age,
    gender = this.gender,
    jobTitle = this.jobTitle
)

fun User.mapFromDomain() = UserEntity(
    id = this.id,
    name = this.name,
    age = this.age,
    gender = this.gender,
    jobTitle = this.jobTitle
)

fun List<UserEntity>.mapFromListEntity(): List<User> {
    return this.map {
        it.mapFromEntity()
    }
}

fun List<User>.mapFromListDomain(): List<UserEntity> {
    return this.map {
        it.mapFromDomain()
    }
}

//endregion1