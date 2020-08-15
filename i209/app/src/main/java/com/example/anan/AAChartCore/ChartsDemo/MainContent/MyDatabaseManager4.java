package com.example.anan.AAChartCore.ChartsDemo.MainContent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class MyDatabaseManager4{
    private MyDatabaseHelper4 helper;
    private SQLiteDatabase db;

    public MyDatabaseManager4(Context context){
        helper=new MyDatabaseHelper4(context);
        db=helper.getWritableDatabase();
    }
    public void addData(BeanRecord4 data)

    {
        addContent(data);
    }
    private  void addContent(BeanRecord4 data){
        ContentValues values=new ContentValues();
        values.put("xueya",data.getXueya());
        values.put("xueya2",data.getXueya2());
        values.put("time",data.getTime());
        db.insert("record",null,values);
    }
    public ArrayList<BeanRecord4> queryALLContent() {
        ArrayList<BeanRecord4> datas = new ArrayList<>();
        Cursor c = db.query("record", null, null, null, null, null, null);
        while (c.moveToNext()) {
            BeanRecord4 data = null;

            String xueya = c.getString(c.getColumnIndex("xueya"));
            String xueya2 = c.getString(c.getColumnIndex("xueya2"));
            String time = c.getString(c.getColumnIndex("time"));

            data = new BeanRecord4(xueya,xueya2,time);
            datas.add(data);
        }
        c.close();
        return datas;
    }
}
