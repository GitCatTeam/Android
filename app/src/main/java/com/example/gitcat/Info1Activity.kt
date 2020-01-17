package com.example.gitcat

import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.DatePicker
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_info1.*
import java.util.*

class Info1Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info1)

        //초기화
        buttonGo.isEnabled = false

        backButton.setOnClickListener{
            onBackPressed()
        }

        buttonGo.setOnClickListener{
            //두번째 화면인 datePicker 등장
            firstLayout.visibility = View.GONE
            secondLayout.visibility = View.VISIBLE
            val animation = AnimationUtils.loadAnimation(this, R.anim.fade_in)
            secondLayout.startAnimation(animation)

        }

        buttonGo2.setOnClickListener {
            //화면 이동
            var intent = Intent(this,Info3Activity::class.java)
            startActivity(intent)
        }

        //성별이 클릭되면
        radioGroupSex.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { group, checkedId ->
                //눌린 값 받아옴
                val radio: RadioButton = findViewById(checkedId)
                Toast.makeText(applicationContext," On checked change : ${radio.text}",
                    Toast.LENGTH_SHORT).show()

                //버튼 활성화
                buttonGo.isEnabled = true

//                buttonGo.setBackgroundResource(R.drawable.info_next)
//                //TODO: 수정해야함
                buttonGo.setBackgroundColor(Color.parseColor("#88cdf6"))
            })

        //날짜 받아오는 부분
        val datePicker = findViewById<DatePicker>(R.id.date_picker)
        val today = Calendar.getInstance()

        datePicker.init(today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(Calendar.DAY_OF_MONTH)
        )
        { view, year, monthOfYear, dayOfMonth ->
            val month = monthOfYear + 1
            val msg = "Selected Date is $dayOfMonth/$month/$year"
            Toast.makeText(this@Info1Activity, msg, Toast.LENGTH_SHORT).show()
        }

    }
}
