package com.example.gitcat

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class ChooseCatAdapter(fm: FragmentManager, private var tabCount: Int) : FragmentStatePagerAdapter(fm) {
    var title = arrayOf("기본","스페셜")
    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> BasicCatFragment()
            else -> SpecialCatFragment()
        }
    }

    override fun getCount(): Int {
        return tabCount
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return title[position]
    }
}