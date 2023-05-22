package com.sportech.bench;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Profile extends AppCompatActivity {

    private ImageButton matchesButton, newMatchButton, logoutButton;

    private AlertDialog.Builder logoutConfirmation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        matchesButton = (ImageButton) findViewById(R.id.matchesButtonProfile);
        newMatchButton = (ImageButton) findViewById(R.id.addButtonProfile);
        logoutButton = (ImageButton) findViewById(R.id.logoutButton);

        logoutConfirmation = new AlertDialog.Builder(this);
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
        logoutConfirmation.setTitle("Alert");
        logoutConfirmation.setMessage("Do you want to logout?");
        logoutConfirmation.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent logoutIntent = new Intent(Profile.this, MainActivity.class);
                finish();
                startActivity(logoutIntent);
            }
        });

        logoutConfirmation.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        logoutConfirmation.show();

    }
}