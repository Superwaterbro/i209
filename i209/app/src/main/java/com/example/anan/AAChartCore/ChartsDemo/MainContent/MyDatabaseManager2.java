package com.example.anan.AAChartCore.ChartsDemo.MainContent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class MyDatabaseManager2 {
    private MyDatabaseHelper2 helper;
    private SQLiteDatabase db;

    public MyDatabaseManager2(Context context) {
        helper = new MyDatabaseHelper2(context);
        db = helper.getWritableDatabase();
    }

    public void addData(BeanRecord2 data) {
        addContent(data);
    }

    private void addContent(BeanRecord2 data) {
        ContentValues values = new ContentValues();

        values.put("xuetang", data.getXuetang());
        values.put("time", data.getTime());

        db.insert("record", null, values);
    }

    public ArrayList<BeanRecord2> queryALLContent() {
        ArrayList<BeanRecord2> datas = new ArrayList<>();
        Cursor c = db.query("record", null, null, null, null, null, null);
        while (c.moveToNext()) {
            BeanRecord2 data = null;


            String xuetang = c.getString(c.getColumnIndex("xuetang"));
            String time = c.getString(c.getColumnIndex("time"));

            data = new BeanRecord2(xuetang, time);
            datas.add(data);
        }
        c.close();
        return datas;
    }
}