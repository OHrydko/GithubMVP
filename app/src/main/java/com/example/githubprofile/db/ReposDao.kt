package com.example.githubprofile.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.githubprofile.model.Repos
import com.example.githubprofile.model.User
import io.reactivex.Observable

@Dao
interface ReposDao {

    @Query("SELECT * FROM repository")
    fun getAll(): List<Repos>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(repos: List<Repos>)

    @Query("SELECT COUNT(*) FROM repository where lower(login) = :login")
    fun checkLoginExists(login: String): Int

    @Query("SELECT * FROM repository WHERE lower(login) = :login")
    fun getRepos(login: String): List<Repos>?
}