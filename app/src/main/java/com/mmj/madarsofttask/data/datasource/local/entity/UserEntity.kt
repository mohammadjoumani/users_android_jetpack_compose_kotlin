package com.mmj.madarsofttask.data.datasource.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Users")
data class UserEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "UsersId")
    val id: Int? = null,

    @ColumnInfo(name = "UserName")
    val name: String,

    @ColumnInfo(name = "UserAge")
    val age: Int,

    @ColumnInfo(name = "UserGender")
    val gender: Int,

    @ColumnInfo(name = "UsersJobTitle")
    val jobTitle: String,
)