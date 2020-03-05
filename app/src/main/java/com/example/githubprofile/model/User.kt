package com.example.githubprofile.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey
    val login: String,
    val avatar_url: String,
    val public_repos: Int
)