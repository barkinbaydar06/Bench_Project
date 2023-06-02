package com.sportech.bench;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;



public class NewMatch extends AppCompatActivity {

        private ImageButton profileButton, matchesButton;
        private Button cancelButton, confirmButton;
        private EditText day, month, year, hour, min, adress, playerNo, notes;
        private String matchDay, matchMonth, matchYear, matchHour, matchMinute, matchAdress, playersNeeded, matchNotes;

        protected AlertDialog.Builder confirmAlertBuilder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_match);
        profileButton = findViewById(R.id.profileButtonCreate);
        matchesButton = findViewById(R.id.matchesButtonCreate);

        cancelButton = findViewById(R.id.cancelButton);
        confirmButton = findViewById(R.id.confirmButton);

        confirmAlertBuilder = new AlertDialog.Builder(this);

        day = findViewById(R.id.dayInput);
        month = findViewById(R.id.monthInput);
        year = findViewById(R.id.yearInput);
        hour = findViewById(R.id.hourInput);
        min = findViewById(R.id.minuteInput);
        adress = findViewById(R.id.adressInput);
        playerNo = findViewById(R.id.playerNoInput);
        notes = findViewById(R.id.notesInput);
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
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(informationCheck()){
                    confirmCreating();
                }
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

        day.setText("");
        month.setText("");
        year.setText("");
        hour.setText("");
        min.setText("");
        adress.setText("");
        playerNo.setText("");
        notes.setText("");

        Intent cancelIntent = new Intent(NewMatch.this, MatchesActivity.class);
        finish();
        startActivity(cancelIntent);
    }

    protected void confirmCreating()
    {
        if(informationCheck()){
            Time matchTime = new Time(Integer.parseInt(year.getText().toString()), Integer.parseInt(month.getText().toString()), Integer.parseInt(day.getText().toString()), Integer.parseInt(hour.getText().toString()), Integer.parseInt(min.getText().toString()));

            Match newMatch = new Match(adress.getText().toString(), matchTime, notes.getText().toString(), Integer.parseInt(playerNo.getText().toString()));

            Database.AddMatch(newMatch);

            Database.AddMatchUnderPlayer(Database.currentUser, newMatch);
            Database.AddPlayerUnderMatch(Database.currentUser, newMatch);

            cancelCreating();
        }
    }
    protected boolean informationCheck()
    {
            if (day.getText().length() > 0)
            {
                if (Integer.parseInt(day.getText().toString()) > 31 )
                {
                    confirmAlertBuilder.setTitle("Alert");
                    confirmAlertBuilder.setMessage("Please enter a valid date!");
                    confirmAlertBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    confirmAlertBuilder.show();
                }
                else
                {
                    if (month.getText().length() > 0)
                    {
                        if (Integer.parseInt(month.getText().toString()) > 12)
                        {
                            confirmAlertBuilder.setTitle("Alert");
                            confirmAlertBuilder.setMessage("Please enter a valid date!");
                            confirmAlertBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                            confirmAlertBuilder.show();
                        }
                        else
                        {
                            if (year.getText().length() > 0)
                            {
                                if (Integer.parseInt(year.getText().toString()) > 23 )
                                {
                                    confirmAlertBuilder.setTitle("Alert");
                                    confirmAlertBuilder.setMessage("Please enter a valid date!");
                                    confirmAlertBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                        }
                                    });
                                    confirmAlertBuilder.show();
                                }
                                else
                                {
                                    if (hour.getText().length() > 0)
                                    {
                                        if (Integer.parseInt(hour.getText().toString()) > 24)
                                        {
                                            confirmAlertBuilder.setTitle("Alert");
                                            confirmAlertBuilder.setMessage("Please enter a valid time!");
                                            confirmAlertBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    dialog.cancel();
                                                }
                                            });
                                            confirmAlertBuilder.show();
                                        }
                                        else
                                        {
                                            if (min.getText().length() > 0)
                                            {
                                                if (Integer.parseInt(min.getText().toString()) > 59)
                                                {
                                                    confirmAlertBuilder.setTitle("Alert");
                                                    confirmAlertBuilder.setMessage("Please enter a valid time!");
                                                    confirmAlertBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {
                                                            dialog.cancel();
                                                        }
                                                    });
                                                    confirmAlertBuilder.show();
                                                }
                                                else
                                                {
                                                    if (adress.getText().length()>0)
                                                    {
                                                        if (playerNo.getText().length() > 0)
                                                        {
                                                            if (Integer.parseInt(playerNo.getText().toString()) > 16)
                                                            {
                                                                confirmAlertBuilder.setTitle("Alert");
                                                                confirmAlertBuilder.setMessage("Please enter a valid number of players!");
                                                                confirmAlertBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(DialogInterface dialog, int which) {
                                                                        dialog.cancel();
                                                                    }
                                                                });
                                                                confirmAlertBuilder.show();
                                                            }
                                                            else
                                                            {
                                                                return true;
                                                            }
                                                        }
                                                        else
                                                        {
                                                            confirmAlertBuilder.setTitle("Alert");
                                                            confirmAlertBuilder.setMessage("Player number cannot be empty!");
                                                            confirmAlertBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialog, int which) {
                                                                    dialog.cancel();
                                                                }
                                                            });
                                                            confirmAlertBuilder.show();
                                                        }
                                                    }
                                                    else
                                                    {
                                                        confirmAlertBuilder.setTitle("Alert");
                                                        confirmAlertBuilder.setMessage("Address cannot be empty!");
                                                        confirmAlertBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialog, int which) {
                                                                dialog.cancel();
                                                            }
                                                        });
                                                        confirmAlertBuilder.show();
                                                    }
                                                }
                                            }
                                            else
                                            {
                                                confirmAlertBuilder.setTitle("Alert");
                                                confirmAlertBuilder.setMessage("Minute cannot be empty!");
                                                confirmAlertBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        dialog.cancel();
                                                    }
                                                });
                                                confirmAlertBuilder.show();
                                            }
                                        }
                                    }
                                    else
                                    {
                                        confirmAlertBuilder.setTitle("Alert");
                                        confirmAlertBuilder.setMessage("Hour cannot be empty!");
                                        confirmAlertBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.cancel();
                                            }
                                        });
                                        confirmAlertBuilder.show();
                                    }
                                }
                            }
                            else
                            {
                                confirmAlertBuilder.setTitle("Alert");
                                confirmAlertBuilder.setMessage("Year cannot be empty!");
                                confirmAlertBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                                confirmAlertBuilder.show();
                            }
                        }
                    }
                    else {
                        confirmAlertBuilder.setTitle("Alert");
                        confirmAlertBuilder.setMessage("Month cannot be empty!");
                        confirmAlertBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        confirmAlertBuilder.show();
                    }
                }
            }
            else {
                confirmAlertBuilder.setTitle("Alert");
                confirmAlertBuilder.setMessage("Day cannot be empty!");
                confirmAlertBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                confirmAlertBuilder.show();
            }
        return false;

    }

    //methods to prevent the texts inside the editTexts to disappear when the activity is changed

    @Override
    protected void onPause()
    {
        super.onPause();

        matchDay = day.getText().toString();
        matchMonth = month.getText().toString();
        matchYear = year.getText().toString();
        matchHour = hour.getText().toString();
        matchMinute = min.getText().toString();
        matchAdress = adress.getText().toString();
        playersNeeded = playerNo.getText().toString();
        matchNotes = notes.getText().toString();

        SharedPreferences preferences = getSharedPreferences("MatchInformation", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("day", matchDay);
        editor.putString("month", matchMonth);
        editor.putString("year", matchYear);
        editor.putString("hour", matchHour);
        editor.putString("minute", matchMinute);
        editor.putString("adress", matchAdress);
        editor.putString("player", playersNeeded);
        editor.putString("notes", matchNotes);
        editor.apply();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        SharedPreferences preferences = getSharedPreferences("MatchInformation", MODE_PRIVATE);
        matchDay = preferences.getString("day","");
        matchMonth = preferences.getString("month","");
        matchYear = preferences.getString("year","");
        matchHour = preferences.getString("hour","");
        matchMinute = preferences.getString("minute","");
        matchAdress = preferences.getString("adress","");
        playersNeeded = preferences.getString("player","");
        matchNotes = preferences.getString("notes", "");

        day.setText(matchDay);
        month.setText(matchMonth);
        year.setText(matchYear);
        hour.setText(matchHour);
        min.setText(matchMinute);
        adress.setText(matchAdress);
        playerNo.setText(playersNeeded);
        notes.setText(matchNotes);
    }

}