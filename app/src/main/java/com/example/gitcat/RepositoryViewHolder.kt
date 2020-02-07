package com.example.gitcat

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RepositoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val strRepName = itemView?.findViewById<TextView>(R.id.repName)

    fun bind(data: Repository, context: Context){
        strRepName?.text=data.repName
    }
}