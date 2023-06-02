package com.sportech.bench;


public class Player {
    String Address;
    Time EarliestTime;
    Time LatestTime;

    public Player(String address, Time earliestTime, Time latestTime){
        SetAddress(address);
        SetTime(earliestTime, latestTime);
    }
    public String GetAddress(){
        return Address;
    }
    public Time GetEarliestTime(){
        return EarliestTime;
    }
    public Time GetLatestTime(){
        return LatestTime;
    }

    public void SetAddress(String s){
        Address = s;
    }
    public void SetEarliestTime(Time time){
        EarliestTime = time;
    }
    public void SetLatestTime(Time time){
        LatestTime = time;
    }
    public void SetTime(Time earliest, Time latest){
        SetEarliestTime(earliest);
        SetLatestTime(latest);
    }
}
