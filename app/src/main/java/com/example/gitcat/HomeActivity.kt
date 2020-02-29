package com.example.gitcat

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
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



class HomeActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        //툴바 적용
        //setSupportActionBar(findViewById(R.id.toolbar))
        //getSupportActionBar()?.title = ""
        //var actionBar = supportActionBar

        //초기화
        loadFragment(HomeFragment())
        navigationView.selectedItemId = R.id.nav_home

        navigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_cal->{
                    loadFragment(CalendarFragment())
                    //actionBar!!.title = "커밋달력"
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_home->{
                    loadFragment(HomeFragment())
                    //actionBar!!.title = ""
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_report->{
                    loadFragment(ReportFragment())
                    //actionBar!!.title = "레포트"
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        System.exit(0)
    }

    private fun loadFragment(fragment: Fragment) {
        // load fragment
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
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
