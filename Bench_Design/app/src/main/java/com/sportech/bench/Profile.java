package com.sportech.bench;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Profile extends AppCompatActivity {

    private ImageButton matchesButton, newMatchButton, logoutButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        matchesButton = (ImageButton) findViewById(R.id.matchesButtonProfile);
        newMatchButton = (ImageButton) findViewById(R.id.addButtonProfile);
        logoutButton = (ImageButton) findViewById(R.id.logoutButton);
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
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
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

    protected void logout()
    {
        Intent logoutIntent = new Intent(Profile.this, MainActivity.class);
        finish();
        startActivity(logoutIntent);
    }
}