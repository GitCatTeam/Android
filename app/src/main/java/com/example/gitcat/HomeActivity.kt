package com.example.gitcat

import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_home.*
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import android.view.View
import com.google.android.material.tabs.TabLayout
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.util.Log
import android.widget.Toast
import com.auth0.android.jwt.JWT
import com.example.gitcat.model.RefreshTokenModel
import com.example.gitcat.retrofit.RetrofitCreator
import com.google.android.material.shadow.ShadowViewDelegate
import com.google.android.material.shape.MaterialShapeDrawable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.fragment.app.FragmentManager


class HomeActivity : AppCompatActivity() {

    private var fragmentManager: FragmentManager? = null
    private var cf: Fragment? = null
    private var hf: Fragment? = null
    private var rf: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        fragmentManager = supportFragmentManager

        init()
        //툴바 적용
        //setSupportActionBar(findViewById(R.id.toolbar))
        //getSupportActionBar()?.title = ""
        //var actionBar = supportActionBar
    }

    override fun onBackPressed() {
        super.onBackPressed()
        System.exit(0)
    }

    private fun loadFragment(fragment: Fragment,tag: String) {
        // load fragment
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(tag)
        transaction.commit()
    }

    private fun loadFragment2(fragment: Fragment, tag: String){
        fragmentManager!!.beginTransaction().add(R.id.container, fragment,tag).commit()
    }

    private fun init(){
        //초기화
        hf = HomeFragment()
        loadFragment(HomeFragment(),"home")

        navigationView.selectedItemId = R.id.nav_home

        navigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_cal->{
                    //loadFragment(CalendarFragment(),"calendar")

                    if(cf == null) {
                        cf = CalendarFragment()
                        loadFragment2(cf!!,"calendar")
                    }
                    if(cf != null) fragmentManager!!.beginTransaction().show(cf!!).commit()
                    if(hf != null) fragmentManager!!.beginTransaction().hide(hf!!).commit()
                    if(rf != null) fragmentManager!!.beginTransaction().hide(rf!!).commit()

                    //actionBar!!.title = "커밋달력"
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_home->{
                    //loadFragment(HomeFragment(),"home")

                    if(hf == null) {
                        hf = HomeFragment()
                        loadFragment2(hf!!,"home")
                    }
                    if(cf != null) fragmentManager!!.beginTransaction().hide(cf!!).commit()
                    if(hf != null) fragmentManager!!.beginTransaction().show(hf!!).commit()
                    if(rf != null) fragmentManager!!.beginTransaction().hide(rf!!).commit()

                    //actionBar!!.title = ""
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_report->{
                    //loadFragment(ReportFragment(),"report")

                    if(rf == null) {
                        rf = ReportFragment()
                        loadFragment2(rf!!,"report")
                    }
                    if(cf != null) fragmentManager!!.beginTransaction().hide(cf!!).commit()
                    if(hf != null) fragmentManager!!.beginTransaction().hide(hf!!).commit()
                    if(rf != null) fragmentManager!!.beginTransaction().show(rf!!).commit()

                    //actionBar!!.title = "레포트"
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }
    }


    //액션버튼 메뉴 액션바에 집어 넣기
//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        menuInflater.inflate(R.menu.action_bar,menu)
//        return true
//    }

    //액션버튼 클릭 했을 때
//    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
//        when(item?.itemId){
//            R.id.settings -> {
//                //설정 버튼을 눌렀을 때
//                return super.onOptionsItemSelected(item)
//            }
//            R.id.diary -> {
//                return super.onOptionsItemSelected(item)
//            }
//            else -> return super.onOptionsItemSelected(item)
//        }
//    }
}
