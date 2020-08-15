package com.example.anan.AAChartCore.ChartsDemo.MainContent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;



import java.util.List;

public class RecordAdapter extends ArrayAdapter<BeanRecord> {
    private int resourceid;
    public RecordAdapter(Context context, int id, List<BeanRecord> objects){
        super(context,id,objects);
        resourceid = id;
    }
    public View getView(int position, View convertView, ViewGroup parent){
        BeanRecord data=getItem(position);
        View view;
        if(convertView==null){
            view= LayoutInflater.from(getContext()).inflate(resourceid,null);
        }
        else{
            view=convertView;
        }

        TextView tiwen=(TextView)view.findViewById(R.id.tiwen);
        TextView time=(TextView)view.findViewById(R.id.time);

        tiwen.setText(data.getTiwen());
        time.setText(data.getTime());

        return view;
    }
}