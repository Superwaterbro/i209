package com.example.anan.AAChartCore.ChartsDemo.MainContent;

import java.util.Calendar;

public class Birth {

    private int whichAlarm=1;

    private String command="datetime";

    private String datetime;

    private String birthStatus="ON";

    private String birthLable="生日";

    private String BirthChangeWay="new";//modify和new两种值



    public int getWhichAlarm() {

        return whichAlarm;

    }



    public Birth(Calendar datetime) {

        this.datetime = TimeTool1.turnDateToString(datetime.getTime());

    }



    public Birth(){

        this(Calendar.getInstance());

    }



    public String getBirthChangeWay() {

        return BirthChangeWay;

    }




    public String getTime() {

        return datetime;

    }



    public String getCommand() {

        return command;

    }



    public String getBirthTime() {

        return datetime;

    }

    public String getBirthStatus() {

        return birthStatus;

    }



    public String getBirthLable() {

        return birthLable;

    }


    public void setBirthChangeWay(String BirthChangeWay) {

        this.BirthChangeWay = BirthChangeWay;

    }

    public void setTime(String datetime) {

        this.datetime = datetime;

    }


    public void setCommand(String command) {

        this.command = command;

    }



    public void setWhichAlarm(int whichAlarm) {

        this.whichAlarm = whichAlarm;

    }


    public void setBirthLable(String birthLable) {

        this.birthLable = birthLable;

    }

    public void setBirthStatus(String birthStatus) {

        this.birthStatus = birthStatus;

    }
}
