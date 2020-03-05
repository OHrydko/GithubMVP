package com.example.githubprofile.repos

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubprofile.R
import com.example.githubprofile.adapter.AdapterRepos
import com.example.githubprofile.base.BaseActivity
import com.example.githubprofile.model.Repos
import kotlinx.android.synthetic.main.activity_github.containerLouder
import kotlinx.android.synthetic.main.activity_repos.*


class ReposActivity : BaseActivity<ReposPresenter>(), ReposView {
    private val adapter = AdapterRepos(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repos)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { super.onBackPressed() }
        val login: String? = intent.getStringExtra("login")
        presenter.name = login.toString()
        presenter.onViewCreated(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onViewDestroyed()
    }

    override fun instantiatePresenter(): ReposPresenter {
        return ReposPresenter(this)
    }

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        containerLouder.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        containerLouder.visibility = View.GONE
    }

    override fun showRepos(repos: List<Repos>) {
        adapter.updateList(repos)
    }

}
