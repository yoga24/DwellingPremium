package com.uiic.dwellingpremium.model;

import com.uiic.dwellingpremium.utils.DwellingConstants;

import java.io.Serializable;

import static com.uiic.dwellingpremium.utils.DwellingConstants.BASIC_PREMIUM_RATE;
import static com.uiic.dwellingpremium.utils.DwellingConstants.LONG_TERM_DISCOUNT;
import static com.uiic.dwellingpremium.utils.DwellingConstants.SERVICE_TAX;

public class Premium implements Serializable {

    private double sumInsured;
    private int years;
    private double basicPremium;
    private double stfi;
    private double eq;
    private double discountedBasicPremium;
    private int totalPremium;
    private int serviceTax;
    private int grandTotal;

    public Premium(double sumInsured, int years) {
        this.sumInsured = sumInsured;
        this.years = years;
        calculatePremium();
    }

    public double getSumInsured() {
        return sumInsured;
    }

    public void setSumInsured(double sumInsured) {
        this.sumInsured = sumInsured;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public double getBasicPremium() {
        return basicPremium;
    }

    public void setBasicPremium(double basicPremium) {
        this.basicPremium = basicPremium;
    }

    public double getStfi() {
        return stfi;
    }

    public void setStfi(double stfi) {
        this.stfi = stfi;
    }

    public double getEq() {
        return eq;
    }

    public void setEq(double eq) {
        this.eq = eq;
    }

    public double getDiscountedBasicPremium() {
        return discountedBasicPremium;
    }

    public void setDiscountedBasicPremium(double discountedBasicPremium) {
        this.discountedBasicPremium = discountedBasicPremium;
    }

    public int getTotalPremium() {
        return totalPremium;
    }

    public void setTotalPremium(int totalPremium) {
        this.totalPremium = totalPremium;
    }

    public int getServiceTax() {
        return serviceTax;
    }

    public void setServiceTax(int serviceTax) {
        this.serviceTax = serviceTax;
    }

    public int getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(int grandTotal) {
        this.grandTotal = grandTotal;
    }

    private void calculatePremium() {

        double premiumRate = BASIC_PREMIUM_RATE.get(10) / 1000;
        double discount = LONG_TERM_DISCOUNT.get(10) / 100;
        if (years > 0 && years < 10) {
            premiumRate = BASIC_PREMIUM_RATE.get(years) / 1000;
            discount = LONG_TERM_DISCOUNT.get(years) / 100;
        }

        this.basicPremium = sumInsured * premiumRate * years;
        this.stfi = sumInsured * DwellingConstants.STFI_RATE * years;
        this.eq = sumInsured * DwellingConstants.EQ_RATE * years;
        this.discountedBasicPremium = this.basicPremium * discount;
        this.totalPremium = (int) ((this.basicPremium - this.discountedBasicPremium) + this.stfi + this.eq);
        this.serviceTax = (int) (this.totalPremium * SERVICE_TAX);
        this.grandTotal = this.totalPremium + this.serviceTax;

    }

}
