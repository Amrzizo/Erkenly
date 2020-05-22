package com.amit.erkenly.customview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.amit.erkenly.base.fragments.BaseFragment;

import java.util.ArrayList;

public class CustomPagerAdpter extends FragmentPagerAdapter {
    private ArrayList<BaseFragment> fragments ;
    private ArrayList<String> titles;

    public CustomPagerAdpter(@NonNull FragmentManager fm) {
        super(fm);
        fragments = new ArrayList<>();
        titles = new ArrayList<>();
    }

    public void  addFragment (BaseFragment fragment, String title){
        fragments.add(fragment);
        titles.add(title);
    }


    public void  addFragment (BaseFragment fragment, String title, int postion){
        fragments.add(postion, fragment);
        titles.add(title);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }
}
