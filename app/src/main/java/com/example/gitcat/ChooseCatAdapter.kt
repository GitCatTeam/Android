package com.example.gitcat

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.gitcat.model.ChooseCatModel

class ChooseCatAdapter(fm: FragmentManager, private var tabCount: Int, private var data: ChooseCatModel) : FragmentStatePagerAdapter(fm) {
    var title = arrayOf("기본","스페셜")
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