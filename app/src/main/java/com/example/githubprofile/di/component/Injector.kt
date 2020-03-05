package com.example.githubprofile.di.component

import com.example.githubprofile.base.BaseView
import com.example.githubprofile.di.module.ContextModule
import com.example.githubprofile.di.module.NetworkModule
import com.example.githubprofile.di.module.RoomModule
import com.example.githubprofile.repos.ReposPresenter
import com.example.githubprofile.ui.GithubPresenter
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(ContextModule::class), (NetworkModule::class), (RoomModule::class)])
interface Injector {

    fun inject(postPresenter: GithubPresenter)
    fun inject(reposPresenter: ReposPresenter)

    @Component.Builder
    interface Builder {
        fun build(): Injector
        fun roomModule(roomModule: RoomModule): Builder
        fun networkModule(networkModule: NetworkModule): Builder
        fun contextModule(contextModule: ContextModule): Builder

        @BindsInstance
        fun baseView(baseView: BaseView): Builder
    }
}