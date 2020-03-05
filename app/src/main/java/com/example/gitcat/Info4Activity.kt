package com.example.gitcat

import android.content.Intent
import android.content.res.Resources
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log.d
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import com.yuxingxin.library.MultiRadioGroup
import kotlinx.android.synthetic.main.activity_info4.*
import android.view.ViewGroup
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.util.Log
import com.example.gitcat.model.CatsModel
import com.example.gitcat.retrofit.RetrofitCreator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection
import java.net.URL
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable

class Info4Activity : AppCompatActivity(), OnDataPass{

    private var catId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info4)

        backButton.setOnClickListener{
            onBackPressed()
        }

        //초기화
        buttonGo.isEnabled = false

        //탭 이벤트
        ////탭 사이 벌리기
        val tab = tabLayout.getChildAt(0) as ViewGroup
        for (i in 0 until tab.childCount - 1) {
            val v = tab.getChildAt(i)
            val params = v.layoutParams as ViewGroup.MarginLayoutParams
            params.rightMargin = 20
        }

        val adapter = ChooseCatAdapter(supportFragmentManager, tabLayout.tabCount)
        pager.adapter = adapter

        pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(p0: TabLayout.Tab?) {}
            override fun onTabUnselected(p0: TabLayout.Tab?) {}
            override fun onTabSelected(tab: TabLayout.Tab?) {
                pager.currentItem = tab!!.position
            }

        })

        /*API*/
        val call: Call<CatsModel> = RetrofitCreator.service.getCats("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJHaXRDYXQiLCJzdWIiOiJ5ZWppOTE3NSIsImlhdCI6MTU4MjY5ODA3MTk0NiwiZXhwIjoxNTgyNzg0NDcxOTQ2fQ.v6vUTmcT3EQblA2sU8oe8kBYnNc0srCHeNtuQSspUmI")
        call.enqueue(
            object : Callback<CatsModel> {
                override fun onFailure(call: Call<CatsModel>, t: Throwable) {
                    Log.e("*+*+", "error: $t")
                }

                override fun onResponse(
                    call: Call<CatsModel>,
                    response: Response<CatsModel>
                ) {
                    if(response.isSuccessful){
                        val cat = response.body()!!
                        d("*+*+", cat.message)

                        for(data in cat.data.common){
                            var img = data.profileImg

                        }

                    }
                }
            }
        )

        //다음 버튼 누르면
        buttonGo.setOnClickListener{
            //FIXME: d("***",catId.toString()) 로 넘겨받기~
            //화면 이동
            var intent = Intent(this,Info5Activity::class.java)
            startActivity(intent)
        }

    }

    override fun onDataPass(catId: Int) {
        d("*+*+",""+catId)
        this.catId = catId
        if(catId != 0){
            //버튼 활성화
            buttonGo.isEnabled = true
            buttonGo.setBackgroundResource(R.drawable.info_next_after)
        }
    }

    fun drawableFromUrl(url: String): Drawable {
        var x: Bitmap

        var connection = URL(url).openConnection() as HttpURLConnection
        connection.connect()
        val input = connection.inputStream

        x = BitmapFactory.decodeStream(input)
        return BitmapDrawable(Resources.getSystem(), x)
    }

}

