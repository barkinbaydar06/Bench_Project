package com.sportech.bench;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ListView;

public class MatchesActivity extends AppCompatActivity
{
    ImageButton matchesButton, addButton, profileButton;
    ListView matchesList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
}