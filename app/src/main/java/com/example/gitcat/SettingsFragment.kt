package com.example.gitcat

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat

class SettingsFragment : PreferenceFragmentCompat(){

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        //addPreferencesFromResource(R.xml.preferences)
        setPreferencesFromResource(R.xml.preferences,rootKey)
    }
}