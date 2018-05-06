package com.example.gudet.sensorsproject;

import java.util.ArrayList;
import java.util.Collections;

public class CommonMethods {

    private Float minValue;
    private Float maxValue;
    private Float avgValue;

    public Float getMaxValue(ArrayList<Float> al){
        maxValue = Collections.max(al);
        return  maxValue;
    }
    public Float getMinValue(ArrayList<Float> al){
        minValue =  Collections.min(al);
        return minValue;
    }
    public Float getAvgValue(ArrayList<Float> al) {
         avgValue = 0f;
        if(!al.isEmpty()) {
            for (Float allValues : al) {
                avgValue += allValues;
            }
            return avgValue / al.size();
        }
        return avgValue;
    }
    public ArrayList<Float> getIndividualResultsLight(ArrayList<MyData> filelist) {

            ArrayList<Float> results = new ArrayList<>();
                        for  (int i=0; i < filelist.size(); i++) {
                  results.add(filelist.get(i).getLightSensor());
            }
            return results;
    }
    public ArrayList<Float> getIndividualResultsPressure(ArrayList<MyData> filelist) {

        ArrayList<Float> results = new ArrayList<>();
        for  (int i=0; i < filelist.size(); i++) {
            results.add(filelist.get(i).getPressureSensor());
        }
        return results;
    }
    public ArrayList<Float> getIndividualResultsTemperature(ArrayList<MyData> filelist) {

        ArrayList<Float> results = new ArrayList<>();

        for  (int i=0; i < filelist.size(); i++) {
            results.add(filelist.get(i).getTempSensor());
        }
        return results;
    }
    public ArrayList<Float> getIndividualResultsDate(ArrayList<MyData> filelist) {

        ArrayList<Float> results = new ArrayList<>();

        for  (int i=0; i < filelist.size(); i++) {
            results.add(Float.valueOf(filelist.get(i).getDate()));
        }
        return results;
    }
}
