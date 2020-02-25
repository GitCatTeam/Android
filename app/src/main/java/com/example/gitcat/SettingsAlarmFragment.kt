package com.example.gitcat

import android.os.Bundle
import android.util.Log.d
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference

class SettingsAlarmFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preferences_alarm)

        val app_alarm = findPreference("app_alarm") as Preference
        val app_alarm_list = findPreference("app_alarm_list") as ListPreference


        app_alarm.setOnPreferenceChangeListener { preference, newValue ->
            if(newValue == true){//활성화
                app_alarm_list.isEnabled = true
            }else if(newValue == false){
                app_alarm_list.isEnabled = false
            }
            true
        }

        app_alarm_list.setOnPreferenceChangeListener { preference, newValue ->
            if(newValue == "1"){
                app_alarm_list.title = "벨소리"
            }
            else if(newValue == "2"){
                app_alarm_list.title = "진동"
            }
            true
        }
    }

    override fun onResume() {
        super.onResume()
    }
}
