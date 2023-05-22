package com.sportech.bench;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Profile extends AppCompatActivity {

    private ImageButton matchesButton, newMatchButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        matchesButton = findViewById(R.id.matchesButtonProfile);
        newMatchButton = findViewById(R.id.addButtonProfile);
        matchesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMatchesPage();
            }
        });
        newMatchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNewMatchPage();
            }
        });
    }

    //Menu Bar Button Methods
    protected void goToMatchesPage()
    {
        Intent matchesIntent = new Intent(Profile.this, MatchesActivity.class);
        finish();
        startActivity(matchesIntent);
    }

    protected void goToNewMatchPage()
    {
        Intent newMatchIntent = new Intent(Profile.this, NewMatch.class);
        finish();
        startActivity(newMatchIntent);
    }
}