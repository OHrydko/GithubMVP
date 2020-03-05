package com.example.githubprofile.ui

import android.annotation.SuppressLint
import android.content.Context
import com.example.githubprofile.base.BasePresenter
import com.example.githubprofile.db.AppDataBase
import com.example.githubprofile.db.UserDao
import com.example.githubprofile.network.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GithubPresenter(githubView: GithubView) : BasePresenter<GithubView>(githubView) {
    @Inject
    lateinit var apiService: ApiService
    private var subscription: Disposable? = null
    var name: String = ""
    private var appDataBase: AppDataBase? = null
    private var userDao: UserDao? = null

    @SuppressLint("DefaultLocale")
    override fun onViewCreated(context: Context) {
        view.showLoading()
        appDataBase = AppDataBase.getDataBase(context)
        userDao = appDataBase?.userDao()

        if (userDao?.checkLoginExists(name) == 0) {
            subscription = apiService
                .getUser(name)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnTerminate { view.hideLoading() }
                .subscribe(
                    { user ->
                        view.showUserInfo(user)
                        Thread {
                            userDao?.insert(user)
                        }.start()


                    },
                    { t: Throwable? ->
                        if (t != null) {
                            view.showError(t.message.toString())
                        }
                    }
                )
        } else {
            userDao?.getUser(login = name.toLowerCase())?.let { view.showUserInfo(it) }
            view.hideLoading()
        }

    }

    override fun onViewDestroyed() {
        subscription?.dispose()
    }
}