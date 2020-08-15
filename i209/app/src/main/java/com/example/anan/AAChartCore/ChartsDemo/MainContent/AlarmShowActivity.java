package com.example.anan.AAChartCore.ChartsDemo.MainContent;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AlarmShowActivity extends AppCompatActivity implements  View.OnClickListener, AdapterView.OnItemClickListener,

        AdapterView.OnItemLongClickListener {

    private ListView listView;//alarm show list

    private ArrayList<String> sList=new ArrayList<>();

    private ImageButton iButton;//add clock button

    private SimpleCursorAdapter cursorAdapter;

    private DataBaseOperator dbOpeater;

    private SQLiteDatabase wb;

    private Cursor mCursor;//数据库指针

    private final String TAG="AlarmShowActivity";



    @Override

    protected void onCreate( Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_show);

        listView=(ListView)findViewById(R.id.listView);

        listView.setOnItemClickListener(this);

        listView.setOnItemLongClickListener(this);

        iButton=(ImageButton)findViewById(R.id.add_button);

        iButton.setOnClickListener(this);

        dbOpeater = new DataBaseOperator(this);//数据库对象



    }

    /*@RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)*/
    @Override

    protected void onResume() {

        super.onResume();

        mCursor=dbOpeater.query(MyDataBaseHelper1.ALARM_TB_NAME);//获得alarm的table

        String [] colums = {MyDataBaseHelper1.COL_TIME,MyDataBaseHelper1.COL_ALARM_STATUS,MyDataBaseHelper1.COL_ALARM_REPEAT_TIMES,MyDataBaseHelper1.COL_ALARM_LABLE};

        int[] layoutsId = {R.id.alarm_time,R.id.alarm_status,R.id.alarm_repeat_times,R.id.alarm_lable};

        cursorAdapter=new SimpleCursorAdapter(this,R.layout.activity_item,mCursor,colums,layoutsId, CursorAdapter.FLAG_AUTO_REQUERY);

        listView.setAdapter(cursorAdapter);

    }



    @Override

    protected void onStop() {

        mCursor.close();

        super.onStop();

    }

    @Override

    public void onClick(View v) {

        switch (v.getId()){

            case R.id.add_button:

                Intent intent =new Intent(this,AlarmEditActivity.class);

                startActivity(intent);

                //cursorAdapter.notifyDataSetChanged();

        }

    }

    @Override

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent=new Intent(this,AlarmEditActivity.class);

        TextView modify_time= (TextView) view.findViewById(R.id.alarm_time);

        intent.putExtra("time",modify_time.getText());

        intent.putExtra("position",position+1);

        Log.d(TAG,"要修改时间为"+modify_time.getText());

        startActivity(intent);

    }



    @Override

    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(this,DeleteAlarmActivity.class);

        startActivity(intent);

        return false;

    }
}
