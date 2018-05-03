package com.example.gudet.sensorsproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;

public class tempDataFragment extends Fragment {
    EditText TValue, TMinValue, TMaxValue, TAvgValue;
    ArrayList<String> al;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tempdata_fragment, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        al = getArguments().getStringArrayList("valuee");

        EditText TValue = view.findViewById(R.id.TValue);
        EditText TMinValue = view.findViewById(R.id.TMinValue);
        EditText TMaxValue = view.findViewById(R.id.TMaxValue);
        EditText TAvgValue = view.findViewById(R.id.TAvgValue);

        TValue.setText(al.get(3));

    }

}