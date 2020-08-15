package com.example.anan.AAChartCore.ChartsDemo.MainContent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DataBaseOperator1 {

    MyDataBaseHelper6 helper6;

    SQLiteDatabase dbWriter;

    public DataBaseOperator1(Context context) {

        helper6=MyDataBaseHelper6.getInstance(context);

        dbWriter=helper6.getWritableDatabase();
    }



    /* 向数据库里插入数据*/

    public void intsert(String tbName, ContentValues values){

        dbWriter.insert(tbName,null,values);

    }



    /*get table*/

    public Cursor query(String tb){

        return dbWriter.query(tb,null,null,null,null,null,null);

    }


    public int update(String table, ContentValues values, String whereClause, String[] whereArgs){

        return dbWriter.update(table,values,whereClause,whereArgs);

    }


    /*通过时间来获得存在数据库的闹钟id*/

    public int getBirthId(String time){

        String QUIRY_BIRTH_ID="SELECT _id,"+ MyDataBaseHelper6.COL_TIME

                +" FROM "+MyDataBaseHelper6.BIRTH_TB_NAME+" WHERE "

                +MyDataBaseHelper6.COL_TIME+"= '"+time+"'";

        Cursor cursor = dbWriter.rawQuery(QUIRY_BIRTH_ID,null);

        int id =-1;

        while(cursor.moveToNext()){

            id=cursor.getInt(0);

        }

        return id;

    }



    public int deleteBirth(String time) {



        String[] args = new String[]{time};

        return dbWriter.delete(MyDataBaseHelper6.BIRTH_TB_NAME,"birth_time=?",args);

    }



    public Birth queryBirthWithId(int id){

        Birth birth=new Birth();

        String QUERY_BIRTHINFO="SELECT * FROM "

                +MyDataBaseHelper6.BIRTH_TB_NAME+" WHERE "

                +"_id='"+String.valueOf(id)+"'";

        Cursor cursor = dbWriter.rawQuery(QUERY_BIRTHINFO,null);

        while (cursor.moveToNext()){

            birth.setBirthStatus(cursor.getString(cursor.getColumnIndex(MyDataBaseHelper6.COL_BIRTH_STATUS)));

             birth.setTime(cursor.getString(cursor.getColumnIndex(MyDataBaseHelper6.COL_TIME)));

            birth.setBirthLable(cursor.getString(cursor.getColumnIndex(MyDataBaseHelper6.COL_BIRTH_LABLE)));

            return birth;

        }

        return birth;

    }





    public  void delete(int id){

        String[] args = new String[]{String.valueOf(id)};

        dbWriter.delete(MyDataBaseHelper6.BIRTH_TB_NAME,"_id=?",args);

    }
}
