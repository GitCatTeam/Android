package com.example.gitcat

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_home.view.*
import androidx.fragment.app.FragmentPagerAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_tu.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment() {
    var adapterViewPager: FragmentPagerAdapter? = null
    val tuDialog = TuDialogFragment()
    val graduateDialog = GraduateDialogFragment()
    val upgradeDialog = UpgradeDialogFragment()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view: View = inflater.inflate(R.layout.fragment_home, container, false)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        init()
    }

    fun init(){
        //튜토리얼
        tuDialog.setStyle(DialogFragment.STYLE_NO_TITLE,android.R.style.Theme_Holo_Light)
        tuDialog.show(fragmentManager!!,"addons_fragment")

        //졸업 다이얼로그
        //graduateDialog.show(fragmentManager!!,"graduate_fragment")

        //업그레이드 다이얼로그
        //upgradeDialog.show(fragmentManager!!,"upgrade_fragment")

        //하단 탭 버튼 리스너
        diaryIcon.setOnClickListener { view ->
            val intent = Intent(activity,CollectionActivity::class.java)
            startActivity(intent)
        }

        settingsIcon.setOnClickListener{ view ->
            val intent = Intent(activity,SettingsActivity::class.java)
            startActivity(intent)
        }

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
