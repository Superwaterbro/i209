package com.example.anan.AAChartCore.ChartsDemo.MainContent;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalleditActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private String TAG = "AlarmEditActivity";

    private ListView listView;

    private List<Map<String, String>> datalist;

    private Map<String, String> map;

    private SimpleAdapter simpleAdapter;

    private Call call;


    Calendar time_calender;

    String modify_time_string;

    LampSharePreference alarmPre;//


    protected void onCreate(Bundle savedInstanceState) {

        Log.d(TAG, "onCreate");

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_calledit);


        MyDatabaseHelper5 helper = new MyDatabaseHelper5(CalleditActivity.this);


        SQLiteDatabase db = helper.getWritableDatabase();//数据库对象
        alarmPre = LampSharePreference.getInstance(this);


        call = new Call();


        call.setAlarmChangeWay("new");
        call.setWhichAlarm(alarmPre.getInt(LampSharePreference.ALARM_NUMBERS, 0) + 1);

        datalist = new ArrayList<>();

       Button saveAlarm = (Button) findViewById(R.id.save_alarm);

        saveAlarm.setOnClickListener(this);

       Button cacelEditAlarm = (Button) findViewById(R.id.cancel_edit_alarm);

        cacelEditAlarm.setOnClickListener(this);


    }

    protected void onStop() {

        super.onStop();

        Log.d(TAG, "onstop");

    }


    @Override

    protected void onResume() {

        Log.d(TAG, "onResume");

        super.onResume();


        ;


        listView = (ListView) findViewById(R.id.alarm_edit_list_view);

        setDataList();

        simpleAdapter = new SimpleAdapter(this, datalist, R.layout.activity_calldeit_item,

                new String[]{"name", "value"}, new int[]{R.id.name, R.id.value});


        listView.setAdapter(simpleAdapter);

        listView.setOnItemClickListener(this);

    }

    public void setDataList() {

        datalist.clear();
        map = new HashMap<>();

        map.put("name", "姓名");

        map.put("value", call.getName());

        datalist.add(map);


        map = new HashMap<>();

        map.put("name", "电话");

        map.put("value", call.getCall());

        datalist.add(map);


    }


    @Override

    public void onClick(View v) {


        switch (v.getId()) {

            case R.id.save_alarm:

                saveAlarm();

                finish();

                break;

            case R.id.cancel_edit_alarm:

                finish();

                break;

        }

    }


    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        switch (position) {

            case 0:

                chooseName();

                break;

            case 1:

                choosecall();


        }

    }


    private void chooseName() {

        AlertDialog.Builder builder = new AlertDialog.Builder(CalleditActivity.this);
        final AlertDialog dialog = builder.create();

        //设置对话框布局

        View dialogView = View.inflate(CalleditActivity.this, R.layout.activity_calllable, null);
        dialog.setView(dialogView);
        dialog.show();
        Button btnConfirm = (Button) dialogView.findViewById(R.id.btn_confirm);
        Button btnRefuse = (Button) dialogView.findViewById(R.id.btn_refuse);
        final EditText etContent = (EditText) dialogView.findViewById(R.id.edt_content);

        final String ooc = String.valueOf(etContent.getText().toString());
        etContent.getText();

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (etContent.getText().toString().equals(" ")) {
                    Toast.makeText(CalleditActivity.this, "不能为空", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    Toast.makeText(CalleditActivity.this, "ok", Toast.LENGTH_SHORT).show();


                    call.setName(etContent.getText().toString());
                    setDataList();
                    simpleAdapter.notifyDataSetChanged();

                    dialog.dismiss();
                }


            }


        });
        btnRefuse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }


    public void choosecall() {
        AlertDialog.Builder builder = new AlertDialog.Builder(CalleditActivity.this);
        final AlertDialog dialog = builder.create();

        //设置对话框布局

        View dialogView = View.inflate(CalleditActivity.this, R.layout.activity_calllable, null);
        dialog.setView(dialogView);
        dialog.show();
        Button btnConfirm = (Button) dialogView.findViewById(R.id.btn_confirm);
        Button btnRefuse = (Button) dialogView.findViewById(R.id.btn_refuse);
        final EditText etoContent = (EditText) dialogView.findViewById(R.id.edt_content);

        final String oo = String.valueOf(etoContent.getText().toString());
        etoContent.getText();

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (etoContent.getText().toString().equals("")) {
                    Toast.makeText(CalleditActivity.this, "不能为空", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    Toast.makeText(CalleditActivity.this, "ok", Toast.LENGTH_SHORT).show();


                    call.setCall(etoContent.getText().toString());
                    setDataList();
                    simpleAdapter.notifyDataSetChanged();

                    dialog.dismiss();
                }


            }


        });
        btnRefuse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }


    private void saveAlarm() {





        Log.d(TAG,call.getName());
        Log.d(TAG,call.getCall());

        ContentValues values = new ContentValues();

        BeanRecord5 data = new BeanRecord5();
        data.setName(call.getName());
        data.setCall(call.getCall());

        MyDatabaseManager5 dbManager = new MyDatabaseManager5(getBaseContext());
        dbManager.addData(data);
        Log.d(TAG,"insert to dataBase");

        Log.d(TAG,"设置闹钟时间为"+call.getName());

        //更新闹钟数量

        alarmPre.setInt(LampSharePreference.ALARM_NUMBERS, call.getWhichAlarm());
        Log.d(TAG,"这是第几个闹钟呢,请回答"+call.getWhichAlarm());




        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);


        //更新闹钟数量

        alarmPre.setInt(LampSharePreference.ALARM_NUMBERS, call.getWhichAlarm());

    }
}
