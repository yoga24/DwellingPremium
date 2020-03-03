package com.uiic.dwellingpremium.utils;

import java.util.HashMap;
import java.util.Map;

public class DwellingConstants {

    public static final String YEARS_INSURED_KEY = "years";
    public static final String PREMIUM_OBJECT_KEY = "premiumObjectModel";
    public static final String SCHEME_KEY = "PREMIUM_SCHEME";
    public static final String SCHEME_A = "Scheme A";
    public static final String SCHEME_B = "Scheme B";

    public static final Map<Integer, Double> BASIC_PREMIUM_RATE = new HashMap<>();
    public static final double STFI_RATE = 0.075 / 1000;
    public static final double EQ_RATE = 0.05 / 1000;
    public static final double SERVICE_TAX = 18.0 / 100;

    public static final Map<Integer, Double> LONG_TERM_DISCOUNT = new HashMap<>();

    static {

        BASIC_PREMIUM_RATE.put(1, 0.26);
        BASIC_PREMIUM_RATE.put(2, 0.26);
        BASIC_PREMIUM_RATE.put(3, 0.244);
        BASIC_PREMIUM_RATE.put(4, 0.237);
        BASIC_PREMIUM_RATE.put(5, 0.23);
        BASIC_PREMIUM_RATE.put(6, 0.223);
        BASIC_PREMIUM_RATE.put(7, 0.216);
        BASIC_PREMIUM_RATE.put(8, 0.209);
        BASIC_PREMIUM_RATE.put(9, 0.202);
        BASIC_PREMIUM_RATE.put(10, 0.195);


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
