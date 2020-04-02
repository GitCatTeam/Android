package com.example.gitcat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        backButton.setOnClickListener{
            onBackPressed()
        }

        supportFragmentManager.beginTransaction().replace(R.id.settingsFrame,SettingsFragment()).commit()


    }
}
