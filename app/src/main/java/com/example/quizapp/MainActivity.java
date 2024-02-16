package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.quizapp.controller.AppController;
import com.example.quizapp.data.AnswerListAsyncResponse;
import com.example.quizapp.data.Respository;
import com.example.quizapp.databinding.ActivityMainBinding;
import com.example.quizapp.model.Question;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private int currentQuestionIndex=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        List < Question> questions;
        new Respository().getQuestions(questionArrayList ->
                binding.questiontextview.setText(questionArrayList.get(currentQuestionIndex).getAnswer())
        );




    }
}