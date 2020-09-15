package com.catlove.gitcat

import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.util.Log.d
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.*
import com.catlove.gitcat.model.DeviceIdModel
import com.catlove.gitcat.model.DeviceTokenModel
import com.catlove.gitcat.retrofit.RetrofitCreator
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.InstanceIdResult
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SettingsAlarmFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preferences_alarm)

        val settings: SharedPreferences = requireActivity().getSharedPreferences("gitcat",
            AppCompatActivity.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = settings.edit()

        val app_alarm = findPreference("app_alarm") as SwitchPreference
//        val app_alarm_list = findPreference("app_alarm_list") as ListPreference

        val prefs = PreferenceManager.getDefaultSharedPreferences(activity!!)
        val is_alarm = prefs.getBoolean("app_alarm", true)
//        app_alarm_list.title = app_alarm_list.entry

        if(settings.getBoolean("alarm",true)==true){
            app_alarm.isChecked = true
        }else{
            app_alarm.isChecked = false
        }
//        if(is_alarm == true){//활성화 상태이면
//            app_alarm_list.isEnabled = true
//            app_alarm.summary = app_alarm_list.entry
//        }else{
//            app_alarm_list.isEnabled = false
//            app_alarm.summary = "앱에서 보내는 다양한 푸시알림을 받습니다."
//
//            val alarmList = findPreference("app_alarm_list") as Preference
//            val alarmTitle = SpannableStringBuilder(app_alarm_list.entry)
//            alarmTitle.setSpan(ForegroundColorSpan(Color.parseColor("#b6b6b6")),0,alarmTitle.length,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
//            alarmList.title = alarmTitle
//        }

        /*값 변경 시작할 때*/
        app_alarm.setOnPreferenceChangeListener { preference, newValue ->
            if(newValue == true){
                if(settings.getString("deviceToken","")==null || settings.getString("androidid","")==null){//디바이스토큰이랑 androidid가 없을 경우
                    //디바이스토큰 넣어주기
                    var deviceToken : String = ""
                    FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener {
                        deviceToken = it.token
                    }
                    //uuid(androidid) 찾기
                    var androidId = "" + android.provider.Settings.Secure.getString(context!!.contentResolver,android.provider.Settings.Secure.ANDROID_ID)
                    editor.putString("androidId",androidId)
                    editor.putString("deviceToken",deviceToken)
                    editor.commit()
                }
                //활성화
                val dt = DeviceTokenModel(settings.getString("deviceToken","")!!,settings.getString("androidId","")!!)
                val call: Call<DeviceTokenModel> = RetrofitCreator.service.putDeviceToken(settings.getString("token","")!!,dt)
                call.enqueue(
                    object : Callback<DeviceTokenModel> {
                        override fun onFailure(call: Call<DeviceTokenModel>, t: Throwable) {
                            Log.e("*+*+", "error: $t")
                            showErrorPopup("재로그인을 해주세요!",context!!)
                        }

                        override fun onResponse(
                            call: Call<DeviceTokenModel>,
                            response: Response<DeviceTokenModel>
                        ) {
                            if(response.isSuccessful){
                                Toast.makeText(context!!,getString(R.string.settings_alarm_on),Toast.LENGTH_SHORT).show()
                                editor.putBoolean("alarm",true)
                                editor.commit()
                            }else{
                                if(response.code()==503){
                                    ServerCheckPopup(context!!)
                                }else if(response.code()>=500){
                                    showErrorPopup("[네트워크 오류] 재로그인을 해주세요!",context!!)
                                }else{
                                    showErrorPopup("재로그인을 해주세요!",context!!)
                                }
                            }
                        }
                    }
                )
//                app_alarm_list.isEnabled = true
//                app_alarm.summary = app_alarm_list.entry
//
//                val alarmList = findPreference("app_alarm_list") as Preference
//                val alarmTitle = SpannableStringBuilder(app_alarm_list.entry)
//                alarmTitle.setSpan(ForegroundColorSpan(Color.parseColor("#646464")),0,alarmTitle.length,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
//                alarmList.title = alarmTitle


            }else if(newValue == false){
                //디바이스토큰 삭제
                val ai = DeviceIdModel(settings.getString("androidId","")!!)
                val call2: Call<DeviceIdModel> = RetrofitCreator.service.deleteDeviceToken(settings.getString("token","")!!,ai)
                call2.enqueue(
                    object : Callback<DeviceIdModel> {
                        override fun onFailure(call: Call<DeviceIdModel>, t: Throwable) {
                            Log.e("*+*+", "error: $t")
                            showErrorPopup("재로그인을 해주세요!",context!!)
                        }

                        override fun onResponse(
                            call: Call<DeviceIdModel>,
                            response: Response<DeviceIdModel>
                        ) {
                            if(response.isSuccessful){
                                Toast.makeText(context!!,getString(R.string.settings_alarm_off),Toast.LENGTH_SHORT).show()
                                editor.putBoolean("alarm",false)
                                editor.commit()
                            }else{
                                if(response.code()==503){
                                    ServerCheckPopup(context!!)
                                }else if(response.code()>=500){
                                    showErrorPopup("[네트워크 오류] 재로그인을 해주세요!",context!!)
                                }else{
                                    showErrorPopup("재로그인을 해주세요!",context!!)
                                }
                            }
                        }
                    }
                )
//                app_alarm_list.isEnabled = false
//                app_alarm.summary = "앱에서 보내는 다양한 푸시알림을 받습니다."
//
//                val alarmList = findPreference("app_alarm_list") as Preference
//                val alarmTitle = SpannableStringBuilder(app_alarm_list.entry)
//                alarmTitle.setSpan(ForegroundColorSpan(Color.parseColor("#b6b6b6")),0,alarmTitle.length,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
//                alarmList.title = alarmTitle
            }
            true
        }

//        app_alarm_list.setOnPreferenceChangeListener { preference, newValue ->
//            if(newValue == "1"){
//                app_alarm_list.title = "벨소리"
//
//                //val summary = SpannableStringBuilder("벨소리")
//                //summary.setSpan(ForegroundColorSpan(Color.parseColor("#88cdf6")), 0, summary.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
//                app_alarm.summary = "벨소리"
//            }
//            else if(newValue == "2"){
//                app_alarm_list.title = "진동"
//
//                //val summary = SpannableStringBuilder("진동")
//                //summary.setSpan(ForegroundColorSpan(Color.parseColor("#88cdf6")), 0, summary.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
//                app_alarm.summary = "진동"
//            }
//            true
//        }
    }

    override fun onResume() {
        super.onResume()

    }
}
