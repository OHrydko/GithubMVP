package com.example.githubprofile.di.module

import android.content.Context
import androidx.room.Room
import com.example.githubprofile.db.AppDataBase
import com.example.githubprofile.db.UserDao
import dagger.Module
import dagger.Provides

@Module
object RoomModule {

    @Provides
    @JvmStatic
    internal fun getDao(appDataBase: AppDataBase): UserDao {
        return appDataBase.userDao()
    }

    @Provides
    @JvmStatic
    internal fun getDataBase(context: Context): AppDataBase {
        return Room.databaseBuilder(context, AppDataBase::class.java, "db")
            .allowMainThreadQueries()
            .build()
    }
}