package com.sportech.bench;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MatchInfo extends AppCompatActivity {


    protected AlertDialog.Builder signUpAlertBuilder;

    private static String time, text, requiredPlayers, address;

    private Button register, unregister, back;
    private TextView matchDate, matchAdress, playerNo, notes;

    private ListView players;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_info);

        signUpAlertBuilder = new AlertDialog.Builder(this);

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
        requiredPlayers = String.valueOf(Database.currentMatch.GetRequiredPlayerCount());

        players = findViewById(R.id.playersInMatch);

        matchDate.setText(time);
        matchAdress.setText(address);
        notes.setText(text);
        playerNo.setText(requiredPlayers);


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

        Database.GetJoinedPlayers(Database.currentMatch.MatchID, new Database.StringListCallback() {
            @Override
            public void onCallback(ArrayList<String> value) {
                String[] userNames = new String[value.size()];
                for(int i = 0; i < value.size(); i++){
                    userNames[i] = value.get(i);
                }
                CreateListView(userNames);
            }
        });
    }

    private void CreateListView(String[] val){
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, val);
        players.setAdapter(adapter);

    }

    public void register()
    {
        Database.GetRequiredPlayerCount(Database.currentMatch.GetMatchID(), new Database.IntCallback() {
            @Override
            public void onCallback(int value) {
                if(value > 0){
                    Database.AddMatchUnderPlayer(Database.currentUser, Database.currentMatch);
                    Database.AddPlayerUnderMatch(Database.currentUser, Database.currentMatch);

                    Database.SetRequiredPlayerCount(Database.currentMatch.GetMatchID(), value - 1);
                    SetPlayerNumber(value - 1);
                }
            }
        });
    }

    public void unregister()
    {
        Database.GetRequiredPlayerCount(Database.currentMatch.GetMatchID(), new Database.IntCallback() {
            @Override
            public void onCallback(int value) {
                Database.RemovePlayerUnderMatch(Database.currentUser, Database.currentMatch);
                Database.RemoveMatchUnderPlayer(Database.currentUser, Database.currentMatch);

                Database.SetRequiredPlayerCount(Database.currentMatch.GetMatchID(), value + 1);
                SetPlayerNumber(value + 1);
            }
        });
    }

    public void SetPlayerNumber(int value){
        playerNo.setText(String.valueOf(value));
    }
}