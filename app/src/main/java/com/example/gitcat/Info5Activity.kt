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
import com.bumptech.glide.Glide
import com.example.gitcat.model.AddCatModel
import com.example.gitcat.retrofit.RetrofitCreator
import kotlinx.android.synthetic.main.activity_chart.*
import kotlinx.android.synthetic.main.activity_info5.*
import kotlinx.android.synthetic.main.activity_info5.backButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



class Info5Activity : AppCompatActivity() {

    private lateinit var keyboardVisibilityUtils: KeyboardVisibilityUtils

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        setContentView(R.layout.activity_info5)

        backButton.setOnClickListener{
            onBackPressed()
        }

//        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

//        val lp = window.attributes
//        lp.flags = lp.flags and WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN.inv()
//        window.attributes = lp
//
        //TODO:키보드 스크롤아 왜 안되니
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN)

        keyboardVisibilityUtils = KeyboardVisibilityUtils(window,
            onShowKeyboard = { keyboardHeight ->
                sv_root.run {
                    smoothScrollTo(scrollX, scrollY + keyboardHeight)
                }
            })


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
                val text = s.toString()
                //calcText.text= "${text.length}자"
            }
            override fun afterTextChanged(s: Editable) {
                //TODO: 텍스트 다시 0일 때 enable false
                buttonGo.isEnabled = true
                buttonGo.setBackgroundResource(R.drawable.info_next_after)

            }
        })

        buttonGo.setOnClickListener{

            editor.putString("catName",editCatName.text.toString())
            editor.apply()

            val cat = AddCatModel(settings.getInt("catId",0),editCatName.text.toString())
            val call: Call<AddCatModel> = RetrofitCreator.service.postCats(settings.getString("token",""),cat)
            call.enqueue(
                object : Callback<AddCatModel> {
                    override fun onFailure(call: Call<AddCatModel>, t: Throwable) {
                        Log.e("*+*+", "error: $t")
                        showErrorPopup(t.toString(),this@Info5Activity)
                    }

                    override fun onResponse(
                        call: Call<AddCatModel>,
                        response: Response<AddCatModel>
                    ) {
                        if(response.isSuccessful){
                            val data = response.body()!!

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
