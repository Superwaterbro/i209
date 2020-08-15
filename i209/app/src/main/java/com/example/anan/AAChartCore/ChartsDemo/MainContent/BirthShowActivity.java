package com.example.anan.AAChartCore.ChartsDemo.MainContent;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
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

public class BirthShowActivity extends AppCompatActivity implements  View.OnClickListener, AdapterView.OnItemClickListener,

        AdapterView.OnItemLongClickListener {

    private ListView listView;//alarm show list

    private ArrayList<String> sList=new ArrayList<>();

    private ImageButton iButton;//add clock button

    private SimpleCursorAdapter cursorAdapter;

    private DataBaseOperator1 dbOpeater;

    private SQLiteDatabase wb;

    private Cursor mCursor;//数据库指针

    private final String TAG="BirthShowActivity";



    @Override

    protected void onCreate( Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_birthshow);

        listView=(ListView)findViewById(R.id.listView);

        listView.setOnItemClickListener(this);

        listView.setOnItemLongClickListener(this);

        iButton=(ImageButton)findViewById(R.id.add_button);

        iButton.setOnClickListener(this);

        dbOpeater = new DataBaseOperator1(this);//数据库对象



    }

    /*@RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)*/
    @Override

    protected void onResume() {

        super.onResume();

        mCursor=dbOpeater.query(MyDataBaseHelper6.BIRTH_TB_NAME);//获得alarm的table

        String [] colums = {MyDataBaseHelper6.COL_TIME,MyDataBaseHelper6.COL_BIRTH_STATUS,MyDataBaseHelper6.COL_BIRTH_LABLE};

        int[] layoutsId = {R.id.birth_time,R.id.birth_status,R.id.birth_lable};

        cursorAdapter=new SimpleCursorAdapter(this,R.layout.activity_birth_item,mCursor,colums,layoutsId, CursorAdapter.FLAG_AUTO_REQUERY);

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

                Intent intent =new Intent(this,BirthEditActivity.class);

                startActivity(intent);

                //cursorAdapter.notifyDataSetChanged();

        }

    }

    @Override

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent=new Intent(this,BirthEditActivity.class);

        TextView modify_time= (TextView) view.findViewById(R.id.birth_time);

        intent.putExtra("datetime",modify_time.getText());

        intent.putExtra("position",position+1);

        Log.d(TAG,"要修改时间为"+modify_time.getText());

        startActivity(intent);

    }



    @Override

    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(this,BirthDeleteActivity.class);

        startActivity(intent);

        return false;

    }
}
