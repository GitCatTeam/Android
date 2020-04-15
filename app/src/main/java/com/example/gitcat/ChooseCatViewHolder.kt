package com.example.gitcat

import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gitcat.model.ChooseCatBasicModel

class ChooseCatViewHolder(view: View):RecyclerView.ViewHolder(view){
    val relative = view.findViewById(R.id.cl_choose_cat) as RelativeLayout
    val img:ImageView = view.findViewById(R.id.img_choose_cat_item)
    fun bind(data: ChooseCatBasicModel){
        Glide.with(itemView).load(data.profileImg).into(img)
    }
}