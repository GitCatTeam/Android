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
import androidx.core.app.ComponentActivity.ExtraData
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


class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        newtoken()
        //툴바 적용
        //setSupportActionBar(findViewById(R.id.toolbar))
        //getSupportActionBar()?.title = ""
        //var actionBar = supportActionBar
    }

    override fun onResume() {
        super.onResume()
        newtoken()
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

    private fun init(){
        //초기화
        loadFragment(HomeFragment(),"home")
        navigationView.selectedItemId = R.id.nav_home

        navigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_cal->{
                    loadFragment(CalendarFragment(),"calendar")
                    //actionBar!!.title = "커밋달력"
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_home->{
                    loadFragment(HomeFragment(),"home")
                    //actionBar!!.title = ""
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_report->{
                    loadFragment(ReportFragment(),"report")
                    //actionBar!!.title = "레포트"
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }
    }

    private fun newtoken(){
        val settings: SharedPreferences = getSharedPreferences("gitcat", AppCompatActivity.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = settings.edit()

        if(Date(settings.getLong("expire", 0)) < Calendar.getInstance().time){ //현재시간이 만료시간을 뛰어넘을때
            val call: Call<RefreshTokenModel> = RetrofitCreator.service.getRefreshToken(settings.getString("refreshToken",""))
            call.enqueue(
                object : Callback<RefreshTokenModel> {
                    override fun onFailure(call: Call<RefreshTokenModel>, t: Throwable) {
                        Log.e("*+*+", "error: $t")
                        showErrorPopup(t.toString(),this@HomeActivity)
                    }

                    override fun onResponse(
                        call: Call<RefreshTokenModel>,
                        response: Response<RefreshTokenModel>
                    ) {
                        if(response.isSuccessful){
                            val data = response.body()!!

                            editor.putString("token",data.data.accessToken)
                            editor.putString("refreshToken",data.data.refreshToken)

                            val jwt = JWT(data.data.accessToken)
                            val issuedAt = jwt.issuedAt//시작
                            val expiresAt = jwt.expiresAt//마감

                            editor.putLong("expire",expiresAt!!.time)
                            editor.apply()

                            init()
                        }else{
                            showErrorPopup("["+response.code().toString()+"] "+response.message(),this@HomeActivity)
                        }
                    }
                }
            )
        }else{
            init()
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
