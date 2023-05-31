package com.sportech.bench;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Profile extends AppCompatActivity {

    private ListView matchView;

    private TextView usernameDisplay;
    private ImageButton matchesButton, newMatchButton, logoutButton;

    private AlertDialog.Builder logoutConfirmation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        matchesButton = (ImageButton) findViewById(R.id.matchesButtonProfile);
        newMatchButton = (ImageButton) findViewById(R.id.addButtonProfile);
        logoutButton = (ImageButton) findViewById(R.id.logoutButton);

        matchView = findViewById(R.id.profileMatchesList);
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

        Database.GetMatchesUnderUser(Database.currentUser, new Database.StringListCallback() {
            @Override
            public void onCallback(ArrayList<String> value) {
                ArrayList<Match> matches = new ArrayList<>();

                for(int i = 0; i < value.size(); i++){
                    Database.GetMatchInfo(value.get(i), new Database.MatchCallback() {
                        @Override
                        public void onCallback(Match value) {
                            matches.add(value);

                        }
                    });
                }



                String[] Titles = new String[matches.size()];
                for(int i = 0; i < matches.size(); i++){
                    Titles[i] = matches.get(i).GetText() + "   " + matches.get(i).GetTime().toString();
                }

                CreateListView(Titles, matches);
            }
        });

        usernameDisplay = findViewById(R.id.userUsername);
        usernameDisplay.setText(Database.currentUser.GetUserName());

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
    private void CreateListView(String[] val, ArrayList<Match> matches){
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, val);
        matchView.setAdapter(adapter);

        matchView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent infoIntent = new Intent(Profile.this, MatchInfo.class);
                Database.currentMatch = matches.get(position);
                finish();
                startActivity(infoIntent);
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