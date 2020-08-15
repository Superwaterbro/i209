package com.example.anan.AAChartCore.ChartsDemo.MainContent;


import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;




import java.util.ArrayList;
import java.util.List;

public class RecordActivity extends AppCompatActivity {
    private MyDatabaseManager dbManager;
    private ListView listView;
    private List<BeanRecord> datas=new ArrayList<>();
    EditText edtSearch;
    ImageView ivDeleteText;
    private RecordAdapter adapter;
    ArrayList<String> tiwen=new ArrayList<String>();
    ArrayList<String> time=new ArrayList<String>();
    Handler myhandler=new Handler();

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        dbManager=new MyDatabaseManager(getBaseContext());
        setContentView(R.layout.activity_record);
        initDatas();
        set_edtSearch_TextChanged();
        set_ivDeleteText_OnClick();
        adapter=new RecordAdapter(this,R.layout.record_list,datas);
        adapter.notifyDataSetChanged();
        listView=(ListView)findViewById(R.id.listview);
        listView.setAdapter(adapter);
        listView.setTextFilterEnabled(true);

    }
    private void set_edtSearch_TextChanged(){
        edtSearch=(EditText)findViewById(R.id.etSearch);
        edtSearch.addTextChangedListener(new TextWatcher(){
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3){

            }
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3){}

            public  void afterTextChanged(Editable s){
                if(s.length()==0){
                    ivDeleteText.setVisibility(View.GONE);
                }
                else{
                    ivDeleteText.setVisibility(View.VISIBLE);
                }
                myhandler.post(eChanged);
            }
        });


    }
    Runnable eChanged=new Runnable() {
        @Override
        public void run() {
            String data=edtSearch.getText().toString();
            datas.clear();
            getmDataSub(datas,data);
            adapter.notifyDataSetChanged();
        }
    };

    private void getmDataSub(List<BeanRecord> datas, String data)
    {
        int length=tiwen.size();
        for (int i=0;i<length;++i){
            if(time.get(i).contains(data)||tiwen.get(i).contains(data))
            { BeanRecord item=new BeanRecord();
                item.setTime(time.get(i));
                item.setTiwen(tiwen.get(i));

                datas.add(item);
            }
        }
    }

    private void set_ivDeleteText_OnClick(){
        ivDeleteText=(ImageView)findViewById(R.id.ivDeleteText);
        ivDeleteText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtSearch.setText("");
            }
        });


    }
    private void initDatas(){
        datas=dbManager.queryALLContent();
        for(BeanRecord d:datas){
            if(d!=null){
                time.add(d.getTime());

                tiwen.add(d.getTiwen());

            }

        }
    }
}