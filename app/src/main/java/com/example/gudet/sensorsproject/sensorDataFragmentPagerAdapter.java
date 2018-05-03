package com.example.gudet.sensorsproject;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class sensorDataFragmentPagerAdapter extends FragmentPagerAdapter{
    private Context mContext;
    String type;



    public sensorDataFragmentPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;

    }


    // This determines the fragment for each tab
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new lightDataFragment();
        } else if (position == 1){
            return new pressureDataFragment();
        } else if (position == 2){
            return new tempDataFragment();
        } else {
            return new lightDataFragment();
        }
    }

    // This determines the number of tabs
    @Override
    public int getCount() {
        return 4;
    }

    // This determines the title for each tab
    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        switch (position) {
            case 0:
                return mContext.getString(R.string.light);
            case 1:
                return mContext.getString(R.string.pressure);
            case 2:
                return mContext.getString(R.string.temperature);

            default:
                return null;
        }
    }


}
