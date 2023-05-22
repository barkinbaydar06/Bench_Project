package com.sportech.bench;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class NewMatch extends AppCompatActivity {

        private ImageButton profileButton, matchesButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_match);
        profileButton = findViewById(R.id.profileButtonCreate);
        matchesButton = findViewById(R.id.matchesButtonCreate);
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
}