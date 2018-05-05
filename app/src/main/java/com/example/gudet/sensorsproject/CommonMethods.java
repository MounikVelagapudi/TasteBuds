package com.example.gudet.sensorsproject;

import java.util.ArrayList;
import java.util.Collections;

public class CommonMethods {

    private Float min;
    private Float max;
    private Float avg;

    public Float getMaxValue(ArrayList<Float> al){
        max = Collections.max(al);
        return  max;
    }
    public Float getMinValue(ArrayList<Float> al){
        min =  Collections.min(al);
        return min;
    }
    public Float getAvgValue(ArrayList<Float> al) {
         avg = 0f;
        if(!al.isEmpty()) {
            for (Float mark : al) {
                avg += mark;
            }
            return avg / al.size();
        }
        return avg;
    }
    public ArrayList<Float> getIndividualResultsLight(ArrayList<MyData> filelist) {

            ArrayList<Float> results = new ArrayList<>();
             //  MyData myData = new MyData();

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
}
