package com.example.githubprofile.ui

import com.example.githubprofile.base.BaseView
import com.example.githubprofile.model.User

interface GithubView : BaseView {

    fun showError(error: String)

    fun showLoading()

    fun hideLoading()

    fun showUserInfo(user: User)
}