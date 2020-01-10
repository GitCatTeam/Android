package com.example.gitcat

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

class ReportViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val strTotalCommit: TextView = view.findViewById(R.id.totalCommit)
    val strLanguage: TextView = view.findViewById(R.id.language)

    fun bind(data: Report){
        strTotalCommit.text=data.totalCommit
        strLanguage.text=data.language
    }
}