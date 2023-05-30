package com.example.b;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;

public class MatchInfo {
    String Address;
    Calendar Time;
    ArrayList<Position> RequiredPositions;
    ArrayList<UserInfo> Players;

    String Title;
    String Subtext;
    String MatchID;

    public MatchInfo(String address, Calendar time, String title, String subtext){
        SetAddress(address);
        SetTime(time);
        SetTitle(title);
        SetSubtext(subtext);

        MatchID = UUID.randomUUID().toString();
    }

    public void AddPlayer(UserInfo p){
        Players.add(p);
    }
    public void RemovePlayer(UserInfo p){
        Players.remove(p);
    }
    public void AddPosition(Position p){
        RequiredPositions.add(p);
    }
    public void RemovePosition(Position p){
        RequiredPositions.remove(p);
    }
    public ArrayList<Position> GetPositions(){
        return RequiredPositions;
    }
    public ArrayList<UserInfo> GetPlayers(){
        return Players;
    }

    public String GetAddress(){
        return Address;
    }
    public Calendar GetTime(){
        return Time;
    }
    public String GetTitle(){
        return Title;
    }
    public String GetSubtext(){
        return Subtext;
    }

    public void SetAddress(String s){
        Address = s;
    }
    public void SetTime(Calendar t){
        Time = t;
    }
    public void SetTitle(String t){
        Title = t;
    }
    public void SetSubtext(String t){
        Subtext = t;
    }
    public String GetMatchID(){ return MatchID; }

}
