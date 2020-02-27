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
import com.yuxingxin.library.MultiRadioGroup
import kotlinx.android.synthetic.main.activity_info4.*


class Info4Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info4)

        backButton.setOnClickListener{
            onBackPressed()
        }

        //초기화
        buttonGo.isEnabled = false
        //투명도 주기
        val cat1D = cat1.compoundDrawables
        cat1D[0].alpha = 50

        //고양이가 선택되면
        var id: Int = 0
        radioGroupCat.setOnCheckedChangeListener { group, checkedId ->
            val radio: RadioButton = findViewById(checkedId)
            id = radio.id
            //버튼 활성화
            buttonGo.isEnabled = true
            buttonGo.setBackgroundResource(R.drawable.info_next_after)
        }

        //탭 이벤트


        //다음 버튼 누르면
        buttonGo.setOnClickListener{
            //FIXME: d("***",id.toString()) 로 넘겨받기~
            //화면 이동
            var intent = Intent(this,Info5Activity::class.java)
            startActivity(intent)
        }

    }

}
