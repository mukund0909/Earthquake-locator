package com.example.apifirstapp;

public class earthquakedata {
    private double mag;
    private String date;
    private String place;
    private String time;
    private String url;
    public earthquakedata(double f,String s,String n,String t,String u)
    {
        mag=f;
        date=n;
        place=s;
        time=t;
        url=u;
    }
    public String getplace() {
        return place;
    }
    public String getdate() {
        return date;
    }
    public double getmag() {
        return mag;
    }
    public String gettime() {
        return time;
    }
    public String geturl() {
        return url;
    }
}
