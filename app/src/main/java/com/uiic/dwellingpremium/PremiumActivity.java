package com.uiic.dwellingpremium;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ShareActionProvider;
import androidx.core.view.MenuItemCompat;

import com.uiic.dwellingpremium.model.Premium;
import com.uiic.dwellingpremium.utils.DwellingConstants;

import java.text.DecimalFormat;

public class PremiumActivity extends AppCompatActivity {

    private static final String LOG_TAG = PremiumActivity.class.getSimpleName();
    Premium premium;

    TextView sumInsured, yearsInsured, basicPremium, stfi, eq, totalPremium, discountedBasicPremium, serviceTax, grandTotal;
    TextView labelDiscountedPremium;

    private ShareActionProvider mShareActionProvider;
    DecimalFormat decimalFormat = new DecimalFormat("\u20B9 ##,##,##,##,###.##");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_premium);

        premium = (Premium) getIntent().getSerializableExtra(DwellingConstants.PREMIUM_OBJECT_KEY);

        //Initialize TextViews
        sumInsured = (TextView) findViewById(R.id.detail_text_sum_insured);
        yearsInsured = (TextView) findViewById(R.id.detail_text_years);
        basicPremium = (TextView) findViewById(R.id.detail_text_basic_premium);
        stfi = (TextView) findViewById(R.id.detail_text_stfi);
        eq = (TextView) findViewById(R.id.detail_text_eq);
        totalPremium = (TextView) findViewById(R.id.detail_text_premium_total);
        discountedBasicPremium = (TextView) findViewById(R.id.detail_text_discounted_premium);
        serviceTax = (TextView) findViewById(R.id.detail_text_service_tax);
        grandTotal = (TextView) findViewById(R.id.detail_text_grand_total);

        labelDiscountedPremium = (TextView) findViewById(R.id.detail_label_text_discounted_premium);

        //Set CommonValues for Premium
        sumInsured.setText(decimalFormat.format(premium.getSumInsured()));
        basicPremium.setText(decimalFormat.format(premium.getBasicPremium()));
        stfi.setText(decimalFormat.format(premium.getStfi()));
        eq.setText(decimalFormat.format(premium.getEq()));
        yearsInsured.setText(String.valueOf(premium.getYears()));

        if (premium.getYears() > 10) {
            labelDiscountedPremium.setText(
                    labelDiscountedPremium.getText().toString().replace("%1%", "50%")
            );
        } else {
            labelDiscountedPremium.setText(
                    labelDiscountedPremium.getText().toString().replace("%1%",
                            Math.round(DwellingConstants.LONG_TERM_DISCOUNT.get(premium.getYears())) + "%")
            );
        }

        discountedBasicPremium.setText(decimalFormat.format(premium.getDiscountedBasicPremium()));
        totalPremium.setText(decimalFormat.format(premium.getTotalPremium()));
        serviceTax.setText(decimalFormat.format(premium.getServiceTax()));
        grandTotal.setText(decimalFormat.format(premium.getGrandTotal()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.premium_detail_menu, menu);

        //Read the share menu item
        MenuItem shareItem = menu.findItem(R.id.menu_item_share);
        //Assign EasyShareAction to SHARE MenuItem
        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);
        //Custom File to write share history
        mShareActionProvider.setShareHistoryFileName("custom_share_history.xml");

        return true;
        //return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_share:
                mShareActionProvider.setShareIntent(createShareIntent());
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private Intent createShareIntent() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);

        shareIntent.setType("text/plain");
        shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Dwelling Premium Calculation");

        shareIntent.putExtra(Intent.EXTRA_TEXT, getPremiumDetailString());

        return shareIntent;
    }

    private String getPremiumDetailString() {
        String shareString =
                getString(R.string.detail_hint_sum_insured) + " : Rs." + decimalFormat.format(premium.getSumInsured()) + "\n" +
                        getString(R.string.detail_hint_years) + " : " + premium.getYears() + " years\n" +
                        getString(R.string.detail_hint_basic_premium) + " : Rs." + decimalFormat.format(premium.getBasicPremium()) + "\n" +
                        getString(R.string.detail_hint_stfi) + " : Rs." + decimalFormat.format(premium.getStfi()) + "\n" +
                        getString(R.string.detail_hint_eq) + " : Rs." + decimalFormat.format(premium.getEq()) + "\n" +
                        labelDiscountedPremium.getText().toString() + " : Rs." + decimalFormat.format(premium.getDiscountedBasicPremium()) + "\n" +
                        getString(R.string.detail_hint_premium_total) + " : Rs." + decimalFormat.format(premium.getTotalPremium()) + "\n" +
                        getString(R.string.detail_hint_service_tax) + " : Rs." + decimalFormat.format(premium.getServiceTax()) + "\n" +
                        getString(R.string.detail_hint_grand_total) + " : Rs." + decimalFormat.format(premium.getGrandTotal());

        return shareString;
    }
}

