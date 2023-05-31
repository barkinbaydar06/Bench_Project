package com.sportech.bench;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class Profile extends AppCompatActivity {

    private ListView matchView;
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

        /*
        Database.GetAllMatchInfo(new Database.MatchListCallback() {
            @Override
            public void onCallback(ArrayList<Match> value) {
                String[] Titles = new String[value.size()];
                for(int i = 0; i < value.size(); i++){
                    Titles[i] = value.get(i).GetText() + value.get(i).GetTime().toString();
                }
                CreateListView(Titles);
            }
        });*/
    }
    private void CreateListView(String[] val){
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, val);
        matchView.setAdapter(adapter);

        matchView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {


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