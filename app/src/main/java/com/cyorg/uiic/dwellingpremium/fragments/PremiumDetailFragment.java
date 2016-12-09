package com.cyorg.uiic.dwellingpremium.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cyorg.uiic.dwellingpremium.R;
import com.cyorg.uiic.dwellingpremium.model.PremiumModel;
import com.cyorg.uiic.dwellingpremium.utils.DwellingConstants;

import java.text.DecimalFormat;

/**
 * Created by HCL on 12/6/2016.
 */
public class PremiumDetailFragment extends Fragment {
    public static final String FRAGMENT_TAG = PremiumDetailFragment.class.getSimpleName();
    private static final String LOG_TAG = PremiumDetailFragment.class.getSimpleName();
    private ShareActionProvider mShareActionProvider;

    TextView sumInsured, yearsInsured, basicPremium, stfi, eq, totalPremium, discountedBasicPremium, serviceTax, grandTotal;
    TextView labelDiscountedPremium;

    DecimalFormat decimalFormat = new DecimalFormat("\u20B9 ##,##,##,##,###.##");
    int currentYear = 0;
    PremiumModel premiumModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);

        View rootView = inflater.inflate(R.layout.fragment_premium_detail, container, false);

        int years = getActivity().getIntent().getIntExtra(DwellingConstants.YEARS_INSURED_KEY, 1);
        premiumModel = (PremiumModel) getActivity().getIntent().getSerializableExtra(DwellingConstants.PREMIUM_OBJECT_KEY);


        if (getArguments() != null) {
            currentYear = getArguments().getInt(DwellingConstants.CURRENT_YEAR_KEY, 0);
            Log.i(LOG_TAG, "Current Year Referred -> " + currentYear);
        }


        //Initialize TextViews
        sumInsured = (TextView) rootView.findViewById(R.id.detail_text_sum_insured);
        yearsInsured = (TextView) rootView.findViewById(R.id.detail_text_years);
        basicPremium = (TextView) rootView.findViewById(R.id.detail_text_basic_premium);
        stfi = (TextView) rootView.findViewById(R.id.detail_text_stfi);
        eq = (TextView) rootView.findViewById(R.id.detail_text_eq);
        totalPremium = (TextView) rootView.findViewById(R.id.detail_text_premium_total);
        discountedBasicPremium = (TextView) rootView.findViewById(R.id.detail_text_discounted_premium);
        serviceTax = (TextView) rootView.findViewById(R.id.detail_text_service_tax);
        grandTotal = (TextView) rootView.findViewById(R.id.detail_text_grand_total);

        labelDiscountedPremium = (TextView) rootView.findViewById(R.id.detail_label_text_discounted_premium);

        //Set CommonValues for Premium
        sumInsured.setText(decimalFormat.format(premiumModel.getSumInsured()));
        basicPremium.setText(decimalFormat.format(premiumModel.getBasicPremium()));
        stfi.setText(decimalFormat.format(premiumModel.getStfi()));
        eq.setText(decimalFormat.format(premiumModel.getEq()));

        if (years > 10) {
            yearsInsured.setText(String.valueOf(premiumModel.getYears()));

            labelDiscountedPremium.setText(
                    labelDiscountedPremium.getText().toString().replace("%1%", "50%")
            );
            discountedBasicPremium.setText(decimalFormat.format(premiumModel.getDiscountedBasicPremium()));

            totalPremium.setText(decimalFormat.format(premiumModel.getTotalPremium()));
            serviceTax.setText(decimalFormat.format(premiumModel.getServiceTax()));
            grandTotal.setText(decimalFormat.format(premiumModel.getGrandTotal()));
        } else {

            yearsInsured.setText(String.valueOf(currentYear + 1));

            labelDiscountedPremium.setText(
                    labelDiscountedPremium.getText().toString().replace("%1%",
                            Math.round(DwellingConstants.LONG_TERM_DISCOUNT.get(currentYear + 1) * 100) + "%")
            );
            discountedBasicPremium.setText(decimalFormat.format(premiumModel.getDiscountedBasicPremiumList().get(currentYear)));

            totalPremium.setText(decimalFormat.format(premiumModel.getTotalPremiumList().get(currentYear)));
            serviceTax.setText(decimalFormat.format(premiumModel.getServiceTaxList().get(currentYear)));
            grandTotal.setText(decimalFormat.format(premiumModel.getGrandTotalList().get(currentYear)));

        }


        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        Log.i(LOG_TAG, "***Fragment OptionsMenu Creation");

        inflater.inflate(R.menu.premium_detail_menu, menu);
        //Read the share menu item
        MenuItem shareItem = menu.findItem(R.id.menu_item_share);
        //Assign EasyShareAction to SHARE MenuItem
        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);
        //Custom File to write share history
        mShareActionProvider.setShareHistoryFileName("custom_share_history.xml");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Log.i(LOG_TAG, "***Option Item Clicked");
        if (item.getItemId() == R.id.menu_item_share) {
            Log.i(LOG_TAG, "*** ShareItem Clicked");
            mShareActionProvider.setShareIntent(createShareIntent());
            return true;
        }
        return false;
    }

    private Intent createShareIntent() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);

        shareIntent.setType("text/plain");
        shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Dwelling Premium Calculation");

        if (premiumModel.getYears() > 10) {
            shareIntent.putExtra(Intent.EXTRA_TEXT, getPremiumDetailString());
        } else {
            shareIntent.putExtra(Intent.EXTRA_TEXT, getPremiumDetailString(currentYear));
        }

        return shareIntent;
    }

    private String getPremiumDetailString() {
        String shareString =
                getString(R.string.detail_hint_sum_insured) + " : Rs." + premiumModel.getSumInsured() + "\n" +
                        getString(R.string.detail_hint_years) + premiumModel.getYears() + " years\n" +
                        getString(R.string.detail_hint_basic_premium) + " : Rs." + premiumModel.getBasicPremium() + "\n" +
                        getString(R.string.detail_hint_stfi) + " : Rs." + premiumModel.getStfi() + "\n" +
                        getString(R.string.detail_hint_eq) + " : Rs." + premiumModel.getEq() + "\n" +
                        labelDiscountedPremium.getText().toString() + " : Rs." + premiumModel.getDiscountedBasicPremium() + "\n" +
                        getString(R.string.detail_hint_premium_total) + " : Rs." + premiumModel.getTotalPremium() + "\n" +
                        getString(R.string.detail_hint_service_tax) + " : Rs." + premiumModel.getServiceTax() + "\n" +
                        getString(R.string.detail_hint_grand_total) + " : Rs." + premiumModel.getGrandTotal();

        return shareString;
    }

    private String getPremiumDetailString(int currentYear) {

        String shareString =
                getString(R.string.detail_hint_sum_insured) + " : Rs." + premiumModel.getSumInsured() + "\n" +
                        getString(R.string.detail_hint_years) + premiumModel.getYears() + " years\n" +
                        getString(R.string.detail_hint_basic_premium) + " : Rs." + premiumModel.getBasicPremium() + "\n" +
                        getString(R.string.detail_hint_stfi) + " : Rs." + premiumModel.getStfi() + "\n" +
                        getString(R.string.detail_hint_eq) + " : Rs." + premiumModel.getEq() + "\n" +
                        labelDiscountedPremium.getText().toString() + " : Rs." + premiumModel.getDiscountedBasicPremiumList().get(currentYear) + "\n" +
                        getString(R.string.detail_hint_premium_total) + " : Rs." + premiumModel.getTotalPremiumList().get(currentYear) + "\n" +
                        getString(R.string.detail_hint_service_tax) + " : Rs." + premiumModel.getServiceTaxList().get(currentYear) + "\n" +
                        getString(R.string.detail_hint_grand_total) + " : Rs." + premiumModel.getGrandTotalList().get(currentYear);

        return shareString;
    }
}
