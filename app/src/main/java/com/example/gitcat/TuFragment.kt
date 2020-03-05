package com.example.gitcat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_tu.*
import android.R.attr.fragment
import android.content.Intent
import android.R.attr.fragment
import android.util.Log

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class TuFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null
    private var view_id: Int? = null

    override fun setArguments(args: Bundle?) {
        view_id = args?.getInt("R_id")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_tu, container, false)
        val tu_image = view.findViewById<ImageView>(R.id.tu_image)
        val tu_num = view.findViewById<TextView>(R.id.tu_num)
        val tu_title = view.findViewById<TextView>(R.id.tu_title)
        val tu_content = view.findViewById<TextView>(R.id.tu_content)
        var tu_btn = view.findViewById<Button>(R.id.tu_btn)

        if(view_id==0){
            tu_image.setImageDrawable(activity!!.getDrawable(R.drawable.img_tutorial_1))
            tu_num.text = "01"
            tu_title.text = "커밋으로 고양씨 성장시키기"
            tu_content.text = "매일 꾸준히 커밋해보세요.\n" +
                    "고양씨의 개발환경이 4단계에 걸쳐 개선됩니다."
        }
        else if(view_id==1){
            tu_image.setImageDrawable(activity!!.getDrawable(R.drawable.img_tutorial_2))
            tu_num.text = "02"
            tu_title.text = "고양씨 졸업시키기"
            tu_content.text = "4단계 고양씨는 졸업하게 되지만, 걱정 마세요!\n" +
                    "우측 상단의 수집 버튼을 통해 졸업앨범을 볼 수 있어요."
        }
        else if(view_id==2){
            tu_image.setImageDrawable(activity!!.getDrawable(R.drawable.img_tutorial_3))
            tu_num.text = "03"
            tu_title.text = "달력과 레포트로 나의 개발 돌아보기"
            tu_content.text = "하단의 커밋달력을 통해 일일 커밋 현황을,\n" +
                    "레포트를 통해 매달 개발 통걔를 확인할 수 있어요."
        }
        else if(view_id==3){
            tu_image.setImageDrawable(activity!!.getDrawable(R.drawable.img_tutorial_4))
            tu_num.text = "04"
            tu_title.text = "튜토리얼 진행하고 고양이 받기"
            tu_content.visibility = View.GONE
            tu_btn.visibility = View.VISIBLE
        }

        tu_btn.setOnClickListener {
            //TODO: DialogFragment 종료
            //val tuDialog = TuDialogFragment()
            //val dialogFragment = fragment as TuDialogFragment
            //tuDialog.onClick(it)
            Log.e("error","되나 안되나?")



            //fragmentManager!!.beginTransaction().detach(tuDialog)
            //childFragmentManager?.beginTransaction()?.detach(tuDialog).commit()
        }

        return view

    }


    companion object {

        fun newInstance(): TuFragment = TuFragment()
    }
}
