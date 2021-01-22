package com.example.truthordrink;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcel;

import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ImageButton extra_dirty_button;
    ImageButton on_the_rock_button;
    ImageButton last_call_button;
    ImageButton happy_hour_button;
    ImageButton mix_button;

    public static HashMap<String, Integer> map = new HashMap<>();

    SharedPreferences sharedpreferences;

    private final String ONTHEROCKS = "ONTHEROCKS";
    private final String EXTRADIRTY = "EXTRADIRTY";
    private final String HAPPYHOUR = "HAPPYHOUR";
    private final String LASTCALL = "LASTCALL";
    private final String MIX = "MIX";

    public static final String PREFS_NAME = "MyApp_Settings";

    private final String modeKey ="ModeKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedpreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);


        extra_dirty_button = (ImageButton) findViewById(R.id.extra_dirty_button);
        on_the_rock_button = (ImageButton) findViewById(R.id.on_the_rock_button);
        last_call_button = (ImageButton) findViewById(R.id.last_call_button);
        happy_hour_button = (ImageButton) findViewById(R.id.happy_hour_button);
        mix_button = (ImageButton) findViewById(R.id.mix_button);

        map.put(ONTHEROCKS, 0);
        map.put(EXTRADIRTY, 0);
        map.put(HAPPYHOUR, 0);
        map.put(LASTCALL, 0);
        map.put(MIX, 0);


        InputStream raw = this.getResources().openRawResource(R.raw.questions);
        BufferedReader reader = new BufferedReader(new InputStreamReader(raw));

        try {
            String line;
            while ((line = reader.readLine()) != null) {

                String[] splitted = line.split("#");
                map.put(splitted[0], map.get(splitted[0])+1);
                map.put(MIX, map.get(splitted[0])+1);

            }
            reader.close();
        } catch (IOException e) {
            System.err.println("File nicht gefunden");
            e.printStackTrace();
        }



    }

    public void setPreferences(String value){
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(modeKey, value);
        editor.apply();

    }

    public void onClick(View v) {
        if (extra_dirty_button.equals(v)) {

            if (map.get(EXTRADIRTY) > 0) {
                setPreferences(EXTRADIRTY);

                launchGame();
            }

        } else if (on_the_rock_button.equals(v)) {
            if (map.get(ONTHEROCKS) > 0) {
                setPreferences(ONTHEROCKS);
                launchGame();
            }

        } else if (last_call_button.equals(v)) {
            if (map.get(LASTCALL) > 0) {
                setPreferences(LASTCALL);
                launchGame();
            }

        } else if (happy_hour_button.equals(v)) {
            if (map.get(HAPPYHOUR) > 0) {
                setPreferences(HAPPYHOUR);
                launchGame();
            }

        } else if (mix_button.equals(v)) {
            if (map.get(MIX) > 0) {
                setPreferences(MIX);
                launchGame();
            }

        }
    }

    private void launchGame() {
        startActivity(new Intent(MainActivity.this, GameActivity.class));
    }

}