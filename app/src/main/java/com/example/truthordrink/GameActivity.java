package com.example.truthordrink;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class GameActivity extends AppCompatActivity {

    private final String ONTHEROCKS = "ONTHEROCKS";
    private final String EXTRADIRTY = "EXTRADIRTY";
    private final String HAPPYHOUR = "HAPPYHOUR";
    private final String LASTCALL = "LASTCALL";
    private final String MIX = "MIX";

    private final String modeKey = "ModeKey";
    public static final String PREFS_NAME = "MyApp_Settings";
    SharedPreferences sharedpreferences;


    public static HashMap<String, List<String>> map = new HashMap<>();
    private List<String> questions;

    int counter = 0;

    TextView questionView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        sharedpreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        map.put(ONTHEROCKS, new ArrayList<String>());
        map.put(EXTRADIRTY, new ArrayList<String>());
        map.put(HAPPYHOUR, new ArrayList<String>());
        map.put(LASTCALL, new ArrayList<String>());
        map.put(MIX, new ArrayList<String>());

        InputStream raw = this.getResources().openRawResource(R.raw.questions);
        BufferedReader reader = new BufferedReader(new InputStreamReader(raw));

        try {
            String line;
            while ((line = reader.readLine()) != null) {

                String[] splitted = line.split("#");
                map.get(splitted[0]).add(splitted[1]);
                map.get(MIX).add(splitted[1]);

            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }



        questionView = findViewById(R.id.textView);
        questions = new ArrayList<>();

        String mode = sharedpreferences.getString(modeKey, "leer");


        if (mode.equals(ONTHEROCKS)) {
            questions = map.get(ONTHEROCKS);

        } else if (mode.equals(EXTRADIRTY)) {
            questions = map.get(EXTRADIRTY);

        } else if (mode.equals(LASTCALL)) {
            questions = map.get(LASTCALL);

        } else if (mode.equals(HAPPYHOUR)) {
            questions = map.get(HAPPYHOUR);

        } else if (mode.equals(MIX)) {
            questions = map.get(MIX);

        } else {
            //Zurück da keine Fragen in der Liste
            launchGame();
        }


        Collections.shuffle(questions);

        questionView.setText(questions.get(counter));

    }

    private void launchGame() {
        startActivity(new Intent(GameActivity.this, MainActivity.class));
    }

    public void clickBack(View v) {

        if (counter - 1 >= 0) {
            counter--;
            questionView.setText(questions.get(counter));
        }

    }

    public void clickNext(View v) {

        if (counter + 1 < questions.size()) {
            counter++;
            questionView.setText(questions.get(counter));
        } else {
            launchGame();
        }

    }
}
