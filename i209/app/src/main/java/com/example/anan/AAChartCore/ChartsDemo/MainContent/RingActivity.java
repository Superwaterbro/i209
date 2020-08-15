package com.example.anan.AAChartCore.ChartsDemo.MainContent;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class RingActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ring);
        //播放音乐
        mediaPlayer = MediaPlayer.create(this, R.raw.nq);
        mediaPlayer.start();

        //显示通知栏
        NotificationCompat.Builder notificationCompat=new NotificationCompat.Builder(this);
        //设置参数
        notificationCompat.setDefaults(NotificationCompat.DEFAULT_ALL);
        notificationCompat.setContentTitle("提醒");
        notificationCompat.setContentText("吃药啦");
        notificationCompat.setSmallIcon(android.R.drawable.ic_lock_idle_alarm);
        Notification notification=notificationCompat.build();


        //通知管理器
        NotificationManager notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //发送通知
        notificationManager.notify(0x101,notification);
    }

    public void close(View view){
        mediaPlayer.stop();
        finish();
    }
}
