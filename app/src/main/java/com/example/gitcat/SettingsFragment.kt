package com.example.gitcat

import android.os.Bundle
import android.util.Log.d
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import kotlinx.android.synthetic.main.activity_home.*
import android.view.ViewGroup



class SettingsFragment : PreferenceFragmentCompat(){

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        //addPreferencesFromResource(R.xml.preferences)
        setPreferencesFromResource(R.xml.preferences,rootKey)

        val set_cat = findPreference("set_cat") as Preference
        val dialogView = layoutInflater.inflate(R.layout.settings_dialog,container,false)

        val dialogTitle = dialogView.findViewById<TextView>(R.id.dialog_title)
        val dialogMessage = dialogView.findViewById<TextView>(R.id.dialog_message)
        val dialogCancel = dialogView.findViewById<TextView>(R.id.dialog_cancel)
        val dialogOK = dialogView.findViewById<TextView>(R.id.dialog_ok)

        set_cat.setOnPreferenceClickListener {
            val builder = AlertDialog.Builder(activity!!)
            val ad = builder.create()

            if (dialogView.parent != null) {
                (dialogView.parent as ViewGroup).removeView(dialogView) // <- fix
            }
            ad.setView(dialogView)
            dialogTitle.text = "고양이 초기화"
            dialogMessage.text = "귀여운 고양이들을 보내시겠습니까?\n" +
                    "한 번 떠난 고양이는 되돌아오지 못합니다!"

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