package com.cyorg.uiic.dwellingpremium.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HCL on 12/6/2016.
 */
public class PremiumModel implements Serializable{

    private double sumInsured;
    private int years;
    private double basicPremium;
    private double stfi;
    private double eq;
    private double discountedBasicPremium;
    private int totalPremium;
    private int serviceTax;
    private int grandTotal;

    private List<Double> discountedBasicPremiumList= new ArrayList<>();
    private List<Integer> totalPremiumList = new ArrayList<>();
    private List<Integer> serviceTaxList= new ArrayList<>();
    private List<Integer> grandTotalList= new ArrayList<>();

    public PremiumModel(double sumInsured,int years)    {
        this.sumInsured = sumInsured;
        this.years = years;
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

    public List<Double> getDiscountedBasicPremiumList() {
        return discountedBasicPremiumList;
    }

    public void setDiscountedBasicPremiumList(List<Double> discountedBasicPremiumList) {
        this.discountedBasicPremiumList = discountedBasicPremiumList;
    }

    public List<Integer> getTotalPremiumList() {
        return totalPremiumList;
    }

    public void setTotalPremiumList(List<Integer> totalPremiumList) {
        this.totalPremiumList = totalPremiumList;
    }

    public List<Integer> getServiceTaxList() {
        return serviceTaxList;
    }

    public void setServiceTaxList(List<Integer> serviceTaxList) {
        this.serviceTaxList = serviceTaxList;
    }

    public List<Integer> getGrandTotalList() {
        return grandTotalList;
    }

    public void setGrandTotalList(List<Integer> grandTotalList) {
        this.grandTotalList = grandTotalList;
    }

}
