package com.example.githubprofile.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.githubprofile.model.Repos
import com.example.githubprofile.model.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getAllUser(): List<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)

    @Query("SELECT COUNT(*) FROM user where lower(login) = :login")
    fun checkLoginExistsUser(login: String): Int

    @Query("SELECT * FROM user WHERE lower(login) = :login")
    fun getUser(login: String): User?

    @Query("SELECT * FROM repository")
    fun getAllRepos(): List<Repos>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRepos(repos: List<Repos>)

    @Query("SELECT COUNT(*) FROM repository where lower(login) = :login")
    fun checkLoginExistsRepos(login: String): Int

    @Query("SELECT * FROM repository WHERE lower(login) = :login")
    fun getRepos(login: String): List<Repos>?
}