package com.example.githubprofile.model

data class User(
    val login: String,
    val id: Int,
    val avatar_url: String,
    val html_url: String,
    val repos_url: String,
    val public_repos: Int
)