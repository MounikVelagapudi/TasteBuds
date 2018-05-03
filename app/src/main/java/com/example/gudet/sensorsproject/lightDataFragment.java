package com.example.gudet.sensorsproject;

import android.hardware.Sensor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class lightDataFragment extends Fragment {
    private Sensor sensorTypeL;

    EditText lIntensity, lMinValue, lMaxValue, lAvgValue;
    ArrayList<String> al;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Bundle b;


        return inflater.inflate(R.layout.lightdata_fragment, container, false);



    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        al = getArguments().getStringArrayList("valuee");

        EditText lIntensity = view.findViewById(R.id.LIntensity);
        EditText lMinValue = view.findViewById(R.id.LMinValue);
        EditText lMaxValue = view.findViewById(R.id.LMaxValue);
        EditText lAvgValue = view.findViewById(R.id.LMaxValue);

        lIntensity.setText(al.get(1));

    }

}