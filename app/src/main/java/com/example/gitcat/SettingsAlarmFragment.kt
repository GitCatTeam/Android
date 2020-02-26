package com.example.gitcat

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.util.Log.d
import android.content.SharedPreferences
import androidx.preference.*


class SettingsAlarmFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preferences_alarm)

        val app_alarm = findPreference("app_alarm") as Preference
        val app_alarm_list = findPreference("app_alarm_list") as ListPreference

        val prefs = PreferenceManager.getDefaultSharedPreferences(activity!!)
        val is_alarm = prefs.getBoolean("app_alarm", false)
        if(is_alarm == true){//활성화 상태이면
            app_alarm_list.isEnabled = true
            //현재 상태 summary에 띄움

            val summary = SpannableStringBuilder(app_alarm_list.entry)
            summary.setSpan(ForegroundColorSpan(Color.parseColor("#88cdf6")), 0, summary.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            app_alarm.summary = summary
        }else{
            app_alarm_list.isEnabled = false
            app_alarm.summary = "앱에서 보내는 다양한 푸시알림을 받습니다."
        }

        /*값 변경 시작할 때*/
        app_alarm.setOnPreferenceChangeListener { preference, newValue ->
            if(newValue == true){//활성화
                app_alarm_list.isEnabled = true
                //현재 상태 summary에 띄움

                val summary = SpannableStringBuilder(app_alarm_list.entry)
                summary.setSpan(ForegroundColorSpan(Color.parseColor("#88cdf6")), 0, summary.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                app_alarm.summary = summary
            }else if(newValue == false){
                app_alarm_list.isEnabled = false
                app_alarm.summary = "앱에서 보내는 다양한 푸시알림을 받습니다."
            }
            true
        }

        app_alarm_list.setOnPreferenceChangeListener { preference, newValue ->
            if(newValue == "1"){
                app_alarm_list.title = "벨소리"

                val summary = SpannableStringBuilder("벨소리")
                summary.setSpan(ForegroundColorSpan(Color.parseColor("#88cdf6")), 0, summary.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                app_alarm.summary = summary
            }
            else if(newValue == "2"){
                app_alarm_list.title = "진동"

                val summary = SpannableStringBuilder("진동")
                summary.setSpan(ForegroundColorSpan(Color.parseColor("#88cdf6")), 0, summary.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                app_alarm.summary = summary
            }
            true
        }
    }

    override fun onResume() {
        super.onResume()

    }
}
