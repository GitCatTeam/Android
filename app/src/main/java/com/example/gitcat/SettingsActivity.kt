package com.example.gitcat

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        backButton.setOnClickListener{
            onBackPressed()
        }

        val settings: SharedPreferences = getSharedPreferences("gitcat", MODE_PRIVATE)
        Glide.with(this).load(settings.getString("profileImg","")).apply(RequestOptions.circleCropTransform()).into(profile)
        //username.text = settings.getString("catName","")
        useremail.text = settings.getString("githubId","")

        supportFragmentManager.beginTransaction().replace(R.id.settingsFrame,SettingsFragment()).commit()

    }
}
