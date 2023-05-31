package com.sportech.bench;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MatchInfo extends AppCompatActivity {


    protected AlertDialog.Builder signUpAlertBuilder;

    private static String name, time, text, requiredPlayers, address;

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

        players = findViewById(R.id.playersInMatch);

        matchDate.setText(time);
        matchAdress.setText(address);
        notes.setText(text);


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
        Database.AddMatchUnderPlayer(Database.currentUser, Database.currentMatch);
        Database.AddPlayerUnderMatch(Database.currentUser, Database.currentMatch);

    }

    public void unregister()
    {
        Database.RemovePlayerUnderMatch(Database.currentUser, Database.currentMatch);
        Database.RemoveMatchUnderPlayer(Database.currentUser, Database.currentMatch);


    }
}