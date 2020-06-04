package com.catlove.gitcat

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class SettingsDevAdapter(private val context: Context, var settingDevList: List<SettingsDev>) : RecyclerView.Adapter<SettingsDevViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingsDevViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.dev_data_item,parent,false)
        return SettingsDevViewHolder(view)
    }

    override fun getItemCount(): Int = settingDevList.size

    override fun onBindViewHolder(holder: SettingsDevViewHolder, position: Int) {
        holder.bind(settingDevList[position], context)
    }
}