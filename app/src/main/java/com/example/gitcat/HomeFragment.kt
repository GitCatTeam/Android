package com.example.gitcat

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_home.view.*
import androidx.fragment.app.FragmentPagerAdapter


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment() {
    var adapterViewPager: FragmentPagerAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view: View = inflater.inflate(R.layout.fragment_home, container, false)

        //튜토리얼
//        tuLayout.bringToFront()
//
//        val pager = view?.findViewById<View>(R.id.tu_viewpager) as ViewPager
//        adapterViewPager = TuAdapter(activity!!.supportFragmentManager)
//        pager.adapter = adapterViewPager
//
//        val tabLayout = view.findViewById<View>(R.id.tab_layout) as TabLayout
//        tabLayout.setupWithViewPager(pager,true)

        //튜토리얼
        val tuDialog = TuDialogFragment()
        tuDialog.setStyle(DialogFragment.STYLE_NO_TITLE,android.R.style.Theme_Holo_Light)
        tuDialog.show(fragmentManager!!,"addons_fragment")


        //하단 탭 버튼 리스너
        view.diaryIcon.setOnClickListener { view ->
            val intent = Intent(activity,CollectionActivity::class.java)
            startActivity(intent)
        }

        view.settingsIcon.setOnClickListener{ view ->
            val intent = Intent(activity,SettingsActivity::class.java)
            startActivity(intent)
        }

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onDestroy() {
        super.onDestroy()
    }



    companion object {
        fun newInstance(): HomeFragment = HomeFragment()
    }


}
