package com.mmj.madarsofttask.core.di

import android.content.Context
import androidx.room.Room
import com.mmj.madarsofttask.core.db.TaskDatabase
import com.mmj.madarsofttask.data.datasource.local.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun providesNerdDb(@ApplicationContext context: Context): TaskDatabase {
        return Room.databaseBuilder(
            context,
            TaskDatabase::class.java,
            TaskDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun providesUserDao(taskDatabase: TaskDatabase): UserDao {
        return taskDatabase.userDao
    }
}