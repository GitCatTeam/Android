package com.example.gitcat

import android.content.Intent
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.util.Log.d
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import com.yuxingxin.library.MultiRadioGroup
import kotlinx.android.synthetic.main.activity_info4.*
import android.view.ViewGroup
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T

class Info4Activity : AppCompatActivity(), OnDataPass{

    private var catId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info4)

        backButton.setOnClickListener{
            onBackPressed()
        }

        //초기화
        buttonGo.isEnabled = false

        //탭 이벤트
        ////탭 사이 벌리기
        val tab = tabLayout.getChildAt(0) as ViewGroup
        for (i in 0 until tab.childCount - 1) {
            val v = tab.getChildAt(i)
            val params = v.layoutParams as ViewGroup.MarginLayoutParams
            params.rightMargin = 20
        }

        val adapter = ChooseCatAdapter(supportFragmentManager, tabLayout.tabCount)
        pager.adapter = adapter

        pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(p0: TabLayout.Tab?) {}
            override fun onTabUnselected(p0: TabLayout.Tab?) {}
            override fun onTabSelected(tab: TabLayout.Tab?) {
                pager.currentItem = tab!!.position
            }

        })

        //다음 버튼 누르면
        buttonGo.setOnClickListener{
            //FIXME: d("***",catId.toString()) 로 넘겨받기~
            //화면 이동
            var intent = Intent(this,Info5Activity::class.java)
            startActivity(intent)
        }

    }

    override fun onDataPass(catId: Int) {
        d("*+*+",""+catId)
        this.catId = catId
        if(catId != 0){
            //버튼 활성화
            buttonGo.isEnabled = true
            buttonGo.setBackgroundResource(R.drawable.info_next_after)
        }
    }

}
