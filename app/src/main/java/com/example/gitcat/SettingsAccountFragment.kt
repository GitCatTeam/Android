package com.example.gitcat

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import kotlinx.android.synthetic.main.activity_home.*
import android.text.SpannableString
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.gitcat.model.LogoutModel
import com.example.gitcat.model.WithdrawModel
import com.example.gitcat.retrofit.RetrofitCreator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SettingsAccountFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preferences_account)

        val settings: SharedPreferences = requireActivity().getSharedPreferences("gitcat",
            AppCompatActivity.MODE_PRIVATE)

        //로그인 계정
        val login_account = findPreference("login_account") as Preference

        val summary = SpannableStringBuilder(settings.getString("githubId",""))
        summary.setSpan(ForegroundColorSpan(Color.parseColor("#88cdf6")), 0, summary.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        login_account.summary = summary

        //계정 로그아웃
        val logout = findPreference("logout") as Preference

        val logoutTitle = SpannableStringBuilder("계정 로그아웃")
        logoutTitle.setSpan(ForegroundColorSpan(Color.parseColor("#ff8c86")),0,logoutTitle.length,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        logout.title = logoutTitle

        //회원 탈퇴
        val withdraw = findPreference("withdraw") as Preference

        val dialogView = layoutInflater.inflate(R.layout.settings_dialog,container,false)

        val dialogTitle = dialogView.findViewById<TextView>(R.id.dialog_title)
        val dialogMessage = dialogView.findViewById<TextView>(R.id.dialog_message)
        val dialogCancel = dialogView.findViewById<TextView>(R.id.dialog_cancel)
        val dialogOK = dialogView.findViewById<TextView>(R.id.dialog_ok)

        /*로그아웃*/
        logout.setOnPreferenceClickListener {
            val builder = AlertDialog.Builder(activity!!)
            val ad = builder.create()

            if (dialogView.parent != null) {
                (dialogView.parent as ViewGroup).removeView(dialogView) // <- fix
            }
            ad.setView(dialogView)
            dialogTitle.text = "계정 로그아웃"
            dialogMessage.text = "현재 로그인 되어 있는 Github계정을\n" +
                    "로그아웃 하시겠습니까?"

            dialogCancel.setOnClickListener {
                ad.dismiss()
            }
            dialogOK.setOnClickListener {
                NewToken(context!!)
                val call: Call<LogoutModel> = RetrofitCreator.service.postLogout(settings.getString("token",""))
                call.enqueue(
                    object : Callback<LogoutModel> {
                        override fun onFailure(call: Call<LogoutModel>, t: Throwable) {
                            Log.e("*+*+", "error: $t")
                            showErrorPopup(t.toString(),context!!)
                        }

                        override fun onResponse(
                            call: Call<LogoutModel>,
                            response: Response<LogoutModel>
                        ) {
                            if(response.isSuccessful){
                                //로그아웃
                                settings.edit().clear().commit()

                                var intent = Intent(context!!,MainActivity::class.java)
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                startActivity(intent)
                            }else{
                                if(response.code()>=500){
                                    showErrorPopup("[네트워크 오류] 잠시 후에 다시 시도해주세요",context!!)
                                }else{
                                    showErrorPopup("["+response.code().toString()+" 오류] "+"관리자에게 문의해주세요",context!!)
                                }
                            }
                        }
                    }
                )

            }

            ad.show()

            true
        }


        /*회원 탈퇴*/
        withdraw.setOnPreferenceClickListener {
            val builder = AlertDialog.Builder(activity!!)
            val ad = builder.create()

            if (dialogView.parent != null) {
                (dialogView.parent as ViewGroup).removeView(dialogView) // <- fix
            }
            ad.setView(dialogView)
            dialogTitle.text = "GitCat 탈퇴하기"
            dialogMessage.text = "GitCat 내의 모든 정보를 삭제하고\n" +
                    "정말로 탈퇴하시겠습니까?\n" +
                    "사라진 계정 정보는 복구할 수 없습니다!"

            dialogCancel.setOnClickListener {
                ad.dismiss()
            }
            dialogOK.setOnClickListener {
                NewToken(context!!)
                val call: Call<Unit> = RetrofitCreator.service.deleteWithdraw(settings.getString("token",""))
                call.enqueue(
                    object : Callback<Unit> {
                        override fun onFailure(call: Call<Unit>, t: Throwable) {
                            Log.e("*+*+", "error: $t")
                            showErrorPopup(t.toString(),context!!)
                        }

                        override fun onResponse(
                            call: Call<Unit>,
                            response: Response<Unit>
                        ) {
                            if(response.isSuccessful){
                                //회원탈퇴 API
                                settings.edit().clear().commit()

                                var intent = Intent(context!!,MainActivity::class.java)
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                startActivity(intent)
                            }else{
                                if(response.code()>=500){
                                    showErrorPopup("[네트워크 오류] 잠시 후에 다시 시도해주세요",context!!)
                                }else{
                                    showErrorPopup("["+response.code().toString()+" 오류] "+"관리자에게 문의해주세요",context!!)
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
