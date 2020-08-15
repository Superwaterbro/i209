package com.example.anan.AAChartCore.ChartsDemo.MainContent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;



import java.util.List;

public class RecordAdapter2 extends ArrayAdapter<BeanRecord2> {
    private int resourceid;
    public RecordAdapter2(Context context, int id, List<BeanRecord2> objects){
        super(context,id,objects);
        resourceid = id;
    }
    public View getView(int position, View convertView, ViewGroup parent){
        BeanRecord2 data=getItem(position);
        View view;
        if(convertView==null){
            view= LayoutInflater.from(getContext()).inflate(resourceid,null);
        }
        else{
            view=convertView;
        }

        TextView xuetang=(TextView)view.findViewById(R.id.xuetang);
        TextView time=(TextView)view.findViewById(R.id.time);

        xuetang.setText(data.getXuetang());
        time.setText(data.getTime());

        return view;
    }
}