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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {
    private SensorManager mSensorManager;// sensorManager object
    private Sensor sensorTypeL, sensorTypeP, sensorTypeT;
    private MyDataSource datasource;
    TextView textView;
    ArrayList<MyData> result;
    String result1 = "";
    String result2 = "";
    String result3 = "";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
        textView.setMovementMethod(new ScrollingMovementMethod());

        dataBaseCreation();

        mSensorManager = (SensorManager)
                getSystemService(Context.SENSOR_SERVICE);
        if (mSensorManager != null) {
            sensorTypeL = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
            sensorTypeP = mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
            sensorTypeT = mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        }
    }

    public void onClickDeleteSensor(View view) {
        datasource.deleteDataBase(this);
        dataBaseCreation();
    }

    public void onStatisticsActivity(View view) {

        result = getSensorData();
        Log.d("SIZE - -- - -  ****", String.valueOf(result.size()));
        Intent in = new Intent(MainActivity.this, Sensors.class);
        in.putExtra("dataa", result);
        startActivity(in);
    }

    final SensorEventListener lightListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
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
            if (event.sensor.getType() == Sensor.TYPE_PRESSURE) {
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
            if (event.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE) {
                float temp = event.values[0];
                result3 += temp;
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    //create database
    public void dataBaseCreation() {
        datasource = new MyDataSource(this);
        datasource.open();
    }

    public void onClickInsertSensor(View view) {
        datasource.InsertSensor(result1, result2, result3);
    }

    public void onClickRetrieveSensor(View view) {
      /* result = getSensorData();
       for (int i = 0; i < result.size(); i++) {

           textView.setText(result.get(i).getLightSensor() +
                   " ~" + result.get(i).getPressureSensor() +
                   " ~ " + result.get(i).getTempSensor() +
                   " ~ " + result.get(i).getDate());
       }
   }
       String result = getSensorData1();
       textView.setText(result); */

        CommonMethods commonMethods = new CommonMethods();
        result = getSensorData();
        ArrayList<Float> alLight = commonMethods.getIndividualResultsLight(result);
        ArrayList<Float> alPressure = commonMethods.getIndividualResultsPressure(result);
        ArrayList<Float> alTemp = commonMethods.getIndividualResultsTemperature(result);
        for (int i = 0; i < alLight.size(); i++) {
            textView.setText(alLight.get(i) + " ~" + alPressure.get(i) +
                    " ~" + alTemp.get(i));
        }
    }

    public String getSensorData1() {
        return datasource.retrieveSensor1();
    }

    public ArrayList<MyData> getSensorData() {
        return datasource.retrieveSensor();
    }

    void onClickQuit(View view) {
        datasource.close();
    }

    @Override
    protected void onResume() {
        //datasource.open();
        super.onResume();
        if (sensorTypeL != null) {
            mSensorManager.registerListener(lightListener, sensorTypeL, SensorManager.SENSOR_DELAY_UI);
        }
        if (sensorTypeP != null) {
            mSensorManager.registerListener(pressureListener, sensorTypeP, SensorManager.SENSOR_DELAY_UI);
        }
        if (sensorTypeT != null) {
            mSensorManager.registerListener(tempListener, sensorTypeT, SensorManager.SENSOR_DELAY_UI);
        }
    }

    @Override
    protected void onPause() {
        //  datasource.close();
        super.onPause();
        mSensorManager.unregisterListener(lightListener);
        mSensorManager.unregisterListener(pressureListener);
        mSensorManager.unregisterListener(tempListener);
    }
}