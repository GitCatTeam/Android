package com.example.gitcat

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class ChooseCatAdapter(fm: FragmentManager, private var tabCount: Int) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        when(position){
            0 -> return BasicCatFragment()
            else -> return SpecialCatFragment()
        }
    }

    override fun getCount(): Int {
        return tabCount
    }
}