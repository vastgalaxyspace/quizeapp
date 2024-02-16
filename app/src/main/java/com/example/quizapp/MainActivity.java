package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.quizapp.controller.AppController;
import com.example.quizapp.data.AnswerListAsyncResponse;
import com.example.quizapp.data.Respository;
import com.example.quizapp.databinding.ActivityMainBinding;
import com.example.quizapp.model.Question;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private int currentQuestionIndex=0;
    List<Question> questionList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);

       questionList= new Respository().getQuestions(questionArrayList ->{
                binding.questiontextview.setText(questionArrayList.get(currentQuestionIndex).getAnswer());
           updateCounter(questionArrayList);

               }
       );

        binding.buttonNext.setOnClickListener(view -> {
            currentQuestionIndex=(currentQuestionIndex +1 ) %questionList.size();
            updateQuestion();

        });
        binding.buttonTrue.setOnClickListener(view -> {
            cheakAnswer(true);

        });
        binding.buttonFalse.setOnClickListener(view -> {
            cheakAnswer(false);
        });




    }

    private void cheakAnswer(boolean userchosecorrect) {
        boolean answer=questionList.get(currentQuestionIndex).isAnswertrue();
        int snackmessageid=0;
        if(userchosecorrect==answer){
            snackmessageid=R.string.correct_answer;
        }else{
            snackmessageid=R.string.false_answer;
        }
        Snackbar.make(binding.cardView,snackmessageid,Snackbar.LENGTH_SHORT).show();
    }

    private void updateCounter(ArrayList<Question> questionArrayList) {
        binding.textViewOutOf.  setText(String.format(getString(R.string.text_formatted) ,
                currentQuestionIndex, questionArrayList.size()));
    }

    private void updateQuestion() {
        String question= questionList.get(currentQuestionIndex).getAnswer();
        binding.questiontextview.setText(question);
        updateCounter((ArrayList<Question>) questionList);
    }

}