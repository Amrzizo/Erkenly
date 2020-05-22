package com.amit.erkenly.base.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.amit.erkenly.R;
import com.amit.erkenly.customview.CustomPagerAdpter;
import com.amit.erkenly.customview.CustomViewPager;
import com.google.android.material.tabs.TabLayout;

public class BaseRegisterationFragment extends BaseFragment {

    private View baseRegisterationView;
    private TabLayout tableLayout;
    private CustomViewPager viewPager;
    private CustomPagerAdpter pagerAdpter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        baseRegisterationView = inflater.inflate(R.layout.fragment_base_registeration, container, false);
        tableLayout = baseRegisterationView.findViewById(R.id.tl_registeration_tabs);
        viewPager = baseRegisterationView.findViewById(R.id.view_pager);
        CreateViewPager();
        tableLayout.setupWithViewPager(viewPager);
        viewPager.setHorizontalScrollEnabled(false);

        createTabsIcons();

        return baseRegisterationView;
    }

    private void createTabsIcons() {

        TextView clientTab = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.tab_row_layout, null);
        clientTab.setText("Client");
        TextView CompanyTab = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.tab_row_layout, null);
        CompanyTab.setText("Company");

        tableLayout.getTabAt(0).setCustomView(clientTab);
        tableLayout.getTabAt(1).setCustomView(CompanyTab);

    }

    private void CreateViewPager() {
        pagerAdpter =  new CustomPagerAdpter(getChildFragmentManager());

        ClientRegisterationfragment clientRegisterationfragment = new ClientRegisterationfragment();
        CompanyRegisterationFragment companyRegisterationFragment = new CompanyRegisterationFragment();

        pagerAdpter.addFragment(clientRegisterationfragment, "");
        pagerAdpter.addFragment(companyRegisterationFragment, "");

        viewPager.setAdapter(pagerAdpter);















    }
}
