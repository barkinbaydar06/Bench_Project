package com.sportech.bench;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MatchInfo extends AppCompatActivity {

    private TextView matchName, matchDate, matchTime, matchAdress, playerNo, notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_info);

        matchName = findViewById(R.id.nameInfo);
        matchDate = findViewById(R.id.dateInfo);
        matchTime = findViewById(R.id.timeInfo);
        matchAdress = findViewById(R.id.adressInfo);
        playerNo = findViewById(R.id.playerNoInfo);
        notes = findViewById(R.id.notesInfo);
    }
}