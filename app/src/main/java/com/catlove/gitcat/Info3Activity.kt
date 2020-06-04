package com.catlove.gitcat

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.util.Log.d
import android.view.WindowManager
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import com.catlove.gitcat.model.InfoModel
import com.catlove.gitcat.retrofit.RetrofitCreator
import kotlinx.android.synthetic.main.activity_info3.*
import kotlinx.android.synthetic.main.activity_info3.backButton
import kotlinx.android.synthetic.main.activity_info3.buttonGo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

            d("*+*+",settings.getString("token","토큰없어"))

            //화면 이동
            val info = InfoModel(settings.getString("githubId",""),settings.getString("gender",""),settings.getString("birth",""),settings.getString("devCareer",""))
            val call: Call<InfoModel> = RetrofitCreator.service.putInfo(settings.getString("token",""),info)
            call.enqueue(
                object : Callback<InfoModel> {
                    override fun onFailure(call: Call<InfoModel>, t: Throwable) {
                        Log.e("*+*+", "error: $t")
                        showErrorPopup("재로그인을 해주세요!",this@Info3Activity)
                    }

                    override fun onResponse(
                        call: Call<InfoModel>,
                        response: Response<InfoModel>
                    ) {
                        if(response.isSuccessful){
                            val data = response.body()!!

                            //이 화면을 거친다는 것 자체가 첫 로그인이라는 것. 튜토리얼로 이동
                            var intent = Intent(this@Info3Activity,HomeActivity::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)
                        }else{
                            if(response.code()>=500){
                                showErrorPopup("[네트워크 오류] 재로그인을 해주세요!",this@Info3Activity)
                            }else{
                                showErrorPopup("["+response.code().toString()+" 오류] "+"재로그인을 해주세요!",this@Info3Activity)
                            }
                        }
                    }
                }
            )

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
