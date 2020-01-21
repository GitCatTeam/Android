package com.example.gitcat

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class DiaryAdapter(private val context: Context, val diaryList: ArrayList<Diary>) : RecyclerView.Adapter<DiaryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiaryViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.diary_data_item,parent,false)
        return DiaryViewHolder(view)
    }

    override fun getItemCount(): Int = diaryList.size

    override fun onBindViewHolder(holder: DiaryViewHolder, position: Int) {
        holder.bind(diaryList[position], context)
    }
}