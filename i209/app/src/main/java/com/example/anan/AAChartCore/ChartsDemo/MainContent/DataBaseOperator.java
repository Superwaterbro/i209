package com.example.anan.AAChartCore.ChartsDemo.MainContent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DataBaseOperator {

    MyDataBaseHelper1 helper;

    SQLiteDatabase dbWriter;

    public DataBaseOperator(Context context) {

        helper=MyDataBaseHelper1.getInstance(context);

        dbWriter=helper.getWritableDatabase();
    }

   /* *//*获得数据库里所有账号，方便登陆自动填写*//*

    public String[] getAllAccounts(){

        ArrayList<String> accounts=new ArrayList<>();



        String GETALLACCOUNTS="SELECT "+MyDataBaseHelper1.COl_ACCOUNTS

                +" FROM "+MyDataBaseHelper1.ACCOUNT_TB_NAME;

        Cursor cursor = dbWriter.rawQuery(GETALLACCOUNTS,null);

        while (cursor.moveToNext()){

            accounts.add(cursor.getString(cursor.getColumnIndex(MyDataBaseHelper1.COl_ACCOUNTS)));

        }

        return accounts.toArray(new String[0]);

    }*/



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

    /*判断数据里是否存在账号account*/

   /* public boolean isAccountExist(String account){

        String QUREY_ACCOUNT="SELECT "+MyDataBaseHelper1.COl_ACCOUNTS

                +" FROM "+MyDataBaseHelper1.ACCOUNT_TB_NAME

                +" WHERE "+MyDataBaseHelper1.COl_ACCOUNTS

                +" ='"+account+"'";

        Cursor cursor =dbWriter.rawQuery(QUREY_ACCOUNT,null);

        if(cursor.moveToNext()){

            return false;

        }

        return  true;

    }*/

    /*通过时间来获得存在数据库的闹钟id*/

    public int getAlarmId(String time){

        String QUIRY_ALARM_ID="SELECT _id,"+ MyDataBaseHelper1.COL_TIME

                +" FROM "+MyDataBaseHelper1.ALARM_TB_NAME+" WHERE "

                +MyDataBaseHelper1.COL_TIME+"= '"+time+"'";

        Cursor cursor = dbWriter.rawQuery(QUIRY_ALARM_ID,null);

        int id =-1;

        while(cursor.moveToNext()){

            id=cursor.getInt(0);

        }

        return id;

    }



    public int deleteAlarm(String time) {



        String[] args = new String[]{time};

        return dbWriter.delete(MyDataBaseHelper1.ALARM_TB_NAME,"alarm_time=?",args);

    }



    public Alarm queryAlarmWithId(int id){

        Alarm alarm=new Alarm();

        String QUERY_ALARMINFO="SELECT * FROM "

                +MyDataBaseHelper1.ALARM_TB_NAME+" WHERE "

                +"_id='"+String.valueOf(id)+"'";

        Cursor cursor = dbWriter.rawQuery(QUERY_ALARMINFO,null);

        while (cursor.moveToNext()){



            alarm.setRepeatTimes(cursor.getString(cursor.getColumnIndex(MyDataBaseHelper1.COL_ALARM_REPEAT_TIMES)));

            alarm.setAlarmStatus(cursor.getString(cursor.getColumnIndex(MyDataBaseHelper1.COL_ALARM_STATUS)));

            alarm.setTime(cursor.getString(cursor.getColumnIndex(MyDataBaseHelper1.COL_TIME)));

            alarm.setAlarmLable(cursor.getString(cursor.getColumnIndex(MyDataBaseHelper1.COL_ALARM_LABLE)));

            return alarm;

        }

        return alarm;

    }





    public  void delete(int id){

        String[] args = new String[]{String.valueOf(id)};

        dbWriter.delete(MyDataBaseHelper1.ALARM_TB_NAME,"_id=?",args);

    }
}
