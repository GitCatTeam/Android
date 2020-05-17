package com.example.gitcat

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

            //SharedPreference가 있다면 HomeActivity로, 없다면 MainActivity로
            if(settings.getString("devCareer","").isEmpty()){
                startActivity(Intent(this,MainActivity::class.java))
            }else{
                startActivity(Intent(this,HomeActivity::class.java))
            }

            // close this activity
            finish()
        }, SPLASH_TIME_OUT)
    }
}
