package com.catlove.gitcat

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RepositoryDetailAdapter(private val context: Context, var repoDetailList: List<RepositoryDetail>) : RecyclerView.Adapter<RepositoryDetailViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryDetailViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.repository_commit_data_item_detail,parent,false)
        return RepositoryDetailViewHolder(view)
    }

    override fun getItemCount(): Int = repoDetailList.size

    override fun onBindViewHolder(holder: RepositoryDetailViewHolder, position: Int) {
        holder.bind(repoDetailList[position], context)
    }
}