package com.cyorg.uiic.dwellingpremium.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import com.cyorg.uiic.dwellingpremium.*
import com.cyorg.uiic.dwellingpremium.model.Premium
import com.cyorg.uiic.dwellingpremium.model.Scheme
import com.cyorg.uiic.dwellingpremium.utils.DwellingConstants
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by yorga on 07-07-2017.
 */
class MainActivity : AppCompatActivity() {

    private val LOG_TAG:String = MainActivity::class.java.simpleName
    private var activityContext: Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityContext = this
        setContentView(R.layout.activity_main)

        spinner_premium_scheme.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                //Not Needed
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (parent?.getItemAtPosition(position)) {
                    "Scheme A" -> edit_discount.visibility = View.INVISIBLE
                    "Scheme B" -> edit_discount.visibility = View.VISIBLE
                }
            }
        }

        main_cal_premium_button.setOnClickListener {

            var validSumInsured: Boolean = edit_sum_insured.isValidLong()
            var validYearsInsured: Boolean = edit_years_insured.isValidInt()
            var selectedScheme = spinner_premium_scheme.selectedItem as String
            var schemeB: Boolean = Scheme.Scheme_B.value == selectedScheme


            if (!validSumInsured)
                edit_layout_sum_insured.error = "Invalid Value"
            if (!validYearsInsured)
                edit_layout_years_insured.error = "Invalid Value"
            if (schemeB && !edit_discount.isValidPercentage())
                edit_layout_discount.error = "Invalid Value"

            if (validSumInsured && validYearsInsured) {
                if (!schemeB || (schemeB && edit_discount.isValidPercentage())) {
                    try {
                        Log.i(LOG_TAG, "SumInsured and Years have been entered")
                        val sumInsured = edit_sum_insured.toLong()
                        val yearsInsured = edit_years_insured.toInt()

                        val premiumModel = Premium(sumInsured, yearsInsured)
                        premiumModel.calculatePremium(selectedScheme)

                        val premiumIntent = Intent(activityContext, PremiumActivity::class.java)
                        premiumIntent.putExtra(DwellingConstants.PREMIUM_OBJECT_KEY, premiumModel)
                        premiumIntent.putExtra(DwellingConstants.YEARS_INSURED_KEY, yearsInsured)
                        premiumIntent.putExtra(DwellingConstants.SCHEME_KEY,selectedScheme)

                        startActivity(premiumIntent)

                    } catch (e: Exception) {
                        Log.e(LOG_TAG, "Error occurred in parsing input string")
                        e.printStackTrace()
                    }

                }
            }

        }
    }
}