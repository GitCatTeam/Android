package com.catlove.gitcat

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.catlove.gitcat.model.ChooseCatModel

class ChooseCatAdapter(fm: FragmentManager, private var tabCount: Int, private var data: ChooseCatModel, context: Context) : FragmentStatePagerAdapter(fm) {
    var title = arrayOf(context.resources.getString(R.string.info4_tab1),context.resources.getString(R.string.info4_tab2))
    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> BasicCatFragment(data.normal)
            else -> SpecialCatFragment(data.special)
        }
    }

    override fun getCount(): Int {
        return tabCount
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return title[position]
    }
}