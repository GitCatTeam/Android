package com.example.gitcat

import android.content.Intent
import android.content.res.Resources
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log.d
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
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.*

class Info4Activity : AppCompatActivity(){

    private var catId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        setContentView(R.layout.activity_info4)

        val fragmentAdapter = ChooseCatAdapter(supportFragmentManager,2)
        vp_information_cat.adapter = fragmentAdapter
        tl_information_cat.setupWithViewPager(vp_information_cat)

        img_back_btn.setOnClickListener {
            onBackPressed()
        }

//TODO: tab margin 주기

//        val choose_cat_tab:View=(this.getSystemService(android.content.Context.LAYOUT_NFLATER_SERVICE) as LayoutInflater).inflate(R.layout.choose_cat_tab,null,false)
//
//
//        tl_information_cat.getTabAt(0)?.customView=choose_cat_tab.findViewById(R.id.nav_choose_cat_basic) as RelativeLayout
//        tl_information_cat.getTabAt(1)?.customView=choose_cat_tab.findViewById(R.id.nav_choose_cat_special) as RelativeLayout
    }

}



