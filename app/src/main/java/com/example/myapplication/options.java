package com.example.myapplication;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;



public class options extends FragmentStatePagerAdapter {
    private static final String TAG = "options";

    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();
    public options(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment fragment, String title) {
       mFragmentList.add(fragment);
       mFragmentTitleList.add(title);
    }




    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}












