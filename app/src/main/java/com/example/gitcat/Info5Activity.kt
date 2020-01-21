package com.example.gitcat

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_info5.*

class Info5Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info5)

        backButton.setOnClickListener{
            onBackPressed()
        }

        editCatName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence, start: Int, count: Int, before: Int) {
                val text = s.toString()
                //calcText.text= "${text.length}자"
            }
            override fun afterTextChanged(s: Editable) {
                okName.visibility = View.VISIBLE

                buttonGo.isEnabled = true
                //buttonGo.setBackgroundResource(R.drawable.info_next)
                //TODO: 수정해야함
                buttonGo.setBackgroundColor(Color.parseColor("#88cdf6"))
            }
        })

        buttonGo.setOnClickListener{
            //화면 이동
            var intent = Intent(this,HomeActivity::class.java)
            startActivity(intent)
        }

    }
}
