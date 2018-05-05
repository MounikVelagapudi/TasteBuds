package com.example.gudet.sensorsproject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;

public class tempDataFragment extends Fragment {
    EditText TValue, TMinValue, TMaxValue, TAvgValue;
    ArrayList<Float> al;
    CommonMethods commonMethods = new CommonMethods();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tempdata_fragment, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        al = (ArrayList<Float>)getArguments().getSerializable("valueTemperature");

        if(al.size() > 0) {
            EditText TValue = view.findViewById(R.id.TValue);
            EditText TMinValue = view.findViewById(R.id.TMinValue);
            EditText TMaxValue = view.findViewById(R.id.TMaxValue);
            EditText TAvgValue = view.findViewById(R.id.TAvgValue);

            Float minValue = commonMethods.getMinValue(al);
            TMinValue.setText("" + minValue);

            Float maxValue = commonMethods.getMaxValue(al);
            TMaxValue.setText("" + maxValue);

            Float avg = commonMethods.getAvgValue(al);
            TAvgValue.setText("" + avg);

            String myfloatvariable = "" + al.get(1);
            TValue.setText(myfloatvariable);

            //   TValue.setText(al.get(3));
        }
    }

}