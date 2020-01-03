package com.example.gitcat

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_info3.*

class Info3Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info3)

        buttonGo.setOnClickListener{

            //화면 이동
            var intent = Intent(this,Info4Activity::class.java)
            startActivity(intent)
        }
    }
}
