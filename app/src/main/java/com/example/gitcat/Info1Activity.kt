package com.example.gitcat

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_info1.*

class Info1Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info1)

        buttonGo.setOnClickListener{

            //화면 이동
            var intent = Intent(this,Info2Activity::class.java)
            startActivity(intent)
        }
    }
}
