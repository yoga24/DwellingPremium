package com.uiic.dwellingpremium.utils;

import java.util.HashMap;
import java.util.Map;

public class DwellingConstants {

    public static final String YEARS_INSURED_KEY = "years";
    public static final String PREMIUM_OBJECT_KEY = "premiumObjectModel";
    public static final String SCHEME_KEY = "PREMIUM_SCHEME";
    public static final String SCHEME_A = "Scheme A";
    public static final String SCHEME_B = "Scheme B";

    public static final double BASIC_PREMIUM_RATE = 0.14 / 1000;
    public static final double STFI_RATE = 0.075 / 1000;
    public static final double EQ_RATE = 0.05 / 1000;
    public static final double SERVICE_TAX = 18.0 / 100;

    public static final Map<Integer, Double> LONG_TERM_DISCOUNT = new HashMap<>();

    static {
        LONG_TERM_DISCOUNT.put(1, 0.0);
        LONG_TERM_DISCOUNT.put(2, 0.0);
        LONG_TERM_DISCOUNT.put(3, 15.0);
        LONG_TERM_DISCOUNT.put(4, 20.0);
        LONG_TERM_DISCOUNT.put(5, 25.0);
        LONG_TERM_DISCOUNT.put(6, 30.0);
        LONG_TERM_DISCOUNT.put(7, 35.0);
        LONG_TERM_DISCOUNT.put(8, 40.0);
        LONG_TERM_DISCOUNT.put(9, 45.0);
        LONG_TERM_DISCOUNT.put(10, 50.0);
    }

}
