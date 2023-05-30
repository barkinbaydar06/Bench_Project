package com.sportech.bench;

import java.util.Calendar;
import java.util.HashSet;

public class Player {
    String Address;
    Calendar EarliestTime;
    Calendar LatestTime;
    HashSet<Position> DesiredPositions;

    public Player(String address, Calendar earliestTime, Calendar latestTime){
        SetAddress(address);
        SetTime(earliestTime, latestTime);
    }

    public void AddPosition(Position p){
        DesiredPositions.add(p);
    }
    public void RemovePosition(Position p){
        DesiredPositions.remove(p);
    }
    public HashSet<Position> GetPositions(){
        return DesiredPositions;
    }

    public String GetAddress(){
        return Address;
    }
    public Calendar GetEarliestTime(){
        return EarliestTime;
    }
    public Calendar GetLatestTime(){
        return LatestTime;
    }

    public void SetAddress(String s){
        Address = s;
    }
    public void SetEarliestTime(Calendar time){
        EarliestTime = time;
    }
    public void SetLatestTime(Calendar time){
        LatestTime = time;
    }
    public void SetTime(Calendar earliest, Calendar latest){
        SetEarliestTime(earliest);
        SetLatestTime(latest);
    }
}
