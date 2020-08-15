package com.example.anan.AAChartCore.ChartsDemo.MainContent;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.SimpleAdapter;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BirthEditActivity extends AppCompatActivity  implements View.OnClickListener, AdapterView.OnItemClickListener {

    private String TAG = "BirthEditActivity";

    private ListView listView;

    private List<Map<String, String>> datalist;

    private Map<String, String> map;

    private SimpleAdapter simpleAdapter;

    private Birth birth;

    DataBaseOperator1 dbOperator;

    Calendar time_calender;

    String modify_time_string;

    DatePicker datePicker;

    int year;

    int month;

    int day;

    LampSharePreference alarmPre;//


    protected void onCreate(Bundle savedInstanceState) {

        Log.d(TAG, "onCreate");

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_birth_edit);

        modify_time_string=getIntent().getStringExtra("datetime");

        alarmPre = LampSharePreference.getInstance(this);

        dbOperator = new DataBaseOperator1(this);

        birth =new Birth();


        if(modify_time_string!=null){

            Log.d(TAG,"modify");

            birth.setBirthChangeWay("modify");

            birth.setTime(modify_time_string);

            birth.setWhichAlarm(getIntent().getIntExtra("position",0));

            Log.d(TAG,"修改位置为"+birth.getWhichAlarm());

        }else{

           birth.setBirthChangeWay("new");

            birth.setWhichAlarm(alarmPre.getInt(LampSharePreference.ALARM_NUMBERS,0)+1);

            Log.d(TAG,"new");

        }

        datalist = new ArrayList<>();

        ImageButton saveBirth = (ImageButton) findViewById(R.id.save_birth);

        saveBirth.setOnClickListener(this);

        ImageButton cacelEditBirth = (ImageButton) findViewById(R.id.cancel_edit_birth);

        cacelEditBirth.setOnClickListener(this);

        Date date = TimeTool1.turnStringToDate(birth.getBirthTime());

        time_calender=Calendar.getInstance();
        Log.d("现在时间为",""+time_calender.getTime());




    }

    protected void onStop() {

        super.onStop();

        Log.d(TAG,"onstop");

    }

    protected void onResume() {

        Log.d(TAG,"onResume");

        super.onResume();

        year=time_calender.get(Calendar.YEAR);

        month=time_calender.get(Calendar.MONTH);

        day=time_calender.get(Calendar.DATE);

        datePicker=findViewById(R.id.datePicker);

        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int year1, int month1, int day1) {
                BirthEditActivity.this.year=year1;
                BirthEditActivity.this.month=month1;
                month1=month1+1;
                BirthEditActivity.this.day=day1;

                if (time_calender.before(Calendar.getInstance())) {

                    time_calender.add(Calendar.DAY_OF_MONTH, 1);

                }

                /*Toast.makeText(BirthEditActivity.this,year1+"-"+month1+"-"+day1,Toast.LENGTH_SHORT).show();*/
            }
        });


        listView = (ListView) findViewById(R.id.birth_edit_list_view);

        setDataList();

        simpleAdapter = new SimpleAdapter(this, datalist, R.layout.activity_edit_item2,

                new String[]{"name", "value"}, new int[]{R.id.name, R.id.value});


        listView.setAdapter(simpleAdapter);

        listView.setOnItemClickListener(this);


    }

    public void setDataList() {

        datalist.clear();
        map = new HashMap<>();

        map.put("name", "目 标");

        map.put("value", birth.getBirthStatus());

        datalist.add(map);

        map = new HashMap<>();

        map.put("name", "标 签");

        map.put("value", birth.getBirthLable());

        datalist.add(map);




    }

    public void onClick(View v) {



        switch (v.getId()) {

            case R.id.save_birth:

                saveBirth();

                finish();

                break;

            case R.id.cancel_edit_birth:

                finish();

                break;

        }

    }

    private void saveBirth() {

        String time_string = TimeTool1.turnDateToString(time_calender.getTime());

        Log.d(TAG,time_string);

        Log.d(TAG,birth.getBirthStatus());

        Log.d(TAG,birth.getBirthLable());


        ContentValues values = new ContentValues();

        values.put(MyDataBaseHelper6.COL_TIME, time_string);

        values.put(MyDataBaseHelper6.COL_BIRTH_STATUS, birth.getBirthStatus());

        values.put(MyDataBaseHelper6.COL_BIRTH_LABLE,birth.getBirthLable());

        /*Intent intent=new Intent(this, AlarmReceiver.class);//启动broadcast的intent

        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);*/

        if(birth.getBirthChangeWay().equals("new")){

            dbOperator.intsert(MyDataBaseHelper6.BIRTH_TB_NAME, values);

            Log.d(TAG,"insert to dataBase");

            Log.d(TAG,"设置生日时间为"+time_calender.getTime());

            /*intent.putExtra("datetime",time_string);*/

            //更新闹钟数量

            alarmPre.setInt(LampSharePreference.ALARM_NUMBERS,birth.getWhichAlarm());

        }else {

            dbOperator.update(MyDataBaseHelper6.BIRTH_TB_NAME,values,"birth_time = ?",new String[]{modify_time_string});

            Log.d(TAG,"update to dataBase");

            Log.d(TAG,"操作时间为"+time_calender.getTime());

            /*intent.putExtra("datetime",time_string);*/

        }

        Log.d(TAG,"这是第几个生日呢,请回答"+birth.getWhichAlarm());

        /*intent.setAction("com.example.AAChartCore-master.Ring");

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,birth.getWhichAlarm(),intent,PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager.set(AlarmManager.RTC_WAKEUP,time_calender.getTimeInMillis(),pendingIntent);*/


        birth.setTime(time_string);

        birth.setCommand("alarm");

    }


    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        switch (position) {

            case 0:

                chooseBirthStatus();
                break;

            case 1:
                ClickLable();
                break;

        }

    }

    private void chooseBirthStatus() {



        String items[] = {"ON","OFF"};



        AlertDialog.Builder builder = new AlertDialog.Builder(this)

                .setTitle("目标")

                .setItems(items, new DialogInterface.OnClickListener() {

                    @Override

                    public void onClick(DialogInterface dialog, int which) {

                        switch (which){

                            case 0:birth.setBirthStatus("ON");break;

                            case 1:birth.setBirthStatus("OFF");break;

                        }

                        setDataList();

                        simpleAdapter.notifyDataSetChanged();

                    }

                }).setPositiveButton("取消", new DialogInterface.OnClickListener() {

                    @Override

                    public void onClick(DialogInterface dialog, int which) {



                    }

                });

        builder.create().show();

    }

    public void ClickLable(){
        AlertDialog.Builder builder = new AlertDialog.Builder(BirthEditActivity.this);
        final AlertDialog dialog = builder.create();
        View dialogView = View.inflate(BirthEditActivity.this, R.layout.activity_lable1, null);
        //设置对话框布局
        dialog.setView(dialogView);
        dialog.show();
        final EditText etContent = (EditText) dialogView.findViewById(R.id.edt_content);
        final String etc = etContent.getText().toString();
        Button btnConfirm = (Button) dialogView.findViewById(R.id.btn_confirm);
        Button btnRefuse = (Button) dialogView.findViewById(R.id.btn_refuse);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (etContent.getText().toString().equals("")) {
                    Toast.makeText(BirthEditActivity.this, "不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{

                    /*Toast.makeText(AlarmEditActivity.this, ""+etContent.getText().toString(), Toast.LENGTH_SHORT).show();*/
                    birth.setBirthLable(etContent.getText().toString());
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

}
