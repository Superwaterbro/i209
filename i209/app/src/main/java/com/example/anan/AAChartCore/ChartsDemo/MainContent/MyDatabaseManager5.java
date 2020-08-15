package com.example.anan.AAChartCore.ChartsDemo.MainContent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class MyDatabaseManager5{
    private MyDatabaseHelper5 helper;
    private SQLiteDatabase db;

    public MyDatabaseManager5(Context context){
        helper=new MyDatabaseHelper5(context);
        db=helper.getWritableDatabase();
    }
    public void addData(BeanRecord5 data)

    {
        addContent(data);
    }
    private  void addContent(BeanRecord5 data){
        ContentValues values=new ContentValues();
        values.put("name",data.getName());
        values.put("call",data.getCall());

        db.insert("record",null,values);
    }
    public ArrayList<BeanRecord5> queryALLContent() {
        ArrayList<BeanRecord5> datas = new ArrayList<>();
        Cursor c = db.query("record", null, null, null, null, null, null);
        while (c.moveToNext()) {
            BeanRecord5 data = null;

            String name = c.getString(c.getColumnIndex("name"));
            String call = c.getString(c.getColumnIndex("call"));


            data = new BeanRecord5(name,call);
            datas.add(data);
        }
        c.close();
        return datas;
    }
}
