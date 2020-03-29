package com.example.gitcat

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gitcat.R
import com.example.gitcat.model.ChooseCatBasicModel
import kotlinx.android.synthetic.main.choose_cat_item.view.*

class ChooseCatViewHolder(view: View):RecyclerView.ViewHolder(view){
    val img:ImageView = view.findViewById(R.id.img_choose_cat_item)
    fun bind(data: ChooseCatBasicModel){
        Glide.with(itemView).load(data.img).into(img)
    }
}