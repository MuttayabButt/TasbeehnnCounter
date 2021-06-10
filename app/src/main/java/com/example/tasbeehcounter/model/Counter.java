package com.example.tasbeehcounter.model;



public class Counter  {


    String zikr;
    int counts;

    public Counter(String zikr, int counts) {
        this.zikr = zikr;
        this.counts = counts;
    }

    public String getZikr() {
        return zikr;
    }

    public void setZikr(String zikr) {
        this.zikr = zikr;
    }

    public int getCounts() {
        return counts;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }


}
