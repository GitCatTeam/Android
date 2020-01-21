package com.example.gitcat

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DiaryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val strDiaryName = itemView?.findViewById<TextView>(R.id.diaryName)
    val strDiaryMajor = itemView?.findViewById<TextView>(R.id.diaryMajor)
    val strDiaryDoing = itemView?.findViewById<TextView>(R.id.diaryDoing)

    fun bind(data: Diary, context: Context){
        strDiaryName?.text=data.diaryName
        strDiaryMajor?.text=data.diaryMajor
        strDiaryDoing?.text=data.diaryDoing
    }
}