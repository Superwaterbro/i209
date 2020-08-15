package com.example.anan.AAChartCore.ChartsDemo.MainContent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class MyDatabaseManager {
    private MyDatabaseHelper helper;
    private SQLiteDatabase db;

    public MyDatabaseManager(Context context) {
        helper = new MyDatabaseHelper(context);
        db = helper.getWritableDatabase();
    }

    public void addData(BeanRecord data) {
        addContent(data);
    }

    private void addContent(BeanRecord data) {
        ContentValues values = new ContentValues();

        values.put("tiwen", data.getTiwen());
        values.put("time", data.getTime());

        db.insert("record", null, values);
    }

    public ArrayList<BeanRecord> queryALLContent() {
        ArrayList<BeanRecord> datas = new ArrayList<>();
        Cursor c = db.query("record", null, null, null, null, null, null);
        while (c.moveToNext()) {
            BeanRecord data = null;


            String tiwen = c.getString(c.getColumnIndex("tiwen"));
            String time = c.getString(c.getColumnIndex("time"));

            data = new BeanRecord(tiwen, time);
            datas.add(data);
        }
        c.close();
        return datas;
    }
}