package com.catlove.gitcat

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.CheckBoxPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager

/*안쓰지만 설정화면 라디오 버튼 할 때*/
class SettingsRepoFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preferences_repo)

        val settings: SharedPreferences = requireActivity().getSharedPreferences("gitcat",
            AppCompatActivity.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = settings.edit()

        val public = findPreference("repo_public") as CheckBoxPreference
        val private = findPreference("repo_private") as CheckBoxPreference

        val prefs = PreferenceManager.getDefaultSharedPreferences(activity!!)
        val is_public = prefs.getBoolean("repo_public",false)
        val is_private = prefs.getBoolean("repo_private",false)

        if(settings.getString("repoAuth","")=="public"){
            public.isChecked = true
            private.isChecked = false
        }else{
            private.isChecked = true
            public.isChecked = false
        }

//        public.setOnPreferenceChangeListener { preference, newValue ->
//            public.isEnabled = true
//            private.isEnabled == false
//        }
//
//        private.setOnPreferenceChangeListener { preference, newValue ->
//            private.isEnabled = true
//            public.isEnabled == false
//        }

        public.setOnPreferenceClickListener {//public
            if(public.isChecked){
                private.isChecked = false
            }
            private.isChecked == false
        }

        private.setOnPreferenceClickListener {//private
            if(private.isChecked){
                public.isChecked = false
            }
            public.isChecked == false
        }
    }
}