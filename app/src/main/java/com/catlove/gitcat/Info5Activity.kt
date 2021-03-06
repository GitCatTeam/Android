package com.catlove.gitcat

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.catlove.gitcat.model.AddCatModel
import com.catlove.gitcat.retrofit.RetrofitCreator
import kotlinx.android.synthetic.main.activity_info5.*
import kotlinx.android.synthetic.main.activity_info5.backButton
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Info5Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        setContentView(R.layout.activity_info5)

        backButton.setOnClickListener{
            onBackPressed()
        }

        //초기화
        buttonGo.isEnabled = false
        val settings: SharedPreferences = getSharedPreferences("gitcat", MODE_PRIVATE)
        val editor: SharedPreferences.Editor = settings.edit()

        //고양이를 선택했다면
        settings.getString("catImageUrl",null).let {
            Glide.with(this).load(it).into(myCatImg)
        }
        
        editCatName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence, start: Int, count: Int, before: Int) {
                //calcText.text= "${text.length}자"
            }
            override fun afterTextChanged(s: Editable) {
                buttonGo.isEnabled = true
                buttonGo.setBackgroundResource(R.drawable.info_next_after)

            }
        })

        buttonGo.setOnClickListener{

            editor.putString("catName",editCatName.text.toString())
            editor.apply()

            val cat = AddCatModel(settings.getInt("catId",0),editCatName.text.toString())
            val call: Call<AddCatModel> = RetrofitCreator.service.postCats(settings.getString("token","")!!,cat)
            call.enqueue(
                object : Callback<AddCatModel> {
                    override fun onFailure(call: Call<AddCatModel>, t: Throwable) {
                        Log.e("*+*+", "error: $t")
                        showErrorPopup("재로그인을 해주세요!",this@Info5Activity)
                    }

                    override fun onResponse(
                        call: Call<AddCatModel>,
                        response: Response<AddCatModel>
                    ) {
                        if(response.isSuccessful){
                            //val data = response.body()!!

                            val editor: SharedPreferences.Editor = settings.edit()
                            editor.putBoolean("doAPI",true)
                            editor.apply()

                            var intent = Intent(this@Info5Activity,HomeActivity::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)
                        }else{
                            if(response.code()==503){
                                ServerCheckPopup(this@Info5Activity)
                            }else if(response.code()>=500){
                                showErrorPopup("[네트워크 오류] 재로그인을 해주세요!",this@Info5Activity)
                            }else{
                                showErrorPopup("재로그인을 해주세요!",this@Info5Activity)
                            }
                        }
                    }
                }
            )

        }

    }

}
