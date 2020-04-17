package com.example.gitcat

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gitcat.model.ChooseCatNewModel

class NewCatRecyclerAdapter (private val context: Context) : RecyclerView.Adapter<NewCatViewHolder>(){
    var data = arrayListOf<ChooseCatNewModel>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewCatViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.new_cat_item,parent,false)
        return NewCatViewHolder(view)
    }

    override fun getItemCount(): Int =data.size

    override fun onBindViewHolder(holder: NewCatViewHolder, position: Int) {
        holder.bind(data[position])
    }
}
