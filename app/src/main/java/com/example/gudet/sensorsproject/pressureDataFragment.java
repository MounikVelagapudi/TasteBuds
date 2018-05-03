package com.example.gudet.sensorsproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;

public class pressureDataFragment extends Fragment {
    EditText PValue, PMinValue, PMaxValue, PAvgValue;
    ArrayList<String> al;

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

        al = getArguments().getStringArrayList("valuee");

        EditText PValue = view.findViewById(R.id.PValue);
        EditText PMinValue = view.findViewById(R.id.PMinValue);
        EditText PMaxValue = view.findViewById(R.id.PMaxValue);
        EditText PAvgValue = view.findViewById(R.id.PAvgValue);

        PValue.setText(al.get(2));

    }




}
