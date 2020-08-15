package com.example.anan.AAChartCore.ChartsDemo.MainContent;

public class Call {
    private int whichAlarm=1;

    private String call="15151515151";

    private String alarmChangeWay="new";//modify和new两种值

    private String name="紧急联系人";



    public int getWhichAlarm() {

        return whichAlarm;

    }



    public void setAlarmChangeWay(String alarmChangeWay) {

        this.alarmChangeWay = alarmChangeWay;

    }









    public void setWhichAlarm(int whichAlarm) {

        this.whichAlarm = whichAlarm;

    }


    public String getAlarmChangeWay() {

        return alarmChangeWay;

    }




    public String getName() {

        return name;

    }



    public String getCall() {

        return call;

    }







    public void setName(String name) {

        this.name = name;

    }


    public void setCall(String call) {

        this.call = call;

    }
}
