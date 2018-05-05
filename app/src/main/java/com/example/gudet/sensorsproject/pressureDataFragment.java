package com.example.gudet.sensorsproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;

public class pressureDataFragment extends Fragment {
    EditText PValue, PMinValue, PMaxValue, PAvgValue;
    ArrayList<Float> al;
    CommonMethods commonMethods = new CommonMethods();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.pressuredata_fragment,container, false);
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        al = (ArrayList<Float>)getArguments().getSerializable("valuePressure");
        if(al.size() > 0) {
            EditText PValue = view.findViewById(R.id.PValue);
            EditText PMinValue = view.findViewById(R.id.PMinValue);
            EditText PMaxValue = view.findViewById(R.id.PMaxValue);
            EditText PAvgValue = view.findViewById(R.id.PAvgValue);

            Float minValue = commonMethods.getMinValue(al);
            PMinValue.setText("" + minValue);

            Float maxValue = commonMethods.getMaxValue(al);
            PMaxValue.setText("" + maxValue);

            Float avg = commonMethods.getAvgValue(al);
            PAvgValue.setText("" + avg);

            String myfloatvariable = "" + al.get(1);
            PValue.setText(myfloatvariable);

        }
    }
}
