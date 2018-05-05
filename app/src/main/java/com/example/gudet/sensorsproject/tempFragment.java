package com.example.gudet.sensorsproject;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

public class tempFragment extends Fragment {
    ArrayList<Float> al;
    CommonMethods commonMethods = new CommonMethods();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.temp_fragment,container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        al = (ArrayList<Float>)getArguments().getSerializable("valueTemperature");
        GraphView graph = (GraphView) view.findViewById(R.id.graph);
        DataPoint[] dataPoints = new DataPoint[al.size()]; // declare an array of DataPoint objects with the same size as your list
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(dataPoints);
        for (int i = 0; i < al.size(); i++) {
            // add new DataPoint object to the array for each of your list entries
            dataPoints[i] = new DataPoint(al.get(4), al.get(3));
            graph.addSeries(series);
        }

    }

}


