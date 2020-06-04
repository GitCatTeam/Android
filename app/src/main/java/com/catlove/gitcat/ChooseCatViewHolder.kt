package com.catlove.gitcat

import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.catlove.gitcat.model.ChooseCatBasicModel

class ChooseCatViewHolder(view: View):RecyclerView.ViewHolder(view){
    val constraint = view.findViewById(R.id.cl_choose_cat) as ConstraintLayout
    val img:ImageView = view.findViewById(R.id.img_choose_cat_item)
    fun bind(data: ChooseCatBasicModel){
        Glide.with(itemView).load(data.profileImg).into(img)
    }
}