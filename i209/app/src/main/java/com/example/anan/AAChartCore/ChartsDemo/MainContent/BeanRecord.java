package com.example.anan.AAChartCore.ChartsDemo.MainContent;

public class BeanRecord {
    private String tiwen;
    private String time;
    public BeanRecord() {};

    public BeanRecord(String tiwen, String time) {

        this.tiwen = tiwen;
        this.time = time;

    }

    public String getTiwen() {
        return tiwen;
    }


    public String getTime() {
        return time;
    }

    public void setTiwen(String tiwen) {
        this.tiwen =tiwen;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
