package com.example.gitcat

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class ChooseCatAdapter(fm: FragmentManager, private var tabCount: Int) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        when(position){
            0 -> return ChooseCat1Fragment()
            1 -> return ChooseCat2Fragment()
            2 -> return ChooseCat3Fragment()
            else -> return ChooseCat1Fragment()
        }
    }

    override fun getCount(): Int {
        return tabCount
    }
}