package com.catlove.gitcat

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CollectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val strCollectionName = itemView?.findViewById<TextView>(R.id.collectionName)
    val strCollectionDoing = itemView?.findViewById<TextView>(R.id.collectionDoing)
    val imgCollectionMedal = itemView?.findViewById<ImageView>(R.id.collectionMedal)
    val imgCollectionImg = itemView?.findViewById<ImageView>(R.id.collectionImg)

    fun bind(data: Collection, context: Context){
        strCollectionName?.text=data.collectionName
        strCollectionDoing?.text=data.collectionDoing
        if(data.collectionMedal){
            imgCollectionMedal.visibility = View.VISIBLE
        }else{
            imgCollectionMedal.visibility = View.GONE
        }
        Glide.with(itemView.context).load(data.collectionImg).into(imgCollectionImg)
    }
}