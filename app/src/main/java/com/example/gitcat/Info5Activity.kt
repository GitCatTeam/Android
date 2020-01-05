package com.example.gitcat

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_info5.*

class Info5Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info5)

        backButton.setOnClickListener{
            onBackPressed()
        }

        buttonGo.setOnClickListener{
            //화면 이동
            var intent = Intent(this,HomeActivity::class.java)
            startActivity(intent)
        }

    }
}
