package com.catlove.gitcat

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RepositoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val strRepName = itemView?.findViewById<TextView>(R.id.repName)
    val recycle = itemView?.findViewById<RecyclerView>(R.id.repositorydetail_recyclerview)

    fun bind(data: Repository, context: Context){
        strRepName?.text=data.repName
        repositoryDetailRecycler(data.commit)
    }

    fun repositoryDetailRecycler(list: List<RepositoryDetail>){
        recycle.layoutManager = LinearLayoutManager(itemView.context,LinearLayoutManager.VERTICAL,false)
        val listAdapter = RepositoryDetailAdapter(itemView.context,list)
        recycle.adapter = listAdapter
        listAdapter.notifyDataSetChanged()
    }
}