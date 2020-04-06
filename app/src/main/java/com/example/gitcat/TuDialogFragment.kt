package com.example.gitcat

import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.Button
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_tu.*
import kotlinx.android.synthetic.main.fragment_tu_dialog.*


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
        dialog?.window?.setGravity(Gravity.LEFT or Gravity.TOP)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        init()
    }

    fun init(){
        val tuLayout = view?.findViewById<View>(R.id.tu_layout)
        tuLayout?.bringToFront()

        adapterViewPager = TuAdapter(childFragmentManager)
        tu_viewpager.adapter = adapterViewPager
        tu_viewpager.pageMargin = 10

        tab_layout.setupWithViewPager(tu_viewpager,true)
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
