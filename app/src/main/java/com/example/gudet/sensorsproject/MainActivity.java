package com.example.gudet.sensorsproject;

import android.hardware.SensorManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private SensorManager mSensorManager;// sensorManager object
    private Sensor sensorTypeL,  sensorTypeP,  sensorTypeT;
    private MyDataSource datasource;
    TextView textView;
    ArrayList<MyData> result;
    String result1 = "";
    String result2 = "";
    String result3 = "";


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=(TextView)findViewById(R.id.textView);
        textView.setMovementMethod(new ScrollingMovementMethod());

        //List<Sensor> lightSensor  =   mSensorManager.getSensorList(Sensor.TYPE_LIGHT);
        dataBaseCreation();

        mSensorManager = (SensorManager)
                getSystemService(Context.SENSOR_SERVICE);
        sensorTypeL = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        sensorTypeP = mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        sensorTypeT = mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);

        mSensorManager.registerListener(lightListener,sensorTypeL, SensorManager.SENSOR_DELAY_UI);
        mSensorManager.registerListener(pressureListener,sensorTypeP, SensorManager.SENSOR_DELAY_UI);
        mSensorManager.registerListener(tempListener,sensorTypeT, SensorManager.SENSOR_DELAY_UI);
    }



    public void onClickDeleteSensor(View view) {
    }

    public void sensorStatistics(View view) {
        goToPagerActivity();
    }

    private void goToPagerActivity() {

        result = getSensorData();
        Intent in = new Intent(MainActivity.this, Sensors.class);
        Bundle bund= new Bundle();
        bund.putSerializable("list", (Serializable) result);
        in.putExtra("dataa", bund);
        startActivity(in);
    }

    public void onDeleteDB(View view) {
        datasource.deleteDataBase(this);
        dataBaseCreation();

    }


    final SensorEventListener lightListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            if(event.sensor.getType() == Sensor.TYPE_LIGHT){
                float light = event.values[0];
                result1 += light;
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    final SensorEventListener pressureListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            if(event.sensor.getType() == Sensor.TYPE_PRESSURE){
                float pressure = event.values[0];
                result2 += pressure;
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    final SensorEventListener tempListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            if(event.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE){
                float temp = event.values[0];
                result3 += temp;
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    //create database
    void dataBaseCreation(){
        datasource = new MyDataSource(this);
        datasource.open();

    }

    void onClickInsertSensor(View view){
        datasource.InsertSensor(result1, result2, result3);

    }

    void onClickRetrieveSensor(View view) {
       result = getSensorData();
       textView.setText(result.get(0) + ") " + result.get(1) + " ~ " +result.get(2) + " ~ " + result.get(3) +" ~ " + result.get(4) );

       /* Intent i = new Intent(this, Sensors.class).putStringArrayListExtra("dataa", result);
        startActivity(i);*/


    }

    public ArrayList<MyData> getSensorData() {
        return datasource.retrieveSensor();
    }

    void onClickQuit(View view){
        datasource.close();
    }

    @Override
    protected void onResume() {
        //datasource.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        //  datasource.close();
        super.onPause();
        mSensorManager.unregisterListener(lightListener,sensorTypeL);
        mSensorManager.unregisterListener(pressureListener,sensorTypeP);
        mSensorManager.unregisterListener(tempListener,sensorTypeT);

    }


}
