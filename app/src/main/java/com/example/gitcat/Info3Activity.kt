package com.example.gitcat

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.util.Log.d
import android.view.WindowManager
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_info3.*
import kotlinx.android.synthetic.main.activity_info3.backButton
import kotlinx.android.synthetic.main.activity_info3.buttonGo

class Info3Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        setContentView(R.layout.activity_info3)

        //초기화
        buttonGo.isEnabled = false
        val settings: SharedPreferences = getSharedPreferences("gitcat", MODE_PRIVATE)
        val editor: SharedPreferences.Editor = settings.edit()
        var devCareer: String = ""

        backButton.setOnClickListener{
            onBackPressed()
        }

        buttonGo.setOnClickListener{
            editor.putString("devCareer",devCareer)
            editor.apply()
            //화면 이동

            //만약 isfirst가 true 라면 HomeActivity로, 아니라면 Info4Activity로
            if(settings.getString("isFirst","").compareTo("true")==0){
                var intent = Intent(this,HomeActivity::class.java)
                startActivity(intent)
            }else{
                var intent = Intent(this,Info4Activity::class.java)
                startActivity(intent)
            }
        }

        //기간이 클릭되면
        radioGroupLong.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { group, checkedId ->

                val radio: RadioButton = findViewById(checkedId)
                devCareer = radio.text.toString()

                //버튼 활성화
                buttonGo.isEnabled = true
                buttonGo.setBackgroundResource(R.drawable.info_next_after)
            })
    }
}
