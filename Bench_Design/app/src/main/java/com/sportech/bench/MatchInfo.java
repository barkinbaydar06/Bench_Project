package com.sportech.bench;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MatchInfo extends AppCompatActivity {

    private static String name, time, text, requiredPlayers, address;
    private Button register, unregister, back;
    private TextView matchDate, matchAdress, playerNo, notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_info);

        matchDate = findViewById(R.id.dateInfo);
        matchAdress = findViewById(R.id.adressInfo);
        playerNo = findViewById(R.id.playerNoInfo);
        notes = findViewById(R.id.notesInfo);

        register = findViewById(R.id.registerButton);
        unregister = findViewById(R.id.unregisterButton);
        back = findViewById(R.id.backButton);

        time = Database.currentMatch.GetTime().toString();
        text = Database.currentMatch.GetText();
        address = Database.currentMatch.GetAddress();

        matchDate.setText(time);
        matchAdress.setText(address);
        notes.setText(text);

    }
}