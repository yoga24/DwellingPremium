package com.cyorg.uiic.dwellingpremium;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cyorg.uiic.dwellingpremium.model.PremiumModel;
import com.cyorg.uiic.dwellingpremium.utils.DwellingConstants;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    TextInputLayout sumTextInputLayout;
    TextInputLayout yearsTextInputLayout;
    EditText sumInsuredEditText;
    EditText yearsInsuredEditText;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        sumTextInputLayout = (TextInputLayout) findViewById(R.id.edit_layout_sum_insured);
//        yearsTextInputLayout = (TextInputLayout) findViewById(R.id.edit_layout_years_insured);
//        sumInsuredEditText = (EditText) findViewById(R.id.edit_sum_insured);
//        yearsInsuredEditText = (EditText) findViewById(R.id.edit_years_insured);
//        button = (Button) findViewById(R.id.main_cal_premium_button);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sumInsuredEditText.getText().toString().isEmpty()) {
                    sumTextInputLayout.setError("Sum Insured cannot be empty");
                } else if (Long.parseLong(sumInsuredEditText.getText().toString()) == 0) {
                    sumTextInputLayout.setError("Invalid Value");
                } else if (yearsInsuredEditText.getText().toString().isEmpty()) {
                    yearsTextInputLayout.setError("Years cannot be empty");
                } else if (Integer.parseInt(yearsInsuredEditText.getText().toString()) == 0) {
                    yearsTextInputLayout.setError("Invalid Value");
                } else {
                    try {
                        Log.i(LOG_TAG, "SumInsured and Years have been entered");
                        double sumInsured = Double.parseDouble(sumInsuredEditText.getText().toString());
                        int yearsInsured = Integer.parseInt(yearsInsuredEditText.getText().toString());

                        PremiumModel premiumModel = new PremiumModel(sumInsured, yearsInsured);
                        calculatePremiumValues(premiumModel);

                        Intent premiumIntent = new Intent(view.getContext(), PremiumActivity.class);
                        premiumIntent.putExtra(DwellingConstants.PREMIUM_OBJECT_KEY, premiumModel);
                        premiumIntent.putExtra(DwellingConstants.YEARS_INSURED_KEY, yearsInsured);

                        startActivity(premiumIntent);

                    } catch (Exception e) {
                        Log.e(LOG_TAG, "Error occurred in parsing input string");
                        e.printStackTrace();
                        Toast.makeText(MainActivity.this, "Error : Check Input String", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });


    }

    private void calculatePremiumValues(PremiumModel premiumModel) {

        double sumInsured = premiumModel.getSumInsured();
        int years = premiumModel.getYears();

        premiumModel.setBasicPremium(sumInsured * DwellingConstants.BASIC_PREMIUM_RATE * years);
        premiumModel.setStfi(sumInsured * DwellingConstants.STFI_RATE * years);
        premiumModel.setEq(sumInsured * DwellingConstants.EQ_RATE * years);


        if (years < 10) {
            premiumModel.setDiscountedBasicPremium(premiumModel.getBasicPremium() * DwellingConstants.LONG_TERM_DISCOUNT.get(years));
            //calculateLongTermDiscount(premiumModel,premiumModel.getBasicPremium(),premiumModel.getStfi(),premiumModel.getEq());
        } else {
            premiumModel.setDiscountedBasicPremium(premiumModel.getBasicPremium() * DwellingConstants.LONG_TERM_DISCOUNT.get(10));
        }
        premiumModel.setTotalPremium(
                (int) ((premiumModel.getBasicPremium() - premiumModel.getDiscountedBasicPremium()) + premiumModel.getStfi() + premiumModel.getEq())
        );
        premiumModel.setServiceTax(
                (int) (premiumModel.getTotalPremium() * DwellingConstants.SERVICE_TAX)
        );
        premiumModel.setGrandTotal(premiumModel.getTotalPremium() + premiumModel.getServiceTax());

    }

    private void calculateLongTermDiscount(PremiumModel premiumModel, double basicPremium, double stfi, double eq) {

        for (int index = 1; index <= 10; index++) {
            double discountedPremium = basicPremium * DwellingConstants.LONG_TERM_DISCOUNT.get(index);
            int totalPremium = (int) ((basicPremium - discountedPremium) + stfi + eq);
            int serviceTax = (int) (totalPremium * DwellingConstants.SERVICE_TAX);
            int grandTotal = totalPremium + serviceTax;

            premiumModel.getDiscountedBasicPremiumList().add(discountedPremium);
            premiumModel.getTotalPremiumList().add(totalPremium);
            premiumModel.getServiceTaxList().add(serviceTax);
            premiumModel.getGrandTotalList().add(grandTotal);

        }
    }
}
