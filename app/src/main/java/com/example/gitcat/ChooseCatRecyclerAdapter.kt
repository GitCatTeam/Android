package com.example.gitcat

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gitcat.model.ChooseCatBasicModel

class ChooseCatRecyclerAdapter(private val context:Context):RecyclerView.Adapter<ChooseCatViewHolder>() {
    var data = listOf<ChooseCatBasicModel>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChooseCatViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.choose_cat_item,parent,false)
        return ChooseCatViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ChooseCatViewHolder, position: Int) {
        holder.bind(data[position])
    }
}