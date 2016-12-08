package com.cyorg.uiic.dwellingpremium.list.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.cyorg.uiic.dwellingpremium.R;
import com.cyorg.uiic.dwellingpremium.fragments.PremiumDetailFragment;
import com.cyorg.uiic.dwellingpremium.model.SummaryModel;
import com.cyorg.uiic.dwellingpremium.utils.DwellingConstants;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by HCL on 12/8/2016.
 */
public class PremiumSummaryAdapter extends ArrayAdapter<SummaryModel> {

    private List<SummaryModel> summaryPremiumList;
    Context mContext;

    DecimalFormat decimalFormat = new DecimalFormat("\u20B9 ##,##,##,##,###.##");

    private static class ViewHolder {
        TextView yearTextView;
        TextView premiumTextView;
    }

    public PremiumSummaryAdapter(List<SummaryModel> summaryList, Context context) {
        super(context, R.layout.list_view_item, summaryList);
        this.summaryPremiumList = summaryList;
        this.mContext = context;

    }

    private int lastPosition = -1;

//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//
//        SummaryModel model = getItem(position);
//        ViewHolder viewHolder;
//
//        final View result;
//
//        if(convertView == null) {
//            viewHolder = new ViewHolder();
//            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
//
//            convertView = layoutInflater.inflate(R.layout.list_view_item,parent,false);
//            viewHolder.yearTextView = (TextView) convertView.findViewById(R.id.list_item_text_year);
//            viewHolder.premiumTextView = (TextView) convertView.findViewById(R.id.list_item_text_premium);
//
//
//            result = convertView;
//            convertView.setTag(viewHolder);
//        }else {
//            viewHolder = (ViewHolder) convertView.getTag();
//            result=convertView;
//        }
//
//        lastPosition = position;
//
//        viewHolder.yearTextView.setText(position+1);
//        viewHolder.premiumTextView.setText(decimalFormat.format(model.getTotalPremium()));
//
//        return convertView;
//    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        SummaryModel model = summaryPremiumList.get(position);

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.list_view_item, null);

        TextView yearTextView = (TextView) view.findViewById(R.id.list_item_text_year);
        TextView premiumTextView = (TextView) view.findViewById(R.id.list_item_text_premium);

        yearTextView.setText("Year "+String.valueOf(position+1));
        premiumTextView.setText(decimalFormat.format(model.getTotalPremium()));

        return view;
    }
}
