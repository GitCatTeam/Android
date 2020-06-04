package com.catlove.gitcat

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RepositoryDetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val strRepMessage = itemView?.findViewById<TextView>(R.id.repo_message)
    val strRepTime = itemView?.findViewById<TextView>(R.id.repo_time)

    fun bind(data: RepositoryDetail, context: Context){
        strRepMessage?.text=data.message
        strRepTime?.text=data.time
    }
}