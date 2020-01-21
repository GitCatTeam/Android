package com.example.gitcat

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_info3.*
import kotlinx.android.synthetic.main.activity_info3.backButton
import kotlinx.android.synthetic.main.activity_info3.buttonGo

class Info3Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info3)

        backButton.setOnClickListener{
            onBackPressed()
        }

        buttonGo.setOnClickListener{
            //화면 이동
            var intent = Intent(this,Info4Activity::class.java)
            startActivity(intent)
        }

        //기간이 클릭되면
        radioGroupLong.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { group, checkedId ->
                //TODO: 버튼 활성화
                val radio: RadioButton = findViewById(checkedId)
                //Toast.makeText(applicationContext," On checked change : ${radio.text}",
                //    Toast.LENGTH_SHORT).show()

                //버튼 활성화
                buttonGo.isEnabled = true
                //buttonGo.setBackgroundResource(R.drawable.info_next)
                //TODO: 수정해야함
                buttonGo.setBackgroundColor(Color.parseColor("#88cdf6"))
            })
    }
}
