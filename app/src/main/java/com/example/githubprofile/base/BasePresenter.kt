package com.example.githubprofile.base

import com.example.githubprofile.di.component.DaggerInjector
import com.example.githubprofile.di.module.ContextModule
import com.example.githubprofile.di.module.NetworkModule
import com.example.githubprofile.repos.ReposPresenter
import com.example.githubprofile.ui.GithubPresenter

open class BasePresenter<out V : BaseView>(protected val view: V) {
    private val injector = DaggerInjector.builder()
        .baseView(view)
        .contextModule(ContextModule)
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is GithubPresenter -> injector.inject(this)
            is ReposPresenter -> injector.inject(this)
        }
    }

    open fun onViewCreated() {}
    open fun onViewDestroyed() {}

}