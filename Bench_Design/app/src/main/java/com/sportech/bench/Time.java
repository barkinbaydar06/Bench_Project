package com.sportech.bench;

public class Time {
    public int year;
    public int month;
    public int day;
    public int hour;
    public int minute;

    public Time(int year, int month, int day, int hour, int minute){
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
    }

    public int getYear(){
        return year;
    }
    public int getMonth(){
        return month;
    }
    public int getDay(){
        return day;
    }
    public int getHour(){
        return hour;
    }
    public int getMinute(){
        return minute;
    }

    public boolean after(Time earlier){
        if(year > earlier.year){
            return true;
        }
        else if(month > earlier.month){
            return true;
        }
        else if(day > earlier.day){
            return true;
        }
        else if(hour > earlier.hour){
            return true;
        }
        else return minute >= earlier.minute;
    }

    public boolean before(Time later){
        if(year < later.year){
            return true;
        }
        else if(month < later.month){
            return true;
        }
        else if(day < later.day){
            return true;
        }
        else if(hour < later.hour){
            return true;
        }
        else return minute <= later.minute;
    }
}
