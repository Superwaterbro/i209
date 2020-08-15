package com.example.anan.AAChartCore.ChartsDemo.MainContent;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MyDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="currenciesdb.db";
    private static final int DATABASE_VERSION = 1;

    public MyDatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db){
        db.execSQL
                ("CREATE TABLE IF NOT EXISTS record"+
                        "(id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                        "tiwen TEXT,"+
                        "time TEXT)");
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("drop table if exists record");
    }
}

