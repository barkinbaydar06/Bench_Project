package com.sportech.bench;

import java.util.ArrayList;
import java.util.UUID;

public class Match {
    public String Address;
    public Time Time;
    public String Title;
    public String MatchID;
    public int RequiredPlayerCount;

    public Match(){
        MatchID = UUID.randomUUID().toString();
    }


    public Match(String address, Time time, String title, int requiredPlayerCount){
        SetAddress(address);
        SetTime(time);
        SetText(title);

        MatchID = UUID.randomUUID().toString();
    }


    public String GetAddress(){
        return Address;
    }
    public Time GetTime(){
        return Time;
    }
    public String GetText(){
        return Title;
    }
    public int GetRequiredPlayerCount(){ return RequiredPlayerCount; }

    public void SetAddress(String s){
        Address = s;
    }
    public void SetTime(Time t){
        Time = t;
    }
    public void SetText(String t){
        Title = t;
    }
    public void SetRequiredPlayerCount(int c){ RequiredPlayerCount = c; }
    public String GetMatchID(){ return MatchID; }

}
