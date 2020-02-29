package com.example.gitcat

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.DialogFragment
import android.util.DisplayMetrics
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout


class TuDialogFragment : DialogFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    //private val view: View? = null
    var adapterViewPager: FragmentPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_tu_dialog, container, false)

        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.setCancelable(false)
        dialog?.window?.setGravity(Gravity.CENTER)

        val metrics = resources.displayMetrics
        val screenWidth = (metrics.widthPixels * 0.95).toInt()
        val screenHeight = (metrics.heightPixels * 0.95).toInt()

        dialog?.window?.setLayout(screenWidth,screenHeight)

        val tuLayout = view.findViewById<View>(R.id.tu_layout)
        tuLayout?.bringToFront()

        val pager = view.findViewById<View>(R.id.tu_viewpager) as ViewPager
        adapterViewPager = TuAdapter(childFragmentManager)
        pager.adapter = adapterViewPager
        pager.pageMargin = 10

        val tabLayout = view.findViewById<View>(R.id.tab_layout) as TabLayout
        tabLayout.setupWithViewPager(pager,true)

        return view
    }

    override fun onDetach() {
        super.onDetach()
    }

    override fun onDestroy() {
        super.onDestroy()
    }



    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TuDialogFragment().apply {
                arguments = Bundle().apply {
                    putString("11", param1)
                    putString("22", param2)
                }
            }
    }
}
