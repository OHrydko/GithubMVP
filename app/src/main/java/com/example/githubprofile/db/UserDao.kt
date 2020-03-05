package com.example.githubprofile.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.githubprofile.model.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

    @Query("SELECT COUNT(*) FROM user where lower(login) = :login")
    fun checkLoginExists(login: String): Int

    @Query("SELECT * FROM user WHERE lower(login) = :login")
    fun getUser(login: String): User?
}