package com.cyorg.uiic.dwellingpremium.model

import com.cyorg.uiic.dwellingpremium.utils.DwellingConstants
import com.cyorg.uiic.dwellingpremium.utils.DwellingConstants.LONG_TERM_DISCOUNT
import java.io.Serializable

/**
 * Created by yorga on 07-07-2017.
 */
data class Premium(var sumInsured: Long,
                   var years: Int,
                   var basicPremium: Double = 0.0,
                   var stfi: Double = 0.0,
                   var eq: Double = 0.0,
                   var discountedBasicPremium: Double = 0.0,
                   var totalPremium: Double = 0.0,
                   var serviceTax:Double = 0.0,
                   var grandTotal: Double = 0.0) : Serializable {

    fun calculatePremium(scheme:String = Scheme.Scheme_A.value)  {
        basicPremium = sumInsured * DwellingConstants.BASIC_PREMIUM_RATE * years
        stfi = sumInsured * DwellingConstants.STFI_RATE * years
        eq = sumInsured * DwellingConstants.EQ_RATE * years

        if(scheme == Scheme.Scheme_A.value)
            discountedBasicPremium = 0.0
        else{
            if(years < 10)
                discountedBasicPremium = basicPremium * (years * 5)
            else
                discountedBasicPremium = basicPremium * (50)
        }

        totalPremium = basicPremium - discountedBasicPremium
        serviceTax = totalPremium * DwellingConstants.SERVICE_TAX
        grandTotal = totalPremium + serviceTax
    }

}
