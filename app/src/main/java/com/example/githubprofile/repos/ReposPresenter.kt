package com.example.githubprofile.repos

import android.annotation.SuppressLint
import android.content.Context
import com.example.githubprofile.base.BasePresenter
import com.example.githubprofile.db.AppDataBase
import com.example.githubprofile.db.ReposDao
import com.example.githubprofile.network.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ReposPresenter(reposView: ReposView) : BasePresenter<ReposView>(reposView) {
    @Inject
    lateinit var apiService: ApiService
    private var subscription: Disposable? = null
    var name: String = ""
    private var appDataBase: AppDataBase? = null
    private var reposDao: ReposDao? = null

    @SuppressLint("DefaultLocale")
    override fun onViewCreated(context: Context) {
        view.showLoading()
        appDataBase = AppDataBase.getDataBase(context)
        reposDao = appDataBase?.reposDao()
        if (reposDao?.checkLoginExists(name) == 0) {
            subscription = apiService
                .getRepos(name)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnTerminate { view.hideLoading() }
                .subscribe(
                    { repos ->
                        view.showRepos(repos)
                        for (item in repos) {
                            item.login = name
                        }
                        Thread {
                            reposDao?.insert(repos)
                        }.start()

                    },
                    { t: Throwable? ->
                        if (t != null) {
                            view.showError(t.message.toString())
                        }
                    }
                )
        } else {
            reposDao?.getRepos(name.toLowerCase())?.let { view.showRepos(it) }
            view.hideLoading()
        }
    }

    override fun onViewDestroyed() {
        subscription?.dispose()
    }
}