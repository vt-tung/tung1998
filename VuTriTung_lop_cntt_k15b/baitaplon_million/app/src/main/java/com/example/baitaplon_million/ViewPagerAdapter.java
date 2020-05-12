package com.example.baitaplon_million;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment> frmlist =  new ArrayList<>();
    ArrayList<String> title = new ArrayList<>();

    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return frmlist.get(position);
    }

    @Override
    public int getCount() {
        return frmlist.size();
    }

    @NonNull
    @Override
    public CharSequence getPageTitle(int position){
        return title.get(position);
    }

    public void add(Fragment frm, String til){
        frmlist.add(frm);
        title.add(til);
    }

}
