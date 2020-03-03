package com.example.githubprofile.repos

import com.example.githubprofile.base.BaseView
import com.example.githubprofile.model.Repos

interface ReposView : BaseView {

    fun showError(error: String)

    fun showLoading()

    fun hideLoading()

    fun showRepos(repos: List<Repos>)
}