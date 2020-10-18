package com.catlove.gitcat

import android.content.Context
import android.content.SharedPreferences
import android.util.Log.d
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.catlove.gitcat.model.ChooseCatBasicModel

class ChooseCatRecyclerAdapter(private val context:Context,var button: Button):RecyclerView.Adapter<ChooseCatViewHolder>() {
    var data = listOf<ChooseCatBasicModel>()
    var select = -1
    private lateinit var hideDialogFragment :HideCatDialogFragment

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChooseCatViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.choose_cat_item,parent,false)
        return ChooseCatViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ChooseCatViewHolder, position: Int) {
        holder.bind(data[position])

        //하나만 선택
        if(select == -1){
            holder.constraint.isSelected = false
        }else{
            holder.constraint.isSelected = select == position
        }
        holder.constraint.setOnClickListener{
            holder.constraint.isSelected = true
            if(select != position) {
                notifyItemChanged(select)
                select = position
            }

            if(!data[select].isAvailable){//false인 경우
                button.isEnabled = false
                button.setBackgroundResource(R.drawable.info_next)
                //팝업
                hideDialogFragment = HideCatDialogFragment(data[select].profileImg,data[select].description)
                val manager = (holder.itemView.context as FragmentActivity).supportFragmentManager
                hideDialogFragment.show(manager,"hideDialogFragment")
            }else{
                button.isEnabled = true
                button.setBackgroundResource(R.drawable.info_next_after)

                //shared 저장
                val settings: SharedPreferences = context.getSharedPreferences("gitcat",Context.MODE_PRIVATE)
                val editor: SharedPreferences.Editor = settings.edit()
                editor.putString("catImageUrl",data[select].profileImg)
                editor.putInt("catId",data[select].id)
                editor.commit()
            }

        }

    }
}