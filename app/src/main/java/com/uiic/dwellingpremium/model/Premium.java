package com.uiic.dwellingpremium.model;

import com.uiic.dwellingpremium.utils.DwellingConstants;

import java.io.Serializable;

public class Premium implements Serializable {

    private double sumInsured;
    private int years;
    private double basicPremium;
    private double terrorism;
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

    public double getTerrorism() {
        return terrorism;
    }

    public void setTerrorism(double terrorism) {
        this.terrorism = terrorism;
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

        this.basicPremium = sumInsured * DwellingConstants.BASIC_PREMIUM_RATE * years;
        this.terrorism = sumInsured * DwellingConstants.TERRORISM * years;
        this.totalPremium = (int) (this.basicPremium + this.terrorism);
        this.serviceTax = (int) (this.totalPremium * DwellingConstants.SERVICE_TAX);
        this.grandTotal = this.totalPremium + this.serviceTax;

    }

}
