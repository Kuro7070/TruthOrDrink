package com.example.truthordrink;

import android.app.UiAutomation;
import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Questions {

    public static HashMap<String, List<String>> map = new HashMap<>();

    public static int mode = 0;

    public static boolean loaded = false;

    private static final String ONTHEROCKS = "ONTHEROCKS";
    private static final String EXTRADIRTY = "EXTRADIRTY";
    private static final String HAPPYHOUR = "HAPPYHOUR";
    private static final String LASTCALL = "LASTCALL";
    private static final String MIX = "MIX";
static{
    map.put(ONTHEROCKS, new ArrayList<String>());
    map.put(EXTRADIRTY, new ArrayList<String>());
    map.put(HAPPYHOUR, new ArrayList<String>());
    map.put(LASTCALL, new ArrayList<String>());
}
    public Questions() {


    }

    public void read(Context context) {
        //textfile reader

        try {

            System.out.println(context);
            InputStream raw = context.getAssets().open("questions.txt");
            BufferedReader is = new BufferedReader(new InputStreamReader(raw, "UTF8"));

            String line;
            while ((line = is.readLine()) != null) {

                System.out.println("#############################" + line);
                String splitted[] = line.split("#");

                switch (splitted[0]) {
                    case ONTHEROCKS:
                        map.get(ONTHEROCKS).add(splitted[1]);
                        break;
                    case EXTRADIRTY:
                        map.get(EXTRADIRTY).add(splitted[1]);
                        break;
                    case HAPPYHOUR:
                        map.get(HAPPYHOUR).add(splitted[1]);
                        break;
                    case LASTCALL:
                        map.get(LASTCALL).add(splitted[1]);
                        break;
                }
            }
            is.close();
        } catch (IOException e) {
            System.out.println("############################################file konnte nicht gelesen");
            e.printStackTrace();
        }

        System.out.println(map.toString());

    }

    public void addToMap(String key, String value){map.get(key).add(value);}

    public static List<String> getQuestionList(String mode) {
        switch (mode) {
            case ONTHEROCKS:
                return map.get(ONTHEROCKS);

            case EXTRADIRTY:
                return map.get(EXTRADIRTY);

            case HAPPYHOUR:
                return map.get(HAPPYHOUR);

            case LASTCALL:
                return map.get(LASTCALL);

            case MIX:
                List<String> liste = new ArrayList<>();
                liste.addAll(map.get(ONTHEROCKS));
                liste.addAll(map.get(EXTRADIRTY));
                liste.addAll(map.get(HAPPYHOUR));
                liste.addAll(map.get(LASTCALL));

                return liste;
        }

        return null;
    }

    public HashMap<String, List<String>> getMap() {
        return map;
    }

    public static boolean checkListNull(String key){

        switch (key) {
            case ONTHEROCKS:
                return !(map.get(ONTHEROCKS).size() > 0);

            case EXTRADIRTY:
                System.out.println(map.get(EXTRADIRTY).size());
                return !(map.get(EXTRADIRTY).size() > 0);

            case HAPPYHOUR:
                return !(map.get(HAPPYHOUR).size() > 0);

            case LASTCALL:
                return !(map.get(LASTCALL).size() > 0);
            case MIX:
                return !((map.get(ONTHEROCKS).size() + map.get(EXTRADIRTY).size() + map.get(HAPPYHOUR).size() + map.get(LASTCALL).size()) > 0);

        }
    return false;
    }


//list operations


}
