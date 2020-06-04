package com.catlove.gitcat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_settings_alarm.*

class SettingsAlarmActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings_alarm)

        alarmBack.setOnClickListener {
            onBackPressed()
        }
        supportFragmentManager.beginTransaction().replace(R.id.settingsAlarmFrame,SettingsAlarmFragment()).commit()
    }
}
