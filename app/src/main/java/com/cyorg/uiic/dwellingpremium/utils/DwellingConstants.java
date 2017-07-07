package com.cyorg.uiic.dwellingpremium.utils;

import android.content.SharedPreferences;

import com.cyorg.uiic.dwellingpremium.model.Premium;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yorga on 12/6/2016.
 */
public class DwellingConstants {

    public static SharedPreferences SHARED_PREFERENCES;

    public static Premium PREMIUM;

    public static final String SHARED_PREF_NAME = "DWELLING_PREFERENCES";
    public static final String BASIC_PREMIUM_RATE_KEY="BASIC_PREMIUM_RATE";
    public static final String STFI_RATE_KEY="STFI_RATE";
    public static final String EQ_RATE_KEY="EQ_RATE";
    public static final String SERVICE_TAX_KEY="SERVICE_TAX_PERCENT";

    public static final String SCHEME_KEY = "PREMIUM_SCHEME";

    public static final String CURRENT_YEAR_KEY = "currentYear";
    public static final String YEARS_INSURED_KEY = "years";
    public static final String PREMIUM_OBJECT_KEY = "premiumObjectModel";

    public static double BASIC_PREMIUM_RATE=0.26/1000;
    public static double STFI_RATE=0.075/1000;
    public static double EQ_RATE=0.05/1000;
    public static double SERVICE_TAX=18.0/100;

    public static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("\u20B9 ##,##,##,##,###.##");

    public static final Map<Integer,Double> LONG_TERM_DISCOUNT = new HashMap<>();

    static  {
        LONG_TERM_DISCOUNT.put(1,0.0);
        LONG_TERM_DISCOUNT.put(2,0.0);
        LONG_TERM_DISCOUNT.put(3,15.0/100);
        LONG_TERM_DISCOUNT.put(4,20.0/100);
        LONG_TERM_DISCOUNT.put(5,25.0/100);
        LONG_TERM_DISCOUNT.put(6,30.0/100);
        LONG_TERM_DISCOUNT.put(7,35.0/100);
        LONG_TERM_DISCOUNT.put(8,40.0/100);
        LONG_TERM_DISCOUNT.put(9,45.0/100);
        LONG_TERM_DISCOUNT.put(10,50.0/100);
    }

}
