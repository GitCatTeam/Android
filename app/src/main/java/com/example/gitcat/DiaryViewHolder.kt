package com.example.gitcat

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DiaryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val strDiaryName = itemView?.findViewById<TextView>(R.id.collectionName)
    val strDiaryMajor = itemView?.findViewById<TextView>(R.id.collectionMajor)
    val strDiaryDoing = itemView?.findViewById<TextView>(R.id.collectionDoing)

    fun bind(data: Diary, context: Context){
        strDiaryName?.text=data.collectionName
        strDiaryMajor?.text=data.collectionMajor
        strDiaryDoing?.text=data.collectionDoing
    }
}