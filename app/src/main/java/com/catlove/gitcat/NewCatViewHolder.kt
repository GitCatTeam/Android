package com.catlove.gitcat

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.catlove.gitcat.model.ChooseCatNewModel

class NewCatViewHolder (view: View) : RecyclerView.ViewHolder(view){
    val cat_img = view.findViewById<ImageView>(R.id.img_new_cat)
    val cat_txt = view.findViewById<TextView>(R.id.txt_new_cat_content)
    fun bind(data: ChooseCatNewModel){
        Glide.with(itemView).load(data.profileImg).into(cat_img)
        cat_txt.text = data.description
    }
}