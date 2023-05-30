package com.sportech.bench;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class MatchesActivity extends AppCompatActivity
{
    private ImageButton profileButton, newMatchButton;

    private ListView matches;

    private ArrayList<Match>  avaibleMatches;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        profileButton = findViewById(R.id.profileButtonMatches);
        newMatchButton = findViewById(R.id.addButtonMatches);
        matches = findViewById(R.id.matchesList);
        avaibleMatches = new ArrayList<>();

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                goToProfilePage();
            }
        });
        newMatchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                goToNewMatchPage();
            }
        });
    }

    //Menu Bar Button Methods
     protected void goToProfilePage()
     {
         Intent profileIntent = new Intent(MatchesActivity.this, Profile.class);
         finish();
         startActivity(profileIntent);
     }

     protected  void goToNewMatchPage()
     {
         Intent newMatchIntent = new Intent(MatchesActivity.this, NewMatch.class);
         finish();
         startActivity(newMatchIntent);
     }

     protected void addMatches()
     {
         avaibleMatches = Database.GetAllMatchInfoArrayList();
         matches.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, avaibleMatches));
     }


}