package com.example.quizapp.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {
public static final String HIGHEST_SCORE="highest_score";
    private SharedPreferences preferences;

    public Prefs(Activity context) {
        this.preferences = context.getPreferences(Context.MODE_PRIVATE);
    }
    public void savehighestscore(int score){
        int currentscore= score;
        int lastscore=preferences.getInt(HIGHEST_SCORE,0);
        if(currentscore > lastscore){
            preferences.edit().putInt(HIGHEST_SCORE,currentscore).apply();
        }


    }
    public int gethighestscore(){
        return preferences.getInt(HIGHEST_SCORE,0);
    }
}
