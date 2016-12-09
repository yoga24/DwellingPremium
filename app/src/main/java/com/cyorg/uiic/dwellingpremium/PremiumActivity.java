package com.cyorg.uiic.dwellingpremium;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.cyorg.uiic.dwellingpremium.fragments.PremiumDetailFragment;
import com.cyorg.uiic.dwellingpremium.fragments.PremiumSummaryFragment;
import com.cyorg.uiic.dwellingpremium.model.PremiumModel;
import com.cyorg.uiic.dwellingpremium.utils.DwellingConstants;

import java.text.DecimalFormat;

/**
 * Created by HCL on 12/6/2016.
 */
public class PremiumActivity extends AppCompatActivity {

    private static final String LOG_TAG = PremiumActivity.class.getSimpleName();
    PremiumModel premiumModel;

    TextView sumInsured, yearsInsured, basicPremium, stfi, eq, totalPremium, discountedBasicPremium, serviceTax, grandTotal;
    TextView labelDiscountedPremium;

    private ShareActionProvider mShareActionProvider;
    DecimalFormat decimalFormat = new DecimalFormat("\u20B9 ##,##,##,##,###.##");

//    FragmentManager fragmentManager;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_premium);
//
//        int years = getIntent().getIntExtra(DwellingConstants.YEARS_INSURED_KEY, 1);
//
//        fragmentManager = getFragmentManager();
//
//        if (years > 10) {
//
//            Fragment fragment = fragmentManager.findFragmentByTag(PremiumDetailFragment.FRAGMENT_TAG);
//            //Fragment Null check is mandatory. This will avoid creating the same fragment over the existing one therby creating two copies in scrollview.
//            if(fragment == null) {
//                fragmentManager.beginTransaction().add(R.id.premium_container, new PremiumDetailFragment(), PremiumDetailFragment.FRAGMENT_TAG).commit();
//            }   else    {
//                fragmentManager.beginTransaction().replace(R.id.premium_container, new PremiumDetailFragment(), PremiumDetailFragment.FRAGMENT_TAG).commit();
//            }
//
//        } else {
//            fragmentManager.beginTransaction().add(R.id.premium_container, new PremiumSummaryFragment(), PremiumSummaryFragment.FRAGMENT_TAG).commit();
//        }
//    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_premium_detail);

        premiumModel = (PremiumModel) getIntent().getSerializableExtra(DwellingConstants.PREMIUM_OBJECT_KEY);

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
        sumInsured.setText(decimalFormat.format(premiumModel.getSumInsured()));
        basicPremium.setText(decimalFormat.format(premiumModel.getBasicPremium()));
        stfi.setText(decimalFormat.format(premiumModel.getStfi()));
        eq.setText(decimalFormat.format(premiumModel.getEq()));
        yearsInsured.setText(String.valueOf(premiumModel.getYears()));

        if (premiumModel.getYears() > 10) {
            labelDiscountedPremium.setText(
                    labelDiscountedPremium.getText().toString().replace("%1%", "50%")
            );
        } else {
            labelDiscountedPremium.setText(
                    labelDiscountedPremium.getText().toString().replace("%1%",
                            Math.round(DwellingConstants.LONG_TERM_DISCOUNT.get(premiumModel.getYears()) * 100) + "%")
            );
        }

        discountedBasicPremium.setText(decimalFormat.format(premiumModel.getDiscountedBasicPremium()));
        totalPremium.setText(decimalFormat.format(premiumModel.getTotalPremium()));
        serviceTax.setText(decimalFormat.format(premiumModel.getServiceTax()));
        grandTotal.setText(decimalFormat.format(premiumModel.getGrandTotal()));
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
                getString(R.string.detail_hint_sum_insured) + " : Rs." + decimalFormat.format(premiumModel.getSumInsured()) + "\n" +
                        getString(R.string.detail_hint_years) + " : " +premiumModel.getYears() + " years\n" +
                        getString(R.string.detail_hint_basic_premium) + " : Rs." + decimalFormat.format(premiumModel.getBasicPremium()) + "\n" +
                        getString(R.string.detail_hint_stfi) + " : Rs." + decimalFormat.format(premiumModel.getStfi()) + "\n" +
                        getString(R.string.detail_hint_eq) + " : Rs." + decimalFormat.format(premiumModel.getEq()) + "\n" +
                        labelDiscountedPremium.getText().toString() + " : Rs." + decimalFormat.format(premiumModel.getDiscountedBasicPremium()) + "\n" +
                        getString(R.string.detail_hint_premium_total) + " : Rs." + decimalFormat.format(premiumModel.getTotalPremium()) + "\n" +
                        getString(R.string.detail_hint_service_tax) + " : Rs." + decimalFormat.format(premiumModel.getServiceTax()) + "\n" +
                        getString(R.string.detail_hint_grand_total) + " : Rs." + decimalFormat.format(premiumModel.getGrandTotal());

        return shareString;
    }

//    @Override
//    public void onBackPressed() {
//        // If the fragment exists and has some back-stack entry
//        if (fragmentManager != null && fragmentManager.getBackStackEntryCount() > 0) {
//            // Get the fragment fragment manager - and pop the backstack
//            fragmentManager.popBackStack();
//        }
//        // Else, nothing in the direct fragment back stack
//        else {
//            // Let super handle the back press
//            super.onBackPressed();
//        }
//    }

}
