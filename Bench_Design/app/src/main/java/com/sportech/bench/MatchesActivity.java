package com.sportech.bench;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MatchesActivity extends AppCompatActivity
{
    private ListView matchView;
    private ImageButton profileButton, newMatchButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        profileButton = findViewById(R.id.profileButtonMatches);
        newMatchButton = findViewById(R.id.addButtonMatches);
        matchView = findViewById(R.id.matchesList);
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


        Database.GetAllMatchInfo(new Database.MatchListCallback() {
            @Override
            public void onCallback(ArrayList<Match> value) {
                //ArrayList<Match> sortedValue = SortByTime(value);
                String[] Titles = new String[value.size()];
                for(int i = 0; i < value.size(); i++){
                    Titles[i] = value.get(i).GetText() + "   " + value.get(i).GetTime().toString();
                }
                CreateListView(Titles, value);
            }
        });

    }
    /*
    private ArrayList<Match> SortByTime(ArrayList<Match> value){
        boolean allSorted;
        do{
            allSorted = true;
            for(int i = 0; i < value.size() - 1; i++){
                if(value.get(i).GetTime().after(value.get(i + 1).GetTime())){
                    allSorted = false;

                    Match newBefore = value.get(i + 1);
                    Match newAfter = value.get(i);

                    value.set(i, newBefore);
                    value.set(i + 1, newAfter);
                }
            }

        }while(!allSorted);
        return value;
    }*/
    private void CreateListView(String[] val, ArrayList<Match> matches){
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, val);
        matchView.setAdapter(adapter);

        matchView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {


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
}