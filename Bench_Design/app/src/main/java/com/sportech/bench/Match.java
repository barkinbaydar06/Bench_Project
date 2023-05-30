package com.sportech.bench;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;

public class Match {
    String Address;
    Calendar Time;
    ArrayList<User> Players;
    String Title;
    private final String MatchID;

    public Match(String adress)
    {
        SetAddress(adress);

        MatchID = UUID.randomUUID().toString();
    }
    public Match(String address, Calendar time){
        SetAddress(address);
        SetTime(time);

        MatchID = UUID.randomUUID().toString();
    }
    public Match(String address, Calendar time, String title){
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
    public Calendar GetTime(){
        return Time;
    }
    public String GetText(){
        return Title;
    }

    public void SetAddress(String s){
        Address = s;
    }
    public void SetTime(Calendar t){
        Time = t;
    }
    public void SetText(String t){
        Title = t;
    }
    public String GetMatchID(){ return MatchID; }

}
