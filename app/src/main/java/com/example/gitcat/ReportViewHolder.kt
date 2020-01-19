package com.example.gitcat

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ReportViewHolder(itemView: View, val itemClick: (Report) -> Unit) : RecyclerView.ViewHolder(itemView) {
    val strTotalCommit = itemView?.findViewById<TextView>(R.id.totalCommit)
    val strLanguage = itemView?.findViewById<TextView>(R.id.language)

    fun bind(data: Report, context: Context){
        strTotalCommit?.text=data.totalCommit
        strLanguage?.text=data.language

        itemView.setOnClickListener { itemClick(data) }
    }

}