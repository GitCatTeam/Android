package com.catlove.gitcat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_settings_account.*

class SettingsAccountActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings_account)

        accountBack.setOnClickListener {
            onBackPressed()
        }

        supportFragmentManager.beginTransaction().replace(R.id.settingsAccountFrame,SettingsAccountFragment()).commit()


    }


}