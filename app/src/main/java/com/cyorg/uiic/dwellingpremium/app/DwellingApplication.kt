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
        STFI_RATE = SHARED_PREFERENCES.getString(STFI_RATE_KEY, 0.075.toString()).toDouble()  / 1000
        EQ_RATE = SHARED_PREFERENCES.getString(EQ_RATE_KEY, 0.05.toString()).toDouble() / 1000
        SERVICE_TAX = SHARED_PREFERENCES.getString(SERVICE_TAX_KEY, 18.0.toString()).toDouble()  / 100

        SERVICE_TAX_PERCENT = SHARED_PREFERENCES.getString(SERVICE_TAX_KEY, 18.toString()).toInt()

    }
}
