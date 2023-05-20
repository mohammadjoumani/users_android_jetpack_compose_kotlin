package com.mmj.madarsofttask.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mmj.madarsofttask.core.app.Constants
import com.mmj.madarsofttask.data.datasource.local.dao.UserDao
import com.mmj.madarsofttask.data.datasource.local.entity.UserEntity

@Database(
    entities = [
        UserEntity::class,
    ],
    version = Constants.DB_VERSION,
    exportSchema = false,
)
abstract class TaskDatabase : RoomDatabase() {

    companion object {
        const val DATABASE_NAME: String = Constants.DB_NAME
    }


    abstract val userDao: UserDao
}