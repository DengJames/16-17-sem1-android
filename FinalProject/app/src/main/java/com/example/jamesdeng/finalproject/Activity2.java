package com.example.jamesdeng.finalproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {

    TextView tv;
    String result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        tv = (TextView) findViewById(R.id.textView);


        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        result = pref.getString("randomResult", "");
        tv.setText("");
        tv.append("Lucky result: " + result);


    }

    public void backtoActivity1(View view){
        Intent myIntent = new Intent(this, Activity1.class);
        startActivity(myIntent);
    }

    public void moveForward(View view){
        //Intent myIntent = new Intent(this, Activity3.class);
        //startActivity(myIntent);
    }



}
