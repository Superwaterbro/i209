package com.example.anan.AAChartCore.ChartsDemo.MainContent;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDataBaseHelper6 extends SQLiteOpenHelper {

    public static MyDataBaseHelper6 helper6;

    private static final int DB_VERSION = 2;//数据库版本号

    public static final String COL_ID="_id";

    public static final String DB_NAME ="birth.db";//数据库名称


    public static final String BIRTH_TB_NAME="birth_tb";

    public static final String COL_TIME="birth_time";

    public static final String COL_BIRTH_STATUS="birth_status";


    public static final String COL_BIRTH_LABLE="birth_lableText";





    public static final String LAMP_TB_NAME="lamp_tb";

    public static final String COL_LAMP_DATA_TIME="data_time";

    public static final String COL_LAMP_DATA_TEMPERATUE="data_temperature";

    public static final String COL_LAMP_DATA_HUMIDITY="data_humidity";

    public static final String COL_LAMP_DATA_NOISE="data_noise";




    private static final String CREATE_BIRTH_TABLE="CREATE TABLE "+BIRTH_TB_NAME

            +"(_id INTEGER PRIMARY KEY AUTOINCREMENT,"

            +COL_BIRTH_STATUS +" TEXT NOT NULL,"

            +COL_BIRTH_LABLE+" TEXT NOT NULL,"

            +COL_TIME+" TEXT NOT NULL);";



    private static final String CREATE_LAMP_TABLE="CREATE TABLE "+LAMP_TB_NAME

            +"("+COL_ID+" INTEGER PRIMARY KEY,"

            +COL_LAMP_DATA_TIME+" TEXT NOT NULL,"

            +COL_LAMP_DATA_TEMPERATUE+" REAL,"

            +COL_LAMP_DATA_NOISE+" INTEGER,"

            +COL_LAMP_DATA_HUMIDITY+" REAL);";

    public MyDataBaseHelper6(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        //通过super调用父类当中的构造函数
        super(context, name, factory, version);

    }

    //单例模式
    public static synchronized MyDataBaseHelper6 getInstance(Context context) {

        if (helper6 == null) {

            helper6 = new MyDataBaseHelper6(context, DB_NAME, null, DB_VERSION);

        }
        return helper6;

    }

    //向数据添加表
    private void createTable(SQLiteDatabase db)
    {

        db.execSQL(CREATE_BIRTH_TABLE);

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
