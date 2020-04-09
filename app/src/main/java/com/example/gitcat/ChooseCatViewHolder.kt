package com.example.gitcat

import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gitcat.R
import com.example.gitcat.model.ChooseCatBasicModel
import kotlinx.android.synthetic.main.choose_cat_item.view.*

class ChooseCatViewHolder(view: View):RecyclerView.ViewHolder(view){
    val constraint: ConstraintLayout = view.findViewById(R.id.cl_choose_cat)
    val img:ImageView = view.findViewById(R.id.img_choose_cat_item)
    fun bind(data: ChooseCatBasicModel){
        Glide.with(itemView).load(data.profileImg).into(img)
        constraint.setOnClickListener {
            it.isSelected = !it.isSelected
        }
    }
}