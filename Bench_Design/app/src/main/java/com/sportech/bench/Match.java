package com.sportech.bench;

import java.util.ArrayList;
import java.util.UUID;

public class Match {
    public String Address;
    public Time Time;
    public ArrayList<User> Players;
    public String Title;
    private final String MatchID;

    public Match(String address){
        SetAddress(address);

        MatchID = UUID.randomUUID().toString();
    }

    public Match(String address, Time time){
        SetAddress(address);
        SetTime(time);

        MatchID = UUID.randomUUID().toString();
    }

    public Match(String address, Time time, String title){
        SetAddress(address);
        SetTime(time);
        SetText(title);

        MatchID = UUID.randomUUID().toString();
    }

    public void AddPlayer(User p){
        Players.add(p);
    }
    public void RemovePlayer(User p){
        Players.remove(p);
    }
    public ArrayList<User> GetPlayers(){
        return Players;
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

    public void SetAddress(String s){
        Address = s;
    }
    public void SetTime(Time t){
        Time = t;
    }
    public void SetText(String t){
        Title = t;
    }
    public String GetMatchID(){ return MatchID; }

}
