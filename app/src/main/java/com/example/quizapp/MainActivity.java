package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.graphics.Color;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.quizapp.data.Respository;
import com.example.quizapp.databinding.ActivityMainBinding;
import com.example.quizapp.model.Question;
import com.example.quizapp.model.Score;
import com.example.quizapp.util.Prefs;
import com.google.android.material.snackbar.Snackbar;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private int currentQuestionIndex=0;
    List<Question> questionList;
    private int scorecounter=0;
    private Score score;
    private Prefs prefs;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        score = new Score();
        prefs=new Prefs(MainActivity.this);
        binding.highestScore.setText(MessageFormat.format("Highest={0}", String.valueOf(prefs.gethighestscore())));
        binding.scoreText.setText(MessageFormat.format("Current Score={0}", String.valueOf(score.getScore())));


       questionList= new Respository().getQuestions(questionArrayList ->{
                binding.questiontextview.setText(questionArrayList.get(currentQuestionIndex).getAnswer());
           updateCounter(questionArrayList);

               }
       );

        binding.buttonNext.setOnClickListener(view -> {
            getnextquestion();

        });
        binding.buttonTrue.setOnClickListener(view -> {
            cheakAnswer(true);
            updateQuestion();

        });
        binding.buttonFalse.setOnClickListener(view -> {
            cheakAnswer(false);
            updateQuestion();
        });




    }

    private void getnextquestion() {
        currentQuestionIndex=(currentQuestionIndex +1 ) %questionList.size();
        updateQuestion();
    }

    private void cheakAnswer(boolean userchosecorrect) {
        boolean answer=questionList.get(currentQuestionIndex).isAnswertrue();
        int snackmessageid=0;
        if(userchosecorrect==answer){
            snackmessageid=R.string.correct_answer;
            fadeanimation();
            addpoint();
        }else{
            snackmessageid=R.string.false_answer;
            sheakAnimation();
            deductpoint();
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
    private void fadeanimation(){
        AlphaAnimation alphaAnimation=new AlphaAnimation(1.0f,0.0f);
        alphaAnimation.setDuration(300);
        alphaAnimation.setRepeatCount(1);
        alphaAnimation.setRepeatMode(Animation.REVERSE);
        binding.cardView.setAnimation(alphaAnimation);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                binding.questiontextview.setTextColor(Color.GREEN);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                binding.questiontextview.setTextColor(Color.WHITE);
                getnextquestion();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
    private void sheakAnimation(){
        Animation snake= AnimationUtils.loadAnimation(MainActivity.this,R.anim.shake_animation);
        binding.cardView.setAnimation(snake);

        snake.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                binding.questiontextview.setTextColor(Color.RED);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                binding.questiontextview.setTextColor(Color.WHITE);
                getnextquestion();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void deductpoint(){

        if(scorecounter>0){
            scorecounter-=100;
            score.setScore(scorecounter);

            binding.scoreText.setText(MessageFormat.format("Score={0}", String.valueOf(score.getScore())));
        }else{
            scorecounter=0;
            score.setScore(scorecounter);

        }
    }

    private void addpoint(){
        scorecounter +=100;
        score.setScore(scorecounter);
        binding.scoreText.setText(String.valueOf(score.getScore()));
        binding.scoreText.setText(MessageFormat.format("Score={0}", String.valueOf(score.getScore())));


    }

    @Override
    protected void onPause() {
        prefs.savehighestscore(score.getScore());
        super.onPause();
    }
}