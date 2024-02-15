package com.example.quizapp.data;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.quizapp.controller.AppController;
import com.example.quizapp.model.Question;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class Respository {

    ArrayList <Question> questionArrayList=new ArrayList<>();
    String url = "https://raw.githubusercontent.com/curiousily/simple-quiz/master/script/statements-data.json";

    public List<Question> getQuestions(){
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url, null, response -> {
            for (int i = 0; i < response.length(); i++) {
                try {
                    Question question=new Question(response.getJSONArray(i).get(0).toString(),
                            response.getJSONArray(i).getBoolean(1));



                    questionArrayList.add(question);
//                    Log.d("hello","getquestion : "+questionArrayList);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }


        }, error -> {
            Log.d("TAG","failed due to sum error");

        });

        AppController.getInstance().addToRequestQueue(jsonArrayRequest);
        return questionArrayList;
    }
}
