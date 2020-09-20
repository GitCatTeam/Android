package com.catlove.gitcat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_tu.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class TuFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null
    private var view_id: Int? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_tu, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        view_id = arguments!!.getInt("R_id")
        init()
    }

    fun init(){
        if(view_id==0){
            tu_image.setImageDrawable(activity!!.getDrawable(R.drawable.img_tutorial_1))
            tu_num.text = "01"
            tu_title.text = getString(R.string.tu_first_mainText)
            tu_content.text = getString(R.string.tu_first_subText)
        }
        else if(view_id==1){
            tu_image.setImageDrawable(activity!!.getDrawable(R.drawable.img_tutorial_2))
            tu_num.text = "02"
            tu_title.text = getString(R.string.tu_second_mainText)
            tu_content.text = getString(R.string.tu_second_subText)
        }
        else if(view_id==2){
            tu_image.setImageDrawable(activity!!.getDrawable(R.drawable.img_tutorial_3))
            tu_num.text = "03"
            tu_title.text = getString(R.string.tu_third_mainText)
            tu_content.text = getString(R.string.tu_third_subText)
        }
        else if(view_id==3){
            tu_image.setImageDrawable(activity!!.getDrawable(R.drawable.img_tutorial_4))
            tu_num.text = "04"
            tu_title.text = getString(R.string.tu_fourth_mainText)
            tu_content.visibility = View.GONE
            tu_btn.visibility = View.VISIBLE
            tu_btn.setOnClickListener {
                //화면 닫기 구현
                val fr = parentFragment as TuDialogFragment
                fr.dismiss()
            }
        }
    }



    companion object {

        fun newInstance(): TuFragment = TuFragment()
    }
}
