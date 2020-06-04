package com.catlove.gitcat

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class SettingsDevViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
    val imgDevImg = itemView?.findViewById<ImageView>(R.id.dev_img)
    val strDevRole = itemView?.findViewById<TextView>(R.id.dev_role)
    val strDevName = itemView?.findViewById<TextView>(R.id.dev_name)
    val strDevContent = itemView?.findViewById<TextView>(R.id.dev_content)

    fun bind(data: SettingsDev, context: Context){
        Glide.with(itemView.context).load(data.devImg).into(imgDevImg)
        strDevRole?.text=data.devRole
        strDevName?.text=data.devName
        strDevContent?.text=data.devContent
    }
}