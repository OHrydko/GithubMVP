package com.example.githubprofile.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repository")
data class Repos(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var login: String,
    val name: String,
    val full_name: String,
    val html_url: String
)