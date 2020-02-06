package com.example.gitcat

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RepositoryAdapter(private val context: Context, val repoList: ArrayList<Repository>) : RecyclerView.Adapter<RepositoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.repository_commit_data_item,parent,false)
        return RepositoryViewHolder(view)
    }

    override fun getItemCount(): Int = repoList.size

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.bind(repoList[position], context)
    }
}