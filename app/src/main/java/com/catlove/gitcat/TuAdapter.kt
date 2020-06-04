package com.catlove.gitcat

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import android.os.Bundle
import androidx.fragment.app.FragmentStatePagerAdapter

class TuAdapter(fm: FragmentManager,val num_fragment: Int) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        val fragment = TuFragment()
        val bundle = Bundle(1)
        when(position){
            0 -> {
                bundle.putInt("R_id",  position)
            }
            1 -> {
                bundle.putInt("R_id", position)
            }
            2 -> {
                bundle.putInt("R_id", position)
            }
            3 -> {
                bundle.putInt("R_id", position)
            }
        }
        fragment.arguments = bundle
        return fragment
    }

    override fun getCount(): Int {
        return num_fragment
    }

}