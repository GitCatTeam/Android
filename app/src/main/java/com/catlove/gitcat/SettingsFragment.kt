package com.catlove.gitcat

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.util.Log.d
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import kotlinx.android.synthetic.main.activity_home.*
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.catlove.gitcat.model.DeviceIdModel
import com.catlove.gitcat.model.LogoutModel
import com.catlove.gitcat.retrofit.RetrofitCreator
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SettingsFragment : PreferenceFragmentCompat(){

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        //addPreferencesFromResource(R.xml.preferences)
        setPreferencesFromResource(R.xml.preferences,rootKey)

        val set_cat = findPreference("set_cat") as Preference
        val set_repo = findPreference("set_repo") as Preference
        val dialogView = layoutInflater.inflate(R.layout.settings_dialog,container,false)

        val settings: SharedPreferences = requireActivity().getSharedPreferences("gitcat",
            AppCompatActivity.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = settings.edit()

        val dialogTitle = dialogView.findViewById<TextView>(R.id.dialog_title)
        val dialogMessage = dialogView.findViewById<TextView>(R.id.dialog_message)
        val dialogCancel = dialogView.findViewById<TextView>(R.id.dialog_cancel)
        val dialogOK = dialogView.findViewById<TextView>(R.id.dialog_ok)

        set_repo.setOnPreferenceClickListener {
            val builder = AlertDialog.Builder(activity!!)
            val ad = builder.create()

            if (dialogView.parent != null) {
                (dialogView.parent as ViewGroup).removeView(dialogView) // <- fix
            }
            ad.setView(dialogView)

            dialogTitle.text = getString(R.string.settings_popup_repo1)
            dialogMessage.text = getString(R.string.settings_popup_repo2) + settings.getString("repoAuth","") + getString(R.string.settings_popup_repo3)

            dialogCancel.setOnClickListener {
                ad.dismiss()
            }

            dialogOK.setOnClickListener {
                NewToken(context!!)

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
                                d("*+*+디바이스토큰","디바이스 토큰 삭제 성공")

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

                val call: Call<LogoutModel> = RetrofitCreator.service.postLogout(settings.getString("token","")!!)
                call.enqueue(
                    object : Callback<LogoutModel> {
                        override fun onFailure(call: Call<LogoutModel>, t: Throwable) {
                            Log.e("*+*+", "error: $t")
                            showErrorPopup("재로그인을 해주세요!",context!!)
                        }

                        override fun onResponse(
                            call: Call<LogoutModel>,
                            response: Response<LogoutModel>
                        ) {
                            if(response.isSuccessful){
                                //로그아웃
                                settings.edit().clear().commit()

                                //레포계정
                                editor.putBoolean("newPeople",true)
                                editor.commit()

                                var intent = Intent(context!!,MainActivity::class.java)
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                startActivity(intent)


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

            }
            ad.show()

            true

        }

        //고양이 초기화
        set_cat.setOnPreferenceClickListener {
            val builder = AlertDialog.Builder(activity!!)
            val ad = builder.create()

            if (dialogView.parent != null) {
                (dialogView.parent as ViewGroup).removeView(dialogView) // <- fix
            }
            ad.setView(dialogView)
            dialogTitle.text = getString(R.string.settings_popup_reset1)
            dialogMessage.text = getString(R.string.settings_popup_reset2)

            dialogCancel.setOnClickListener {
                ad.dismiss()
            }
            dialogOK.setOnClickListener {
                NewToken(context!!)
                val call: Call<Unit> = RetrofitCreator.service.deleteCats(settings.getString("token","")!!)
                call.enqueue(
                    object : Callback<Unit> {
                        override fun onFailure(call: Call<Unit>, t: Throwable) {
                            Log.e("*+*+", "error: $t")
                            showErrorPopup("재로그인을 해주세요!",context!!)
                        }

                        override fun onResponse(
                            call: Call<Unit>,
                            response: Response<Unit>
                        ) {
                            if(response.isSuccessful){
                                //고양이 초기화 API
                                var intent = Intent(context!!,Info4Activity::class.java)
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                startActivity(intent)
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
            }

            ad.show()

            true
        }
    }
}