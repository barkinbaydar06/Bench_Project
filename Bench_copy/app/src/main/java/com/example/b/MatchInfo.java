package com.example.b;

import java.util.Calendar;

public class MatchInfo {
    String Address;
    Calendar Time;
    MatchType TypeOfMatch;

    public static enum MatchType{
        Football,
        Basketball
    }
}
