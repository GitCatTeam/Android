package com.example.gitcat

import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_info4.*

class Info4Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info4)

        backButton.setOnClickListener{
            onBackPressed()
        }

        buttonGo.setOnClickListener{
            //화면 이동
            var intent = Intent(this,Info5Activity::class.java)
            startActivity(intent)
        }

        //고양이가 선택되면
        radioGroupCat.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { group, checkedId ->
                //TODO: 버튼 활성화
                val radio: RadioButton = findViewById(checkedId)
                Toast.makeText(applicationContext," On checked change : ${radio.text}",
                    Toast.LENGTH_SHORT).show()

                //버튼 활성화
                buttonGo.isEnabled = true
                //buttonGo.setBackgroundResource(R.drawable.info_next)
                //TODO: 수정해야함
                buttonGo.setBackgroundColor(Color.parseColor("#88cdf6"))
            })
    }
}
