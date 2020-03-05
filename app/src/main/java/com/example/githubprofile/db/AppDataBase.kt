package com.example.githubprofile.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.githubprofile.model.Repos
import com.example.githubprofile.model.User

@Database(entities = [User::class, Repos::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    abstract fun userDao(): UserDao

    /*companion object {
        private var INSTANCE: AppDataBase? = null

        fun getDataBase(context: Context): AppDataBase? {

            if (INSTANCE == null) {
                synchronized(AppDataBase::class) {
                    INSTANCE = Room.databaseBuilder(context, AppDataBase::class.java, "db")
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }
    }*/

}