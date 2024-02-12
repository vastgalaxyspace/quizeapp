package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;

public class MainActivity extends AppCompatActivity {

       String url = "https://raw.githubusercontent.com/curiousily/simple-quiz/master/script/statements-data.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url, null, response -> {
            Log.d("TAG","onCreate: "+response.toString());

        }, error -> {
            Log.d("TAG","failed due to sum error");

        });

        AppController.getInstance().addToRequestQueue(jsonArrayRequest);
    }
}