package com.example.gudet.sensorsproject;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class Sensors extends FragmentActivity {
    TextView tv;

    View_Pager2 view_Pager2;
    View_Pager4 view_pager3;
    public static lightDataFragment llightDataFragment;
    public static pressureDataFragment ppressureDataFragment;
    public static tempDataFragment ttempDataFragment;
    //ArrayList<String> sValues;
    public static lightFragment llightFragment;
    public static pressureFragment ppressureFragment;
    public static tempFragment ttempFragment;
    CommonMethods commonMethods = new CommonMethods();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensor);

        view_Pager2 = new View_Pager2();
        view_pager3 = new View_Pager4();

        ArrayList<MyData> filelist = (ArrayList<MyData>) getIntent().getSerializableExtra("dataa");
        Log.d("val", "" + filelist.size());

        ArrayList<Float> indivResLight = commonMethods.getIndividualResultsLight(filelist);
        Bundle bundlLight = new Bundle();
        bundlLight.putSerializable("valueLight", indivResLight);
        Log.d("light", "" +indivResLight);
        llightDataFragment = new lightDataFragment();
        llightDataFragment.setArguments(bundlLight);

        llightFragment = new lightFragment();
        llightFragment.setArguments(bundlLight);

        ArrayList<Float> indivResPressure = commonMethods.getIndividualResultsPressure(filelist);
        Bundle bundlPressure = new Bundle();
        bundlPressure.putSerializable("valuePressure", indivResPressure);
        ppressureDataFragment = new pressureDataFragment();
        Log.d("pressure", "" +indivResPressure);
        ppressureDataFragment.setArguments(bundlPressure);

        ppressureFragment = new pressureFragment();
        ppressureFragment.setArguments(bundlPressure);

        ArrayList<Float> indivResTemperature = commonMethods.getIndividualResultsTemperature(filelist);
        Bundle bundlTemp = new Bundle();
        bundlTemp.putSerializable("valueTemperature", indivResTemperature);
        ttempDataFragment = new tempDataFragment();
        ttempDataFragment.setArguments(bundlTemp);

        ttempFragment = new tempFragment();
        ttempFragment.setArguments(bundlTemp);
    }

    public void sensorActivity(View view) {
       getSupportFragmentManager().beginTransaction().replace(R.id.container1, view_Pager2).commit();
    }

    @Override
    public void setTitle(int titleId) {
        super.setTitle("Sensors");
    }

    public void graphView(View view) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container1, view_pager3).commit();
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
            public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
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
                    return llightDataFragment;
                } else if (position == 1) {
                    return ppressureDataFragment;
                } else if (position == 2) {
                    return ttempDataFragment;
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
    public static class View_Pager4 extends Fragment {

                @Override
                public void onCreate(Bundle savedInstanceState) {
                    super.onCreate(savedInstanceState);
                }

                @Override
                public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
                    return inflater.inflate(R.layout.view_pager, container, false);

                }

                @Override
                public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
                    super.onViewCreated(view, savedInstanceState);

                    // Find the view pager that will allow the user to swipe between fragments
                    ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewPager);

                    // Create an adapter that knows which fragment should be shown on each page
                    sensorFragmentPagerAdapter1 adapter = new sensorFragmentPagerAdapter1(getActivity(), getFragmentManager());

                    // Set the adapter onto the view pager
                    viewPager.setAdapter(adapter);

                    // Give the TabLayout the ViewPager

                    TabLayout tabLayout = (TabLayout) view.findViewById(R.id.sliding_tabs);
                    tabLayout.setupWithViewPager(viewPager);
                }
            }
            public static class sensorFragmentPagerAdapter1 extends FragmentPagerAdapter {
                private Context mContext;
                String type;
                public sensorFragmentPagerAdapter1(Context context, FragmentManager fm) {
                    super(fm);
                    mContext = context;
                }
                // This determines the fragment for each tab
                @Override
                public Fragment getItem(int position) {
                    if (position == 0) {
                        return  llightFragment;
                    } else if (position == 1){
                        return ppressureFragment;
                    } else if (position == 2){
                        return  ttempFragment;
                    } else {
                        return llightFragment;
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
