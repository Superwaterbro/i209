package com.example.anan.AAChartCore.ChartsDemo.MainContent;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDataBaseHelper1 extends SQLiteOpenHelper {

    /*在SQLiteOpenHelper的子类当中，必须有该构造函数
      context   上下文对象
      name      数据库名称
      factory
      version   当前数据库的版本，值必须是整数并且是递增的状态 */


    public static MyDataBaseHelper1 helper;

    private static final int DB_VERSION = 2;//数据库版本号

    public static final String COL_ID="_id";

    public static final String DB_NAME ="wrist.db";//数据库名称

   /* public static final String ACCOUNT_TB_NAME ="account_tb";

    public static final String COl_ACCOUNTS="account";

    public static final String COL_PASSWORD="password";*/



    public static final String ALARM_TB_NAME="alarm_tb";

    public static final String COL_TIME="alarm_time";

    public static final String COL_ALARM_STATUS="alarm_status";

    public static final String COL_ALARM_REPEAT_TIMES="alarm_times";

    public static final String COL_ALARM_LABLE="alarm_lableText";





    public static final String LAMP_TB_NAME="lamp_tb";

    public static final String COL_LAMP_DATA_TIME="data_time";

    public static final String COL_LAMP_DATA_TEMPERATUE="data_temperature";

    public static final String COL_LAMP_DATA_HUMIDITY="data_humidity";

    public static final String COL_LAMP_DATA_NOISE="data_noise";




    //数据库SQL语句添加一个表
    /*private static final String CREATE_ACCOUNT_TABLE="CREATE TABLE "+ACCOUNT_TB_NAME

            +" (_id INTEGER PRIMARY KEY AUTOINCREMENT, "

            +COl_ACCOUNTS+" TEXT NOT NULL,"

            +COL_PASSWORD+" TEXT NOT NULL);";*/



    private static final String CREATE_ALARM_TABLE="CREATE TABLE "+ALARM_TB_NAME

            +"(_id INTEGER PRIMARY KEY AUTOINCREMENT,"

            +COL_ALARM_STATUS +" TEXT NOT NULL,"

            +COL_ALARM_REPEAT_TIMES+" TEXT NOT NULL,"

            +COL_ALARM_LABLE+" TEXT NOT NULL,"

            +COL_TIME+" TEXT NOT NULL);";



    private static final String CREATE_LAMP_TABLE="CREATE TABLE "+LAMP_TB_NAME

            +"("+COL_ID+" INTEGER PRIMARY KEY,"

            +COL_LAMP_DATA_TIME+" TEXT NOT NULL,"

            +COL_LAMP_DATA_TEMPERATUE+" REAL,"

            +COL_LAMP_DATA_NOISE+" INTEGER,"

            +COL_LAMP_DATA_HUMIDITY+" REAL);";

    public MyDataBaseHelper1(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        //通过super调用父类当中的构造函数
        super(context, name, factory, version);

    }

    //单例模式
    public static synchronized MyDataBaseHelper1 getInstance(Context context) {

        if (helper == null) {

            helper = new MyDataBaseHelper1(context, DB_NAME, null, DB_VERSION);

        }
        return helper;

    }

    //向数据添加表
    private void createTable(SQLiteDatabase db)
    {
        /*db.execSQL(CREATE_ACCOUNT_TABLE);*/

        db.execSQL(CREATE_ALARM_TABLE);

        db.execSQL(CREATE_LAMP_TABLE);

    }
    @Override

    public void onCreate(SQLiteDatabase db) {
        createTable(db);
    }

    @Override

    //拿当前数据库的版本信息与之前数据库的版本信息，用来更新数据库
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
