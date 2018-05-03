package com.example.gudet.sensorsproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class Sensors extends FragmentActivity {
    TextView tv;
    View_Pager2 view_Pager2;
    static lightDataFragment llightDataFragment;
    static pressureDataFragment ppressureDataFragment;
    static tempDataFragment ttempDataFragment;
    ArrayList<String> sValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensor);


      //  sValues = getIntent().getExtras("dataa");

        Intent in = getIntent();
        Bundle args = in.getBundleExtra("dataa");
        ArrayList<MyData> res = (ArrayList<MyData>) args.getSerializable("list");
        Log.d("val", "" +res);
      //  Bundle bundl = new Bundle();
      //  bundl.putStringArrayList("valuee", sValues);
        //bundl.getStringArrayList("data");



        view_Pager2 = new View_Pager2();

       /* llightDataFragment = new lightDataFragment();
        llightDataFragment.setArguments(res);

        ppressureDataFragment = new pressureDataFragment();
        ppressureDataFragment.setArguments(res);

        ttempDataFragment = new tempDataFragment();
        ttempDataFragment.setArguments(res);*/
/*
        Bundle bundl = new Bundle();
        bundl.getStringArrayList("data");

        sensorDataFragmentPagerAdapter sensorDisplay = new sensorDataFragmentPagerAdapter(this, getSupportFragmentManager());
      sensorDisplay.setArguments(bundl);*/
    }

    public void sensorActivity(View view) {


       getSupportFragmentManager().beginTransaction().replace(R.id.container1, view_Pager2).commit();


    }

    @Override
    public void setTitle(int titleId) {
        super.setTitle("Sensors");
    }

    public void graphView(View view) {

        getSupportFragmentManager().beginTransaction().replace(R.id.container1, view_Pager2).commit();

    }



    public static class View_Pager2 extends Fragment {


        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.view_pager1, container, false);

        }

        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            // Find the view pager that will allow the user to swipe between fragments
            ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewPager1);

            // Create an adapter that knows which fragment should be shown on each page
            sensorDataFragmentPagerAdapter1 adapter = new sensorDataFragmentPagerAdapter1(getActivity(), getFragmentManager());

            // Set the adapter onto the view pager
            viewPager.setAdapter(adapter);

            // Give the TabLayout the ViewPager

            TabLayout tabLayout = (TabLayout) view.findViewById(R.id.sliding_tabs1);
            tabLayout.setupWithViewPager(viewPager);
        }
    }
    public static class sensorDataFragmentPagerAdapter1 extends FragmentPagerAdapter {
        private Context mContext;
        String type;



        public sensorDataFragmentPagerAdapter1(Context context, FragmentManager fm) {
            super(fm);
            mContext = context;

        }

        // This determines the fragment for each tab
        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return  llightDataFragment;
            } else if (position == 1){
                return ppressureDataFragment;
            } else if (position == 2){
                return  ttempDataFragment;
            } else {
                return llightDataFragment;
            }
        }

        // This determines the number of tabs
        @Override
        public int getCount() {
            return 3;
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
}
