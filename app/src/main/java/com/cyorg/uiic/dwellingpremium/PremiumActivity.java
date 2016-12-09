package com.cyorg.uiic.dwellingpremium;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.cyorg.uiic.dwellingpremium.fragments.PremiumDetailFragment;
import com.cyorg.uiic.dwellingpremium.fragments.PremiumSummaryFragment;
import com.cyorg.uiic.dwellingpremium.utils.DwellingConstants;

/**
 * Created by HCL on 12/6/2016.
 */
public class PremiumActivity extends AppCompatActivity {

    private static final String LOG_TAG = PremiumActivity.class.getSimpleName();

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_premium);

        int years = getIntent().getIntExtra(DwellingConstants.YEARS_INSURED_KEY, 1);
        Log.d(PremiumActivity.class.getSimpleName(), "*** Inside Premium Activity ***");

        fragmentManager = getFragmentManager();

        if (years > 10) {

            Fragment fragment = fragmentManager.findFragmentByTag(PremiumDetailFragment.FRAGMENT_TAG);
            //Fragment Null check is mandatory. This will avoid creating the same fragment over the existing one therby creating two copies in scrollview.
            if(fragment == null) {
                fragmentManager.beginTransaction().add(R.id.premium_container, new PremiumDetailFragment(), PremiumDetailFragment.FRAGMENT_TAG).commit();
            }   else    {
                fragmentManager.beginTransaction().replace(R.id.premium_container, new PremiumDetailFragment(), PremiumDetailFragment.FRAGMENT_TAG).commit();
            }

        } else {
            fragmentManager.beginTransaction().add(R.id.premium_container, new PremiumSummaryFragment(), PremiumSummaryFragment.FRAGMENT_TAG).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.i(LOG_TAG,"Activity OptionMenu Creation");
        return super.onCreateOptionsMenu(menu);
        //return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.i(LOG_TAG,"Activity MenuListener Called");
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        // If the fragment exists and has some back-stack entry
        if (fragmentManager != null && fragmentManager.getBackStackEntryCount() > 0) {
            // Get the fragment fragment manager - and pop the backstack
            fragmentManager.popBackStack();
        }
        // Else, nothing in the direct fragment back stack
        else {
            // Let super handle the back press
            super.onBackPressed();
        }
    }

}
