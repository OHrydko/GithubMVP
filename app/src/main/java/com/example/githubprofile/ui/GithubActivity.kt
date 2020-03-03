package com.example.githubprofile.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.githubprofile.R
import com.example.githubprofile.R.string.repository
import com.example.githubprofile.base.BaseActivity
import com.example.githubprofile.model.User
import com.example.githubprofile.repos.ReposActivity
import kotlinx.android.synthetic.main.activity_github.*

class GithubActivity : BaseActivity<GithubPresenter>(), GithubView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_github)
        click.setOnClickListener {
            if (editTextName.text.isNotEmpty()) {
                presenter.name = editTextName.text.toString()
                presenter.onViewCreated()
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onViewDestroyed()
    }

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        cardView.visibility = View.INVISIBLE
    }

    override fun showLoading() {
        containerLouder.visibility = View.VISIBLE
        click.isEnabled = false
    }

    override fun hideLoading() {
        containerLouder.visibility = View.GONE
        click.isEnabled = true
        cardView.visibility = View.VISIBLE
    }

    override fun showUserInfo(user: User) {
        Glide.with(this).load(user.avatar_url).into(avatar)
        login.text = user.login
        repos.text = getString(repository, user.public_repos)
        container.setOnClickListener {
            startActivity(Intent(this,ReposActivity::class.java)
                .putExtra("login",user.login)) }
    }


    override fun instantiatePresenter(): GithubPresenter {
        return GithubPresenter(this)
    }


}
