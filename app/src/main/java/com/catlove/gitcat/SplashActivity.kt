package com.catlove.gitcat

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    // This is the loading time of the splash screen
    private val SPLASH_TIME_OUT:Long = 3000 // 1 sec
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val settings: SharedPreferences = getSharedPreferences("gitcat", MODE_PRIVATE)

        Handler().postDelayed({
            // This method will be executed once the timer is over
            // Start your app main activity

            if(settings.getString("devCareer","")!!.isEmpty()){//정보입력 안한 사람
                //d("*+*+",settings.getString("devCareer","ddd"))
                if(settings.getString("token","")!!.isNotEmpty()){ //토큰이 있다면 -> 회원인데 휴대폰 바꾼 사람이 로그인 한 이후 앱 들어올 때
                    startActivity(Intent(this,HomeActivity::class.java))
                }else{ //토큰이 없다면 -> 새로운 사람
                    startActivity(Intent(this,MainActivity::class.java))
                }
            }else{//정보입력 한 사람
                startActivity(Intent(this,HomeActivity::class.java))
            }

            val editor: SharedPreferences.Editor = settings.edit()
            editor.putBoolean("doAPI",true)
            editor.apply()

            // close this activity
            finish()
        }, SPLASH_TIME_OUT)
    }
}
