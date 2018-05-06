package com.example.gudet.sensorsproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.Calendar;

public class lightFragment extends Fragment {
    EditText lIntensity, lMinValue, lMaxValue, lAvgValue;
    ArrayList<Float> al;
    CommonMethods commonMethods = new CommonMethods();

    // TextView mDisplayDateTime;
    // Calendar mDateAndTime = Calendar.getInstance();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.light_fragment, container, false);
        // mDisplayDateTime = (TextView) view.findViewById(R.id.dateTime);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        al = (ArrayList<Float>) getArguments().getSerializable("valueLight");

        GraphView graph = (GraphView) view.findViewById(R.id.graph);
        DataPoint[] dataPoints = new DataPoint[al.size()];
        // declare an array of DataPoint objects with the same size as your list

        for (int i = 0; i < al.size(); i++) {
            // add new DataPoint object to the array for each of your list entries
            dataPoints[i] = new DataPoint(al.get(4), al.get(1));
        }
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(dataPoints);
        graph.addSeries(series);
    }
}


  /* public void onDateClicked(View v) {


        DatePickerDialog.OnDateSetListener mDateListener = new

                DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        mDateAndTime.set(Calendar.YEAR, year);
                        mDateAndTime.set(Calendar.MONTH, monthOfYear);
                        mDateAndTime.set(DAY_OF_MONTH, dayOfMonth);
                        updateDateAndTimeDisplay();
                    }
                };

        new DatePickerDialog(getActivity(), mDateListener, mDateAndTime.get(Calendar.YEAR),  mDateAndTime.get(Calendar.MONTH), mDateAndTime.get(DAY_OF_MONTH)).show();

    }
    private void updateDateAndTimeDisplay() {
        mDisplayDateTime.setText(DateUtils.formatDateTime(getActivity(), mDateAndTime.getTimeInMillis(), DateUtils.FORMAT_SHOW_DATE  & DateUtils.FORMAT_SHOW_TIME));
    }
*/



