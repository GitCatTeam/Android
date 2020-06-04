package com.catlove.gitcat

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CollectionAdapter(private val context: Context, val collectionList: ArrayList<Collection>) : RecyclerView.Adapter<CollectionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.collection_data_item,parent,false)
        return CollectionViewHolder(view)
    }

    override fun getItemCount(): Int = collectionList.size

    override fun onBindViewHolder(holder: CollectionViewHolder, position: Int) {
        holder.bind(collectionList[position], context)
    }
}