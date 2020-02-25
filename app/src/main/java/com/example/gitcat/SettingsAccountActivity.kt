package com.example.gitcat

import android.os.Bundle
import android.os.PersistableBundle
import android.preference.PreferenceActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceFragmentCompat
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