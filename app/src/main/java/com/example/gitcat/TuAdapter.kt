package com.example.gitcat

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import android.os.Bundle

class TuAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    private val NUM_ITEMS = 4
    private lateinit var fragment: TuFragment

    override fun getItem(position: Int): Fragment {
        var bundle: Bundle? = null
        when(position){
            0 -> {
                bundle = Bundle()
                bundle.putInt("R_id", 0 )
                fragment = TuFragment()
                fragment.arguments = bundle
            }
            1 -> {
                bundle = Bundle()
                bundle.putInt("R_id", 1 )
                fragment = TuFragment()
                fragment.arguments = bundle
            }
            2 -> {
                bundle = Bundle()
                bundle.putInt("R_id", 2 )
                fragment = TuFragment()
                fragment.arguments = bundle
            }
            3 -> {
                bundle = Bundle()
                bundle.putInt("R_id", 3 )
                fragment = TuFragment()
                fragment.arguments = bundle

            }
        }
        return fragment
    }

    override fun getCount(): Int {
        return NUM_ITEMS
    }

}