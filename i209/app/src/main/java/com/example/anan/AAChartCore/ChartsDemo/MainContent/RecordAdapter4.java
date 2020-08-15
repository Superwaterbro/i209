package com.example.anan.AAChartCore.ChartsDemo.MainContent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;



import java.util.List;

public class RecordAdapter4 extends ArrayAdapter<BeanRecord4> {
    private int resourceid;
    public RecordAdapter4(Context context, int id, List<BeanRecord4> objects){
        super(context,id,objects);
        resourceid = id;
    }
    public View getView(int position, View convertView, ViewGroup parent){
        BeanRecord4 data=getItem(position);
        View view;
        if(convertView==null){
            view= LayoutInflater.from(getContext()).inflate(resourceid,null);
        }
        else{
            view=convertView;
        }
        TextView xueya=(TextView)view.findViewById(R.id.xueya);
        TextView xueya2=(TextView)view.findViewById(R.id.xueya2);
        TextView time=(TextView)view.findViewById(R.id.time);
        xueya.setText(data.getXueya());
        xueya2.setText(data.getXueya2());
        time.setText(data.getTime());
        return view;
    }
}
