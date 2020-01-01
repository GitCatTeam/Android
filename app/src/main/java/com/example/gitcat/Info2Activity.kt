package com.example.gitcat

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_info2.*

class Info2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info2)

        buttonGo.setOnClickListener{

            //화면 이동
            var intent = Intent(this,Info3Activity::class.java)
            startActivity(intent)
        }
    }
}
