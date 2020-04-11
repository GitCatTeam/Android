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
                val call: Call<LogoutModel> = RetrofitCreator.service.postLogout("token")
                call.enqueue(
                    object : Callback<LogoutModel> {
                        override fun onFailure(call: Call<LogoutModel>, t: Throwable) {
                            Log.e("*+*+", "error: $t")
                        }

                        override fun onResponse(
                            call: Call<LogoutModel>,
                            response: Response<LogoutModel>
                        ) {
                            if(response.isSuccessful){
                                //FIXME: 로그아웃 API
                                //var intent = Intent(this@SettingsAccountFragment,MainActivity::class.java)
                                //startActivity(intent)
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
            dialogTitle.text = "회원 탈퇴"
            dialogMessage.text = "현재 로그인 되어 있는 Github계정을\n" +
                    "회원 탈퇴 하시겠습니까?"

            dialogCancel.setOnClickListener {
                ad.dismiss()
            }
            dialogOK.setOnClickListener {
                val call: Call<WithdrawModel> = RetrofitCreator.service.deleteWithdraw("token")
                call.enqueue(
                    object : Callback<WithdrawModel> {
                        override fun onFailure(call: Call<WithdrawModel>, t: Throwable) {
                            Log.e("*+*+", "error: $t")
                        }

                        override fun onResponse(
                            call: Call<WithdrawModel>,
                            response: Response<WithdrawModel>
                        ) {
                            if(response.isSuccessful){
                                //FIXME: 회원탈퇴 API
                                //var intent = Intent(this@SettingsAccountFragment,MainActivity::class.java)
                                //startActivity(intent)
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
