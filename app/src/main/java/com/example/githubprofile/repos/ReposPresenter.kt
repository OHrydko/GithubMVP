package com.example.githubprofile.repos

import com.example.githubprofile.base.BasePresenter
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

    override fun onViewCreated() {
        view.showLoading()
        subscription = apiService
            .getRepos(name)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnTerminate { view.hideLoading() }
            .subscribe(
                { repos ->
                    view.showRepos(repos)
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