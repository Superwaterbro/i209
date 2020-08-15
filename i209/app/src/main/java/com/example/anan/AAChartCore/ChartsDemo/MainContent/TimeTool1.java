package com.example.anan.AAChartCore.ChartsDemo.MainContent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeTool1 {
    public static String getTime() {

        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");

        Date now = new Date();

        return format.format(now);

    }





    public static Date turnStringToDate(String sTime){

        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");

        Date now = null;

        try {

            now =format.parse(sTime);

        } catch (ParseException e) {

            e.printStackTrace();

        }

        return now;

    }



    public static String turnDateToString(Date date){

        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");

        String time;

        time =format.format(date);

        return time;



    }



    public static String turnDateToStringonlyDate(Date date){

        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");

        String time;

        time =format.format(date);

        return time;

    }



    //计算两个时刻之间的时间差
    public static long getDiffTime(Date offTime_date, Date nowTime_date) {

        long offTime=offTime_date.getTime();

        long nowTime=nowTime_date.getTime();

        long diffTime=nowTime-offTime;

        return diffTime;



    }





    public static String turnMiuTimeToString(long time){

        long hour=time/(1000*60*60);

        long min = (time-hour*(1000*60*60))/(1000*60);

        return hour+":"+min;

    }



    public static long turnTimeToMin(long time){

        return time/(1000*60);

    }
}
