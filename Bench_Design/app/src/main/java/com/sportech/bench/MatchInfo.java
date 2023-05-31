package com.sportech.bench;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
<<<<<<< Updated upstream
=======
import android.view.View;
>>>>>>> Stashed changes
import android.widget.Button;
import android.widget.TextView;

public class MatchInfo extends AppCompatActivity {

    private static String name, time, text, requiredPlayers, address;
    private Button register, unregister, back;
    private TextView matchDate, matchAdress, playerNo, notes;

    private Button register, unregister, back;

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

<<<<<<< Updated upstream
        time = Database.currentMatch.GetTime().toString();
        text = Database.currentMatch.GetText();
        address = Database.currentMatch.GetAddress();

        matchDate.setText(time);
        matchAdress.setText(address);
        notes.setText(text);

=======
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

            }
        });

        unregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void register()
    {
        
>>>>>>> Stashed changes
    }
}