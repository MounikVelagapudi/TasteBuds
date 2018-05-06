package com.example.gudet.sensorsproject;

import java.io.Serializable;

public class MyData implements Serializable {

    private long id;
    private float lightSensor;
    private float tempSensor;
    private float pressureSensor;
    private String date;

    MyData() {
    }

    public String getDate(){ return date;}
    public void setDate(String date){ this.date=date;}

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public float getLightSensor() {
        return lightSensor;
    }
    public void setLightSensor(float lightSensor) {
        this.lightSensor   = lightSensor;
    }

    public float getTempSensor() {
        return tempSensor;
    }
    public void setTempSensor(float tempSensor) {
        this.tempSensor = tempSensor;
    }

    public float getPressureSensor() {
        return pressureSensor;
    }
    public void setPressureSensor(float pressureSensor) {
        this.pressureSensor = pressureSensor;
    }

}
