package com.example.gitcat

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


class SettingsAccountFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preferences_account)

        //로그인 계정
        val login_account = findPreference("login_account") as Preference

        val summary = SpannableStringBuilder("jihu02@naver.com")
        summary.setSpan(ForegroundColorSpan(Color.parseColor("#88cdf6")), 0, summary.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        login_account.summary = summary

        //계정 로그아웃
        val logout = findPreference("logout") as Preference

        val logoutTitle = SpannableStringBuilder("계정 로그아웃")
        logoutTitle.setSpan(ForegroundColorSpan(Color.parseColor("#ff8c86")),0,logoutTitle.length,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        logout.title = logoutTitle

        val dialogView = layoutInflater.inflate(R.layout.settings_dialog,container,false)

        val dialogTitle = dialogView.findViewById<TextView>(R.id.dialog_title)
        val dialogMessage = dialogView.findViewById<TextView>(R.id.dialog_message)
        val dialogCancel = dialogView.findViewById<TextView>(R.id.dialog_cancel)
        val dialogOK = dialogView.findViewById<TextView>(R.id.dialog_ok)

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
                ad.dismiss()
            }

            ad.show()

            true
        }
    }


}
