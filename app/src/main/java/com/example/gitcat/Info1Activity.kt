package com.example.gitcat

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.Log.d
import android.view.View
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.DatePicker
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_info1.*
import java.util.*


class Info1Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        setContentView(R.layout.activity_info1)

        //초기화
        buttonGo.isEnabled = false
        var radioSex: String = ""
        var birth: String = ""
        val settings: SharedPreferences = getSharedPreferences("gitcat", MODE_PRIVATE)
        val editor: SharedPreferences.Editor = settings.edit()
        emailText.setText(settings.getString("githubId",""))
        Glide.with(this).load(settings.getString("profileImg","")).apply(RequestOptions.circleCropTransform()).into(profileImg)

        backButton.setOnClickListener{
            onBackPressed()
        }

        buttonGo.setOnClickListener{
            if(radioSex.equals("여자")){
                editor.putString("gender","여자")
            }else if(radioSex.equals("남자")){
                editor.putString("gender","남자")
            }else{
                editor.putString("gender","기타")
            }

            //두번째 화면인 datePicker 등장
            firstLayout.visibility = View.GONE
            secondLayout.visibility = View.VISIBLE
            val animation = AnimationUtils.loadAnimation(this, R.anim.fade_in)
            secondLayout.startAnimation(animation)
        }

        buttonGo2.setOnClickListener {
            editor.putString("birth",birth)
            //화면 이동
            var intent = Intent(this,Info3Activity::class.java)
            startActivity(intent)
        }

        //성별이 클릭되면
        radioGroupSex.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { group, checkedId ->
                val radio: RadioButton = findViewById(checkedId)
                radioSex = radio.text.toString()

                //버튼 활성화
                buttonGo.isEnabled = true
                buttonGo.setBackgroundResource(R.drawable.info_next_after)
            })

        //날짜 받아오는 부분
        val datePicker = findViewById<DatePicker>(R.id.date_picker)
        val today = Calendar.getInstance()

        datePicker.init(today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(Calendar.DAY_OF_MONTH)
        )
        { view, year, monthOfYear, dayOfMonth ->
            var month = monthOfYear + 1
            var monthString: String = ""
            var dayOfMonthString: String = ""
            if(month<10){
                monthString = "0"+month.toString()
                if(dayOfMonth<10){
                    dayOfMonthString = "0"+dayOfMonth.toString()
                }else{
                    dayOfMonthString = dayOfMonth.toString()
                }
            }else{
                monthString = month.toString()
                if(dayOfMonth<10){
                    dayOfMonthString = "0"+dayOfMonth.toString()
                }else{
                    dayOfMonthString = dayOfMonth.toString()
                }
            }
            val msg = "$year-$monthString-$dayOfMonthString"
            birth = msg
        }

    }
}
