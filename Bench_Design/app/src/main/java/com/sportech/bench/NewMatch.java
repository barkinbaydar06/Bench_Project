package com.sportech.bench;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class NewMatch extends AppCompatActivity {

        private ImageButton profileButton, matchesButton;
        private Button cancelButton;
        private EditText name, day, month, year, hour, min, adress, playerNo;
        private String matchName, matchDate, matchTime, matchAdress, playersNeeded;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_match);
        profileButton = findViewById(R.id.profileButtonCreate);
        matchesButton = findViewById(R.id.matchesButtonCreate);

        cancelButton = findViewById(R.id.cancelButton);

        name = findViewById(R.id.matchNameInput);
        day = findViewById(R.id.dayInput);
        month = findViewById(R.id.monthInput);
        year = findViewById(R.id.yearInput);
        hour = findViewById(R.id.hourInput);
        min = findViewById(R.id.minuteInput);
        adress = findViewById(R.id.adressInput);
        playerNo = findViewById(R.id.playerNoInput);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                goToProfilePage();
            }
        });
        matchesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                goToMatchesPage();
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelCreating();
            }
        });
    }

    //Menu Bar Button Methods
    protected void goToProfilePage()
    {
        Intent profileIntent = new Intent(NewMatch.this, Profile.class);
        finish();
        startActivity(profileIntent);
    }

    protected void goToMatchesPage()
    {
        Intent matcesIntent = new Intent(NewMatch.this, MatchesActivity.class);
        finish();
        startActivity(matcesIntent);
    }

    //Page Button Methods
    protected void cancelCreating()
    {
        goToMatchesPage();
    }

    protected void confirmCreating()
    {

    }
}