package com.example.gudet.sensorsproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class View_Pager1 extends Fragment {


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
        sensorDataFragmentPagerAdapter adapter = new sensorDataFragmentPagerAdapter(getActivity(), getFragmentManager());

        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);

        // Give the TabLayout the ViewPager

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.sliding_tabs1);
        tabLayout.setupWithViewPager(viewPager);
    }
}
