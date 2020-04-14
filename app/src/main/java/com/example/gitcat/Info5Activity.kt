package com.example.gitcat

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Log.d
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.gitcat.model.InfoModel
import com.example.gitcat.retrofit.RetrofitCreator
import kotlinx.android.synthetic.main.activity_info5.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Info5Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        setContentView(R.layout.activity_info5)

        backButton.setOnClickListener{
            onBackPressed()
        }

        //초기화
        buttonGo.isEnabled = false
        val settings: SharedPreferences = getSharedPreferences("gitcat", MODE_PRIVATE)
        val editor: SharedPreferences.Editor = settings.edit()

        editCatName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence, start: Int, count: Int, before: Int) {
                val text = s.toString()
                //calcText.text= "${text.length}자"
            }
            override fun afterTextChanged(s: Editable) {
                buttonGo.isEnabled = true
                buttonGo.setBackgroundResource(R.drawable.info_next_after)

            }
        })

        buttonGo.setOnClickListener{

            editor.putString("name",editCatName.text.toString())
            editor.apply()

            d("*+*+",editCatName.text.toString())
            d("*+*+",settings.getString("gender","0"))
            d("*+*+",settings.getString("birth","1"))
            d("*+*+",settings.getString("devCareer","2"))
            d("*+*+",settings.getString("token","3"))

            val info = InfoModel(editCatName.text.toString(),settings.getString("gender",""),settings.getString("birth",""),settings.getString("devCareer",""))
            val call: Call<InfoModel> = RetrofitCreator.service.putInfo(settings.getString("token",""),info)
            call.enqueue(
                object : Callback<InfoModel> {
                    override fun onFailure(call: Call<InfoModel>, t: Throwable) {
                        Log.e("*+*+", "error: $t")
                        showErrorPopup(t.toString(),this@Info5Activity)
                    }

                    override fun onResponse(
                        call: Call<InfoModel>,
                        response: Response<InfoModel>
                    ) {
                        if(response.isSuccessful){
                            val data = response.body()!!

                            //화면 이동
                            var intent = Intent(this@Info5Activity,HomeActivity::class.java)
                            startActivity(intent)
                        }else{
                            showErrorPopup("["+response.code().toString()+"] "+response.message(),this@Info5Activity)
                        }
                    }
                }
            )

        }

    }
}
