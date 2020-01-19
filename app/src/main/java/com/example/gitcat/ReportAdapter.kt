package com.example.gitcat

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ReportAdapter(private val context: Context, val reportList: ArrayList<Report>, val itemClick: (Report) -> Unit) : RecyclerView.Adapter<ReportViewHolder>(){
//    var data = listOf<Report>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.report_data_item,parent,false)
        return ReportViewHolder(view, itemClick)
    }

    override fun getItemCount(): Int = reportList.size


    override fun onBindViewHolder(holder: ReportViewHolder, position: Int) {
        holder.bind(reportList[position], context)
    }

}