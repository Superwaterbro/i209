package com.example.anan.AAChartCore.ChartsDemo.MainContent;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.anan.AAChartCore.ChartsDemo.AdditionalContent.CandyChartActivity;

public class CallDelete extends AppCompatActivity {
    ListView listView;
    private SimpleCursorAdapter Adapter;
    MyListViewAdapter adapter;
    private Cursor mCursor;
    @Override

    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_calldelete);

        Button button = (Button) findViewById(R.id.calcel_delete);

        button.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {

                finish();

            }

        });

        listView = (ListView)findViewById(R.id.delete_call_list);

        initData();



    }

    private void initData(){

        MyDatabaseHelper5 helper = new MyDatabaseHelper5(CallDelete.this);


        SQLiteDatabase db = helper.getWritableDatabase();//数据库对象

        mCursor=db.query("record", null, null, null, null, null, null);//获得alarm的ta
        Cursor cursor = db.query("record", null, null, null, null, null, null);

        //调用moveToFirst()将数据指针移动到第一行的位置。
        if (cursor.moveToFirst()) {


            String [] colums = {"name","call"};

            int[] layoutsId = {R.id.callname, R.id.callcall};

            adapter =new MyListViewAdapter(this, R.layout.activity_calldetete_item,mCursor,colums,layoutsId, CursorAdapter.FLAG_AUTO_REQUERY);

            listView.setAdapter(adapter);

        } while (cursor.moveToNext());

        cursor.close();



    }

    public class MyListViewAdapter extends SimpleCursorAdapter {

        public MyListViewAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {

            super(context, layout, c, from, to, flags);

        }



        @Override

        public void bindView(View view, final Context context, final Cursor cursor) {

            super.bindView(view, context, cursor);



            Button imageButton = (Button)view.findViewById(R.id.alarm_delete_button1);

            imageButton.setOnClickListener(new View.OnClickListener() {
                final int id = cursor.getInt(0);
                @Override

                public void onClick(View view) {
                    MyDatabaseHelper5 helper = new MyDatabaseHelper5(CallDelete.this);
                    SQLiteDatabase db = helper.getWritableDatabase();


                    String[] args = new String[]{String.valueOf( cursor.getInt(0))};

                    LampSharePreference pre = LampSharePreference.getInstance(context);
                    int nums= pre.getInt(LampSharePreference.ALARM_NUMBERS,0);


                    nums--;
                    pre.setInt(LampSharePreference.ALARM_NUMBERS,0);

                    db.delete("record", "_id=?", args);



                    cursor.requery();

                    adapter.notifyDataSetChanged();




                }

            });

        }

    }
}
