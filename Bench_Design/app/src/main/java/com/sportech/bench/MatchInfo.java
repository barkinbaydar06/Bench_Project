package com.sportech.bench;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MatchInfo extends AppCompatActivity {

    private static String name, time, text, requiredPlayers, address;

    private Button register, unregister, back;
    private TextView matchDate, matchAdress, playerNo, notes;

    private ListView players;


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

        players = findViewById(R.id.playersInMatch);

        matchDate.setText(time);
        matchAdress.setText(address);
        notes.setText(text);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backIntent = new Intent(MatchInfo.this, MatchesActivity.class);
                finish();
                startActivity(backIntent);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });

        unregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unregister();
            }
        });
    }

    public void register()
    {
        Database.AddMatchUnderPlayer(Database.currentUser, Database.currentMatch);
    }

    public void unregister()
    {
        Database.RemovePlayerUnderMatch(Database.currentUser, Database.currentMatch);
    }
}