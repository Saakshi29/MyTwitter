package com.example.daksh.mytwitter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Daksh Garg on 7/24/2017.
 */

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0)
        {
            UsertimelineActivity fragment=new UsertimelineActivity();
            return fragment;
        }
        if(position==1)
        {
            TrendFragment fragment=new TrendFragment();
            return fragment;
        }
        if(position==2)
        {
            TimelineActivity fragment=new TimelineActivity();
            return fragment;
        }
        if(position==3)
        {
            TimelineActivity fragment=new TimelineActivity();
            return fragment;
        }


        return null;

    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "SECTION 1";
            case 1:
                return "SECTION 2";
            case 2:
                return "SECTION 3";
            case 3:
                return "SECTION 4";
        }
        return null;
    }
}
