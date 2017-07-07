package com.cyorg.uiic.dwellingpremium.activity

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.view.MenuItemCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.ShareActionProvider
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.cyorg.uiic.dwellingpremium.R
import com.cyorg.uiic.dwellingpremium.databinding.ActivityPremium1Binding
import com.cyorg.uiic.dwellingpremium.model.Premium
import com.cyorg.uiic.dwellingpremium.model.Scheme
import com.cyorg.uiic.dwellingpremium.utils.DwellingConstants
import com.cyorg.uiic.dwellingpremium.utils.DwellingConstants.DECIMAL_FORMAT
import com.cyorg.uiic.dwellingpremium.utils.DwellingConstants.PREMIUM
import kotlinx.android.synthetic.main.activity_premium1.*

/**
 * Created by yorga on 07-07-2017.
 */
class PremiumActivity:AppCompatActivity()   {
    
    var mShareActionProvider : ShareActionProvider? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding:ActivityPremium1Binding = DataBindingUtil.setContentView(this,R.layout.activity_premium1)
        
        PREMIUM = intent.getSerializableExtra(DwellingConstants.PREMIUM_OBJECT_KEY) as Premium
        val scheme = intent.getStringExtra(DwellingConstants.SCHEME_KEY)

        binding.premium =  PREMIUM
        
        if(scheme == Scheme.Scheme_A.value)
            table_row_discount.visibility = View.GONE
        
        if(PREMIUM.years >= 10) {
            detail_label_text_discounted_premium.text = detail_label_text_discounted_premium.text.toString().replace("%1%", "50%")
        }else{
            detail_label_text_discounted_premium.text = detail_label_text_discounted_premium.text.toString().replace("%1%", (5 * PREMIUM.years).toString())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.premium_detail_menu, menu)

        //Read the share menu item
        val shareItem = menu.findItem(R.id.menu_item_share)
        //Assign EasyShareAction to SHARE MenuItem
        mShareActionProvider = MenuItemCompat.getActionProvider(shareItem) as ShareActionProvider
        //Custom File to write share history
        mShareActionProvider?.setShareHistoryFileName("custom_share_history.xml")

        return true
        //return super.onCreateOptionsMenu(menu);
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_item_share -> {
                mShareActionProvider?.setShareIntent(createShareIntent())
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun createShareIntent(): Intent {
        val shareIntent = Intent(Intent.ACTION_SEND)

        shareIntent.type = "text/plain"
        shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Dwelling Premium Calculation")

        shareIntent.putExtra(Intent.EXTRA_TEXT, getPremiumDetailString())

        return shareIntent
    }

    private fun getPremiumDetailString(): String {
        val shareString = getString(R.string.detail_hint_sum_insured) + " : Rs." + DECIMAL_FORMAT.format(PREMIUM.sumInsured) + "\n" +
                getString(R.string.detail_hint_years) + " : " + PREMIUM.years + " years\n" +
                getString(R.string.detail_hint_basic_premium) + " : Rs." + DECIMAL_FORMAT.format(PREMIUM.basicPremium) + "\n" +
                getString(R.string.detail_hint_stfi) + " : Rs." + DECIMAL_FORMAT.format(PREMIUM.stfi) + "\n" +
                getString(R.string.detail_hint_eq) + " : Rs." + DECIMAL_FORMAT.format(PREMIUM.eq) + "\n" +
                detail_label_text_discounted_premium.text.toString() + " : Rs." + DECIMAL_FORMAT.format(PREMIUM.discountedBasicPremium) + "\n" +
                getString(R.string.detail_hint_premium_total) + " : Rs." + DECIMAL_FORMAT.format(PREMIUM.totalPremium) + "\n" +
                getString(R.string.detail_hint_service_tax) + " : Rs." + DECIMAL_FORMAT.format(PREMIUM.serviceTax) + "\n" +
                getString(R.string.detail_hint_grand_total) + " : Rs." + DECIMAL_FORMAT.format(PREMIUM.grandTotal)

        return shareString
    }
    
}
