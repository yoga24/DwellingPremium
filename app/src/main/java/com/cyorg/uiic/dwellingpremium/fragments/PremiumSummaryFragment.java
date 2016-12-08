package com.cyorg.uiic.dwellingpremium.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.cyorg.uiic.dwellingpremium.R;
import com.cyorg.uiic.dwellingpremium.list.adapter.PremiumSummaryAdapter;
import com.cyorg.uiic.dwellingpremium.model.PremiumModel;
import com.cyorg.uiic.dwellingpremium.model.SummaryModel;
import com.cyorg.uiic.dwellingpremium.utils.DwellingConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yoganand on 12/6/2016.
 */
public class PremiumSummaryFragment extends Fragment {

    public static final String FRAGMENT_TAG = PremiumSummaryFragment.class.getSimpleName();
    private static final String LOG_TAG = PremiumSummaryFragment.class.getSimpleName();

    ListView summaryListView;
    List<SummaryModel> summaryModelList;

    public PremiumSummaryFragment()    {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);

        View rootView = inflater.inflate(R.layout.fragment_premium_summary,container,false);

        PremiumModel premiumModel = (PremiumModel) getActivity().getIntent().getSerializableExtra(DwellingConstants.PREMIUM_OBJECT_KEY);
        summaryModelList = createSummaryModelList(premiumModel);

        summaryListView = (ListView) rootView.findViewById(R.id.premium_summary_list);
        ArrayAdapter<SummaryModel> summaryAdapter= new PremiumSummaryAdapter(summaryModelList,getActivity());
        summaryListView.setAdapter(summaryAdapter);
        summaryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Log.i(LOG_TAG,"Item Listener Called -> "+position);

                Bundle bundle = new Bundle();
                bundle.putInt(DwellingConstants.CURRENT_YEAR_KEY,position);

                PremiumDetailFragment premiumDetailFragment = new PremiumDetailFragment();
                premiumDetailFragment.setArguments(bundle);

                getActivity().getFragmentManager().beginTransaction().replace(R.id.premium_container,premiumDetailFragment,PremiumDetailFragment.FRAGMENT_TAG).addToBackStack(null).commit();
            }
        });

        return rootView;
    }

    private List<SummaryModel> createSummaryModelList(PremiumModel premiumModel) {
        List<SummaryModel> summaryList = new ArrayList<>();

        for(int index = 0 ; index < premiumModel.getTotalPremiumList().size() ; index++)    {
            SummaryModel summaryModel = new SummaryModel();

            summaryModel.setYear(index+1);
            summaryModel.setTotalPremium(premiumModel.getGrandTotalList().get(index));

            summaryList.add(summaryModel);
        }

        return summaryList;
    }
}
