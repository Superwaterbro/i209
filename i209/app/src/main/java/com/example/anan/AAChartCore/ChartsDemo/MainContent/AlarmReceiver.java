package com.example.anan.AAChartCore.ChartsDemo.MainContent;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AlarmReceiver extends BroadcastReceiver {
    private static final String TAG="AlarmReceiver";



    @Override

    public void onReceive(Context context, Intent intent) {

            if("com.example.AAChartCore-master.Ring".equals(intent.getAction())){

                Log.i("text1","收到广播了");
                Intent intent1=new Intent(context,RingActivity.class);

                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent1);
            }

        /*String time =intent.getStringExtra("time");
        DataBaseOperator operator=new DataBaseOperator(context);

        if(time!=null){

            Log.d(TAG,"将要删除时间为"+time);

            if(operator.deleteAlarm(time)>0){

                LampSharePreference pre = LampSharePreference.getInstance(context);

                int nums=pre.getInt(LampSharePreference.ALARM_NUMBERS,0);

                nums--;

                Log.d(TAG,"现在闹钟数量为"+nums);

                pre.setInt(LampSharePreference.ALARM_NUMBERS,nums);

            }*/


    }
}
