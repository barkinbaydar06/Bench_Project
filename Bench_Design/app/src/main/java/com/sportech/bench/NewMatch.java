package com.sportech.bench;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class NewMatch extends AppCompatActivity {

        private ImageButton profileButton, matchesButton;
        private Button cancelButton;
        private EditText name, day, month, year, hour, min, adress, playerNo;
        private String matchName, matchDay, matchMonth, matchYear, matchHour, matchMinute, matchAdress, playersNeeded;
        private static boolean cancelClicked;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_match);
        profileButton = findViewById(R.id.profileButtonCreate);
        matchesButton = findViewById(R.id.matchesButtonCreate);

        cancelButton = findViewById(R.id.cancelButton);

        name = findViewById(R.id.matchNameInput);
        day = findViewById(R.id.dayInput);
        month = findViewById(R.id.monthInput);
        year = findViewById(R.id.yearInput);
        hour = findViewById(R.id.hourInput);
        min = findViewById(R.id.minuteInput);
        adress = findViewById(R.id.adressInput);

        playerNo = findViewById(R.id.playerNoInput);
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
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelCreating();
            }
        });
    }

    //Menu Bar Button Methods
    protected void goToProfilePage()
    {
        playersNeeded = playerNo.getText().toString();
        Intent profileIntent = new Intent(NewMatch.this, Profile.class);
        finish();
        startActivity(profileIntent);
    }

    protected void goToMatchesPage()
    {
        playersNeeded = playerNo.getText().toString();
        Intent matchesIntent = new Intent(NewMatch.this, MatchesActivity.class);
        finish();
        startActivity(matchesIntent);
    }

    //Page Button Methods
    protected void cancelCreating()
    {
        SharedPreferences preferences = getSharedPreferences("MatchInformation", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();

        name.setText("");
        day.setText("");
        month.setText("");
        year.setText("");
        hour.setText("");
        min.setText("");
        adress.setText("");
        playerNo.setText("");

        Intent cancelIntent = new Intent(NewMatch.this, MatchesActivity.class);
        finish();
        startActivity(cancelIntent);
    }

    protected void confirmCreating()
    {

    }

    //methods to prevent the texts inside the editTexts to disappear when the activity is changed

    @Override
    protected void onPause()
    {
        super.onPause();

        matchName = name.getText().toString();
        matchDay = day.getText().toString();
        matchMonth = month.getText().toString();
        matchYear = year.getText().toString();
        matchHour = hour.getText().toString();
        matchMinute = min.getText().toString();
        matchAdress = adress.getText().toString();
        playersNeeded = playerNo.getText().toString();

        SharedPreferences preferences = getSharedPreferences("MatchInformation", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("name", matchName);
        editor.putString("day", matchDay);
        editor.putString("month", matchMonth);
        editor.putString("year", matchYear);
        editor.putString("hour", matchHour);
        editor.putString("minute", matchMinute);
        editor.putString("adress", matchAdress);
        editor.putString("player", playersNeeded);
        editor.apply();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        SharedPreferences preferences = getSharedPreferences("MatchInformation", MODE_PRIVATE);
        matchName = preferences.getString("name","");
        matchDay = preferences.getString("day","");
        matchMonth = preferences.getString("month","");
        matchYear = preferences.getString("year","");
        matchHour = preferences.getString("hour","");
        matchMinute = preferences.getString("minute","");
        matchAdress = preferences.getString("adress","");
        playersNeeded = preferences.getString("player","");

        name.setText(matchName);
        day.setText(matchDay);
        month.setText(matchMonth);
        year.setText(matchYear);
        hour.setText(matchHour);
        min.setText(matchMinute);
        adress.setText(matchAdress);
        playerNo.setText(playersNeeded);
    }

}