package com.cyorg.uiic.dwellingpremium.activity

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceActivity

/**
 * Created by yorga on 08-07-2017.
 */
class SettingsActivity : PreferenceActivity(),SharedPreferences.OnSharedPreferenceChangeListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preferenceManager
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}