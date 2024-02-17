package com.example.quizapp.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {
public static final String HIGHEST_SCORE="highest_score";
    public static final String STATE = "Trivia_state";
    private SharedPreferences preferences;

    public Prefs(Activity context) {
        this.preferences = context.getPreferences(Context.MODE_PRIVATE);
    }
    public void savehighestscore(int score){
        int lastscore=preferences.getInt(HIGHEST_SCORE,0);
        if(score > lastscore){
            preferences.edit().putInt(HIGHEST_SCORE, score).apply();
        }


    }
    public int gethighestscore(){
        return preferences.getInt(HIGHEST_SCORE,0);
    }
    public void setState(int index){
        preferences.edit().putInt(STATE,index).apply();
    }
    public int getState(){
         return preferences.getInt(STATE,0);

    }
}
