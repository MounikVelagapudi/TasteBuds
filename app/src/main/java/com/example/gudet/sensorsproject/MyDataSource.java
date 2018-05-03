package com.example.gudet.sensorsproject;

/**
 * Created by mounikvelagapudi on 28/04/18.
 */


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.SensorEvent;
import android.os.AsyncTask;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import static android.content.ContentValues.TAG;


public class MyDataSource {

    // Database fields
    //create objects
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    // AsyncTextRunner runner;


    SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss", Locale.getDefault());
    Date date = new Date();



    private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.COLUMN_DATA1, MySQLiteHelper.COLUMN_DATA2, MySQLiteHelper.COLUMN_DATA3, MySQLiteHelper.COLUMN_DATA4};
    //create constructor
    public MyDataSource(Context context)
    {
        //definition of object

        dbHelper = new MySQLiteHelper(context);
        // dbHelper.DeleteDatabase(context);
    }


    public void open() throws SQLException {

        database = dbHelper.getWritableDatabase();
    }

    void InsertSensor(String value1, String value2, String value3){
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_DATA1, value1);
        values.put(MySQLiteHelper.COLUMN_DATA2, value2);
        values.put(MySQLiteHelper.COLUMN_DATA3, value3);
        values.put(MySQLiteHelper.COLUMN_DATA4, dateFormat.format(date));
      //  Log.d("date ", ""+new Date().getDate());

        long insertId = database.insert(MySQLiteHelper.TABLE_DATA, null,values);
    }
    public void deleteSensor(long id1) {
        long id = id1;
        database.delete(MySQLiteHelper.TABLE_DATA, MySQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }


    public ArrayList<MyData> retrieveSensor() {
        ArrayList<MyData> alResult = null;
        Cursor cursor = database.query(MySQLiteHelper.TABLE_DATA,
                allColumns, null, null,
                null, null, null);
      //  Results results = new Results();
      //  ArrayList<Results> rsult = new ArrayList<Results>();
        ArrayList<MyData> newData=null;
        MyData myData = new MyData();
        String result="";
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            newData = cursorToData(cursor);
             alResult = new ArrayList<MyData>();
            for (int i=0; i< newData.size(); i++){
                myData.setLightSensor(newData.get(i).getLightSensor());
                myData.setPressureSensor(newData.get(i).getPressureSensor());
                myData.setTempSensor(newData.get(i).getTempSensor());
                myData.setDate(newData.get(i).getDate());
                alResult.add(myData);
            }
           /* results.setLight(newData.getLightSensor());
            results.setPressure(newData.getPressureSensor());
            results.setTemperature(newData.getTempSensor());
            results.setDate(newData.getDate());
*/
          //  rsult.add(results);

      /*  rsult.add(String.valueOf(newData.getId()));
            rsult.add(String.valueOf(newData.getLightSensor()));
            rsult.add(String.valueOf(newData.getPressureSensor()));
            rsult.add(String.valueOf(newData.getTempSensor()));
            rsult.add(newData.getDate());*/
            // result+=newData.getId()+" --- "+newData.getLightSensor()+" --- "+newData.getPressureSensor()+" ---  "+newData.getTempSensor()+" --- "+newData.getDate()+"\n";
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return alResult;
    }

//    public ArrayList<Integer> retrieveSensor() {
//        Cursor cursor = null;
//        try {
//             cursor = database.query(MySQLiteHelper.TABLE_DATA,
//                    allColumns, null, null,
//                    null, null, null);
//            MyData newData = null;
//            // String result="";
//            ArrayList<Integer> resultList = new ArrayList<>();
//            cursor.moveToFirst();
//            while (!cursor.isAfterLast()) {
//                newData = cursorToData(cursor);
//                // result+=newData.getId()+" "+newData.getLightSensor()+" "+newData.getPressureSensor()+" "+newData.getTempSensor()+" "+newData.getDate()+"\n";
//                resultList.add((int) newData.getId());
//                resultList.add((int) newData.getLightSensor());
//                resultList.add((int) newData.getPressureSensor());
//                resultList.add((int) newData.getTempSensor());
//                Log.d("----------------", newData.getId()+" "+newData.getLightSensor()+" "+newData.getPressureSensor()+" "+newData.getTempSensor()+" "+newData.getDate());
//                cursor.moveToNext();
//            }
//
//            return resultList;
//
//        } finally {
//            cursor.close();
//        }
//    }

    private ArrayList<MyData> cursorToData(Cursor cursor) {
        ArrayList<MyData> mydataAL = new ArrayList<MyData>();
        MyData mydata = new MyData();

        mydata.setId(cursor.getLong(0));
        mydata.setLightSensor(cursor.getFloat(1));
        mydata.setPressureSensor(cursor.getFloat(2));
        mydata.setTempSensor(cursor.getFloat(3));
        mydata.setDate(cursor.getString(4));
        mydataAL.add(mydata);

        return mydataAL;
    }

    public void close() {
        dbHelper.close();
    }

    public void deleteDataBase(Context context) {

        dbHelper.DeleteDatabase(context);
    }




       /* private class AsyncTextRunner extends AsyncTask<String[], Void, Void> {
            @Override
            protected Void doInBackground(String[]... params) {
                SensorEvent event = events[0].get[i];
            }
            protected void onPostExecute(String result) {
                myText.setText(R.string.message_txt);
                txtView.setText(result);
                txtView.setMovementMethod(new ScrollingMovementMethod());
                Toast.makeText(getApplicationContext(), R.string.note_success,Toast.LENGTH_SHORT).show();
            }
        }
*/
}
