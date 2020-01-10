package com.example.gitcat

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

class ReportAdapter(private val context: Context) : RecyclerView.Adapter<ReportViewHolder>(){
    var data = listOf<Report>()
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ReportViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.report_data_item,parent,false)
        return ReportViewHolder(view)
    }

    override fun getItemCount(): Int = data.size


    override fun onBindViewHolder(holder: ReportViewHolder?, position: Int) {
        holder?.bind(data[position])
    }
}