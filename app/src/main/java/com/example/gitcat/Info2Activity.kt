package com.example.gitcat

import android.content.Intent
import android.os.Bundle
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_info2.*
import java.util.*

//FIXME: 안쓸예정
class Info2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info2)

        backButton.setOnClickListener{
            onBackPressed()
        }

        //날짜 받아오는 부분
        val datePicker = findViewById<DatePicker>(R.id.date_picker)
        val today = Calendar.getInstance()

        datePicker.init(today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(Calendar.DAY_OF_MONTH)
        )
        { view, year, monthOfYear, dayOfMonth ->
            val month = monthOfYear + 1
            val msg = "Selected Date is $dayOfMonth/$month/$year"
            Toast.makeText(this@Info2Activity, msg, Toast.LENGTH_SHORT).show()
        }

        buttonGo.setOnClickListener{

            //화면 이동
            var intent = Intent(this,Info3Activity::class.java)
            startActivity(intent)

            //Toast.makeText(this@Info2Activity, msg, Toast.LENGTH_LONG).show()
        }

    }
}

