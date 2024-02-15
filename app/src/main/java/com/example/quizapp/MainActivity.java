package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.quizapp.controller.AppController;
import com.example.quizapp.data.Respository;
import com.example.quizapp.model.Question;

import java.util.List;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List < Question> questions;
        questions = new Respository().getQuestions();

        Log.d("main","oncreate : "+ questions.get(0));


    }
}