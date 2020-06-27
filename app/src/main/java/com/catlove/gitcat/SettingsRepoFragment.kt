package com.catlove.gitcat

import android.os.Bundle
import androidx.preference.CheckBoxPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager

class SettingsRepoFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preferences_repo)

        val public = findPreference("repo_public") as CheckBoxPreference
        val private = findPreference("repo_private") as CheckBoxPreference

        val prefs = PreferenceManager.getDefaultSharedPreferences(activity!!)
        val is_public = prefs.getBoolean("repo_public",false)
        val is_private = prefs.getBoolean("repo_private",false)

//        public.setOnPreferenceChangeListener { preference, newValue ->
//            public.isEnabled = true
//            private.isEnabled == false
//        }
//
//        private.setOnPreferenceChangeListener { preference, newValue ->
//            private.isEnabled = true
//            public.isEnabled == false
//        }

        public.setOnPreferenceClickListener {
            if(public.isChecked){
                private.isChecked = false
            }
            private.isChecked == false
        }

        private.setOnPreferenceClickListener {
            if(private.isChecked){
                public.isChecked = false
            }
            public.isChecked == false
        }
    }
}