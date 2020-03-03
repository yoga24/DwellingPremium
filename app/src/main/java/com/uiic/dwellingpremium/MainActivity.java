package com.uiic.dwellingpremium;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.uiic.dwellingpremium.model.Premium;
import com.uiic.dwellingpremium.utils.DwellingConstants;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    TextInputLayout sumTextInputLayout;
    TextInputLayout yearsTextInputLayout;
    EditText sumInsuredEditText;
    EditText yearsInsuredEditText;
    Spinner schemeSpinner;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        sumTextInputLayout = findViewById(R.id.edit_layout_sum_insured);
        yearsTextInputLayout = findViewById(R.id.edit_layout_years_insured);
        sumInsuredEditText = findViewById(R.id.edit_sum_insured);
        yearsInsuredEditText = findViewById(R.id.edit_years_insured);
        schemeSpinner = findViewById(R.id.spinner_premium_scheme);
        button = findViewById(R.id.main_cal_premium_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sumInsuredEditText.getText().toString().isEmpty()) {
                    sumTextInputLayout.setError("Sum Insured cannot be empty");
                } else if (Long.parseLong(sumInsuredEditText.getText().toString()) == 0) {
                    sumTextInputLayout.setError("Invalid Value");
                } else if (yearsInsuredEditText.getText().toString().isEmpty()) {
                    yearsTextInputLayout.setError("Years cannot be empty");
                } else if (Integer.parseInt(yearsInsuredEditText.getText().toString()) == 0 || Integer.parseInt(yearsInsuredEditText.getText().toString()) < 1) {
                    yearsTextInputLayout.setError("Invalid Value");
                } else {
                    try {
                        Log.i(LOG_TAG, "SumInsured and Years have been entered");
                        double sumInsured = Double.parseDouble(sumInsuredEditText.getText().toString());
                        int yearsInsured = Integer.parseInt(yearsInsuredEditText.getText().toString());
                        String selectedScheme = schemeSpinner.getSelectedItem().toString();

                        Premium premium = new Premium(sumInsured, yearsInsured, selectedScheme);

                        Intent premiumIntent = new Intent(view.getContext(), PremiumActivity.class);
                        premiumIntent.putExtra(DwellingConstants.PREMIUM_OBJECT_KEY, premium);
                        premiumIntent.putExtra(DwellingConstants.YEARS_INSURED_KEY, yearsInsured);
                        premiumIntent.putExtra(DwellingConstants.SCHEME_KEY, selectedScheme);

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
}
