package com.example.anan.AAChartCore.ChartsDemo.MainContent;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class CallshowActivity extends AppCompatActivity implements  View.OnClickListener, AdapterView.OnItemClickListener,

       AdapterView.OnItemLongClickListener{

    private ListView listView;//alarm show list

    private ArrayList<String> sList=new ArrayList<>();

    private ImageButton iButton;//add clock button

    private SimpleCursorAdapter cursorAdapter;

    private DataBaseOperator dbOpeater;

    private SQLiteDatabase wb;

    private Cursor mCursor;//数据库指针

    private final String TAG="CallShowActivity";



    @Override

    protected void onCreate( Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_callshow);

        listView=(ListView)findViewById(R.id.listView);
        listView.setOnItemLongClickListener(this);

        listView.setOnItemClickListener(this);

        Button button7;
        button7 = (Button) findViewById(R.id.add_button);
        button7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setClass(com.example.anan.AAChartCore.ChartsDemo.MainContent.CallshowActivity.this, CalleditActivity.class);//this前面为当前activty名称，class前面为要跳转到得activity名称
                startActivity(intent);
            }
        });






    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    @Override

    protected void onResume() {
        MyDatabaseHelper5 helper = new MyDatabaseHelper5(CallshowActivity.this);


        SQLiteDatabase db = helper.getWritableDatabase();//数据库对象

        super.onResume();
        mCursor=db.query("record", null, null, null, null, null, null);//获得alarm的table
       Cursor cursor = db.query("record", null, null, null, null, null, null);
        //调用moveToFirst()将数据指针移动到第一行的位置。

        if (cursor.moveToFirst()) {

                String [] colums = {"name","call"};

                int[] layoutsId = {R.id.name, R.id.call};

                cursorAdapter=new SimpleCursorAdapter(this, R.layout.activity_callitem,mCursor,colums,layoutsId, CursorAdapter.FLAG_AUTO_REQUERY);

                listView.setAdapter(cursorAdapter);

               } while (cursor.moveToNext());

        cursor.close();






                }

          protected void onStop() {

              mCursor.close();

              super.onStop();

          }



    public void onClick(View v) {

        switch (v.getId()){

            case R.id.add_button:

                Intent intent =new Intent(this, CalleditActivity.class);

                startActivity(intent);

                //cursorAdapter.notifyDataSetChanged();

        }

    }


    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        MyDatabaseHelper5 helper = new MyDatabaseHelper5(CallshowActivity.this);
        String baba[]=new String[100];
        String[] u=new String[10];
        int i = 0;
        SQLiteDatabase db = helper.getWritableDatabase();//数据库对象
        Cursor cursor = db.query("record", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                //然后通过Cursor的getColumnIndex()获取某一列中所对应的位置的索引

                String tel = cursor.getString(cursor.getColumnIndex("call"));



                baba[i]=tel;

                u[i]=tel;
                i++;
            } while (cursor.moveToNext());
        }
        cursor.close();



        callPhone(u[position]);
    }
    public void callPhone(String phoneNum){
        if (Build.VERSION.SDK_INT >= 23) {
            int REQUEST_CODE_CONTACT = 101;
            String[] permissions = {Manifest.permission.CALL_PHONE};
            //验证是否许可权限
            for (String str : permissions) {
                if (this.checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED) {
                    //申请权限
                    this.requestPermissions(permissions, REQUEST_CODE_CONTACT);
                    return;
                }
            }
        }
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "已经拨打电话啦", Toast.LENGTH_LONG).show();
    }

    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        MyDatabaseHelper5 helper = new MyDatabaseHelper5(CallshowActivity.this);


        SQLiteDatabase db = helper.getWritableDatabase();//数据库对象
        Cursor cursor = db.query("record", null, null, null, null, null, null);
        Intent intent = new Intent(this,CallDelete.class);

        startActivity(intent);

        return false;

    }
}
