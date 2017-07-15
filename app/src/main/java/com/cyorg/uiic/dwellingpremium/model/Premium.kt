package com.cyorg.uiic.dwellingpremium.model

import android.util.Log
import com.cyorg.uiic.dwellingpremium.utils.DwellingConstants
import java.io.Serializable

/**
 * Created by yorga on 07-07-2017.
 */
data class Premium(var sumInsured: Long,
                   var years: Int,
                   var basicPremium: Long = 0L,
                   var stfi: Long = 0L,
                   var eq: Long = 0L,
                   var discountedBasicPremium: Long = 0L,
                   var totalPremium: Long = 0L,
                   var serviceTax:Long = 0L,
                   var grandTotal: Long = 0L) : Serializable {

    fun calculatePremium(scheme:String = Scheme.Scheme_A.value)  {
        basicPremium = Math.round(sumInsured * DwellingConstants.BASIC_PREMIUM_RATE * years)
        stfi = Math.round(sumInsured * DwellingConstants.STFI_RATE * years)
        eq = Math.round(sumInsured * DwellingConstants.EQ_RATE * years)

        if(scheme == Scheme.Scheme_B.value) {
            if(years < 10)
                discountedBasicPremium = Math.round(basicPremium * ((years * 5)/100.0))
            else
                discountedBasicPremium = Math.round(basicPremium * (50.0/100))
        }

        totalPremium = basicPremium - discountedBasicPremium + stfi + eq
        serviceTax = Math.round(totalPremium * DwellingConstants.SERVICE_TAX)
        grandTotal = totalPremium + serviceTax
    }

}
