package com.cyorg.uiic.dwellingpremium.app

import android.app.Application
import android.content.Context
import com.cyorg.uiic.dwellingpremium.utils.DwellingConstants.*

/**
 * Created by yorga on 07-07-2017.
 */
class DwellingApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        SHARED_PREFERENCES = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)

        BASIC_PREMIUM_RATE = SHARED_PREFERENCES.getString(BASIC_PREMIUM_RATE_KEY, (0.26 / 1000).toString()).toDouble()
        STFI_RATE = SHARED_PREFERENCES.getString(STFI_RATE_KEY, (0.075 / 1000).toString()).toDouble()
        EQ_RATE = SHARED_PREFERENCES.getString(EQ_RATE_KEY, (0.05 / 1000).toString()).toDouble()
        SERVICE_TAX = SHARED_PREFERENCES.getString(SERVICE_TAX_KEY, (18.0/100).toString()).toDouble()

    }
}
