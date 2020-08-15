package com.example.anan.AAChartCore.ChartsDemo.MainContent;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.CursorAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class BirthDeleteActivity extends AppCompatActivity {

    ListView listView;

    BirthDeleteActivity.MyListViewAdapter adapter;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_delete_birth);

        ImageButton button = (ImageButton) findViewById(R.id.calcel_delete);

        button.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {

                finish();

            }

        });

        listView = (ListView)findViewById(R.id.delete_birth_list);

        initData();



    }

    private void initData(){

        MyDataBaseHelper6 helper6 = MyDataBaseHelper6.getInstance(this);

        SQLiteDatabase dWriter = helper6.getWritableDatabase();

        Cursor cursor = dWriter.query(MyDataBaseHelper6.BIRTH_TB_NAME,null,null,null,null,null,null);

        String[] birthColums = new String[]{MyDataBaseHelper6.COL_TIME,MyDataBaseHelper6.COL_BIRTH_LABLE};

        int[] layoutId = new int[]{R.id.birth_delete_time,R.id.birth_lable_delete};

        adapter = new BirthDeleteActivity.MyListViewAdapter(this,R.layout.activity_birth_deleteitem,cursor,birthColums,layoutId, CursorAdapter.FLAG_AUTO_REQUERY);

        listView.setAdapter(adapter);



    }

    public class MyListViewAdapter extends SimpleCursorAdapter {

        public MyListViewAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {

            super(context, layout, c, from, to, flags);

        }



        @Override

        public void bindView(View view, final Context context, final Cursor cursor) {

            super.bindView(view, context, cursor);

            final int id = cursor.getInt(0);

            ImageButton imageButton = (ImageButton)view.findViewById(R.id.birth_delete_ib);

            imageButton.setOnClickListener(new View.OnClickListener() {

                @Override

                public void onClick(View view) {

                    DataBaseOperator1 operator = new DataBaseOperator1(context);

                    LampSharePreference pre = LampSharePreference.getInstance(context);

                    int nums= pre.getInt(LampSharePreference.ALARM_NUMBERS,0);

                    nums--;

                    pre.setInt(LampSharePreference.ALARM_NUMBERS,0);

                    operator.delete(id);

                    cursor.requery();

                    adapter.notifyDataSetChanged();



                }

            });

        }

    }
}
