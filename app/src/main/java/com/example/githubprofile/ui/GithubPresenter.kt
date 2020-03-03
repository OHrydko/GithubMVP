package com.example.githubprofile.ui

import com.example.githubprofile.base.BasePresenter
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

    override fun onViewCreated() {
        view.showLoading()
        subscription = apiService
            .getUser(name)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnTerminate { view.hideLoading() }
            .subscribe(
                { user ->
                    view.showUserInfo(user)
                },
                { t: Throwable? ->
                    if (t != null) {
                        view.showError(t.message.toString())
                    }
                }
            )
    }

    override fun onViewDestroyed() {
        subscription?.dispose()
    }
}