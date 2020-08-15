package com.example.anan.AAChartCore.ChartsDemo.MainContent;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class RecordAdapter5 extends ArrayAdapter<BeanRecord5> {
    private int resourceid;
    public RecordAdapter5(Context context, int id, List<BeanRecord5> objects){
        super(context,id,objects);
        resourceid = id;
    }
    public View getView(int position, View convertView, ViewGroup parent){
        BeanRecord5 data=getItem(position);
        View view;
        if(convertView==null){
            view= LayoutInflater.from(getContext()).inflate(resourceid,null);
        }
        else{
            view=convertView;
        }
        TextView name=(TextView)view.findViewById(R.id.name);
        TextView call=(TextView)view.findViewById(R.id.call);

        name.setText(data.getName());
        call.setText(data.getCall());

        return view;



    }
}