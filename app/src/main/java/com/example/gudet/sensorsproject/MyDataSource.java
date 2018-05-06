package com.example.gudet.sensorsproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MyDataSource {

    // Database fields
    //create objects
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    // AsyncTextRunner runner;


    SimpleDateFormat dateFormat = new SimpleDateFormat("MMM-dd-yy HH:mm:ss", Locale.getDefault());
    Date date = new Date();


    private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.COLUMN_DATA1, MySQLiteHelper.COLUMN_DATA2, MySQLiteHelper.COLUMN_DATA3, MySQLiteHelper.COLUMN_DATA4};
    //create constructor
    public MyDataSource(Context context)
    {
        //definition of object
        dbHelper = new MySQLiteHelper(context);
    }
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void InsertSensor(String value1, String value2, String value3){
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_DATA1, value1);
        values.put(MySQLiteHelper.COLUMN_DATA2, value2);
        values.put(MySQLiteHelper.COLUMN_DATA3, value3);
        values.put(MySQLiteHelper.COLUMN_DATA4, dateFormat.format(date));
        long insertId = database.insert(MySQLiteHelper.TABLE_DATA, null,values);
    }
    public void deleteSensor(Context context) {
        dbHelper.DeleteDatabase(context);

    }
    public String retrieveSensor1() {
        Cursor cursor = database.query(MySQLiteHelper.TABLE_DATA,
                allColumns, null, null,
                null, null, null);
        MyData newData1=null;
        String result="";
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            newData1 = cursorToData1(cursor);
            result+=newData1.getId()+" "+newData1.getLightSensor()+" "+newData1.getPressureSensor()+" "+newData1.getTempSensor()+" "+newData1.getDate()+"\n";
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return result;
    }
    private MyData cursorToData1(Cursor cursor) {
        MyData mydata = new MyData();
        mydata.setId(cursor.getLong(0));
        mydata.setLightSensor(cursor.getFloat(1));
        mydata.setPressureSensor(cursor.getFloat(2));
        mydata.setTempSensor(cursor.getFloat(3));
        mydata.setDate(cursor.getString(4));
        return mydata;
    }
    public ArrayList<MyData> retrieveSensor() {
        ArrayList<MyData> alResult = null;
        Cursor cursor = database.query(MySQLiteHelper.TABLE_DATA,
                allColumns, null, null,
                null, null, null);

        ArrayList<MyData> newData=null;
        alResult = new ArrayList<MyData>();
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            MyData mydata = new MyData();
            mydata.setId(cursor.getLong(0));
            mydata.setLightSensor(cursor.getFloat(1));
            mydata.setPressureSensor(cursor.getFloat(2));
            mydata.setTempSensor(cursor.getFloat(3));
            mydata.setDate(cursor.getString(4));
            alResult.add(mydata);
            cursor.moveToNext();
        }
        cursor.close();
        return alResult;
    }

   /* private ArrayList<MyData> cursorToData(Cursor cursor) {
        ArrayList<MyData> mydataAL = new ArrayList<MyData>();
        MyData mydata = new MyData();

        mydata.setId(cursor.getLong(0));
        mydata.setLightSensor(cursor.getFloat(1));
        mydata.setPressureSensor(cursor.getFloat(2));
        mydata.setTempSensor(cursor.getFloat(3));
        mydata.setDate(cursor.getString(4));
        mydataAL.add(mydata);

        return mydataAL;
    }*/

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
