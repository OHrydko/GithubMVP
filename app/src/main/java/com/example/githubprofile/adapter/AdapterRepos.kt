package com.example.githubprofile.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.githubprofile.R
import com.example.githubprofile.model.Repos
import kotlinx.android.synthetic.main.item.view.*

class AdapterRepos(private val context: Context) :
    RecyclerView.Adapter<AdapterRepos.ViewHolderRepos>() {
    var repos: List<Repos> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderRepos {
        return ViewHolderRepos(LayoutInflater.from(context).inflate(R.layout.item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolderRepos, position: Int) {
        holder.names.text = repos[position].name
        holder.fullName.text = repos[position].html_url
    }

    override fun getItemCount(): Int {
        return repos.size
    }

    fun updateList(repos: List<Repos>) {
        this.repos = repos
        notifyDataSetChanged()
    }

    class ViewHolderRepos(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val names: TextView = itemView.reposName
        val fullName: TextView = itemView.reposFullPath
    }
}