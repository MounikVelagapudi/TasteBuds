package com.example.gudet.sensorsproject;

import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private SensorManager mSensorManager;// sensorManager object
    private Sensor sensorTypeL,  sensorTypeP,  sensorTypeT;
    private MyDataSource datasource;
    TextView textView;
    ArrayList<MyData> result;
    String result1 = "";
    String result2 = "";
    String result3 = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        textView.setMovementMethod(new ScrollingMovementMethod());

        dataBaseCreation();

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        sensorTypeL = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        sensorTypeP = mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        sensorTypeT = mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);

    }
    @Override

    protected void onStart() {
        super.onStart();
        if (sensorTypeL != null) {
            mSensorManager.registerListener(lightListener, sensorTypeL, SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (sensorTypeP != null) {
            mSensorManager.registerListener(pressureListener, sensorTypeP, SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (sensorTypeT != null) {
            mSensorManager.registerListener(tempListener, sensorTypeT, SensorManager.SENSOR_DELAY_NORMAL);
        }

    }
    private SensorEventListener lightListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
                for (float light : event.values) {
                    result1 = String.valueOf(light);
                }
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };
    private SensorEventListener pressureListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.sensor.getType() == Sensor.TYPE_PRESSURE) {
                for (float pressure : event.values) {
                    result2 = String.valueOf(pressure);
                }
            }
        }
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };
    private SensorEventListener tempListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            if(event.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE){
                for (float temperature : event.values) {
                    result3 = String.valueOf(temperature);
                }
            }
        }
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
    @Override
    protected void onResume () {
        super.onResume();

        if (sensorTypeL != null) {
            mSensorManager.registerListener(lightListener, sensorTypeL, SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (sensorTypeP != null) {
            mSensorManager.registerListener(pressureListener, sensorTypeP, SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (sensorTypeT != null) {
            mSensorManager.registerListener(tempListener, sensorTypeT, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }
    @Override
    protected void onPause () {

        super.onPause();
        mSensorManager.unregisterListener(lightListener);
        mSensorManager.unregisterListener(pressureListener);
        mSensorManager.unregisterListener(tempListener);
    }
    //create database
    void dataBaseCreation(){
        datasource = new MyDataSource(this);
        datasource.open();
    }
    public void onClickInsertSensor(View view){

        datasource.InsertSensor(result1, result2, result3);
    }
    public void onClickRetrieveSensor(View view) {
        String res = getSensorData1();
        textView.setText(res);

        //result = getSensorData();
        //textView.setText(result.toString());
      /*
       for (int i = 0; i < result.size(); i++) {
           textView.setText(result.get(i).getLightSensor() +
                   " ~" + result.get(i).getPressureSensor() +
                   " ~ " + result.get(i).getTempSensor() +
                   " ~ " + result.get(i).getDate()+"\n");
       }*/

       /* CommonMethods commonMethods = new CommonMethods();
        result = getSensorData();
        ArrayList<Float> alLight = commonMethods.getIndividualResultsLight(result);
        ArrayList<Float> alPressure = commonMethods.getIndividualResultsPressure(result);
        ArrayList<Float> alTemp = commonMethods.getIndividualResultsTemperature(result);
        for (int i = 0; i < alLight.size(); i++) {
            textView.setText(alLight.get(i)+ " ~"+ alPressure.get(i) +
                    " ~" + alTemp.get(i));*/
        }



    public String getSensorData1() {
        return datasource.retrieveSensor1();
    }
    public ArrayList<MyData> getSensorData() {
        return datasource.retrieveSensor();
    }
    public void onClickDeleteSensor(View view) {
        datasource.deleteDataBase(this);
        dataBaseCreation();
    }
    public void onStatisticsActivity(View view) {
        goToPagerActivity();
    }
    private void goToPagerActivity() {
        result = getSensorData();
        Intent in = new Intent(MainActivity.this, Sensors.class);
        in.putExtra("dataa", result);
        startActivity(in);
    }
    void onClickQuit (View view){
        finish();
    }

}