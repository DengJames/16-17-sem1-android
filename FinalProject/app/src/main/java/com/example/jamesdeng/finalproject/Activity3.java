package com.example.jamesdeng.finalproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Activity3 extends AppCompatActivity {
    SharedPreferences pref3;
    String input = "input";

    EditText entries;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        entries = (EditText) findViewById(R.id.btnUserInput);

    }


    public void onclick_GoToMap(View view) {
        String userInput = entries.getText().toString();

        pref3 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        SharedPreferences.Editor editor = pref3.edit();
        editor.putString(input, userInput);
        editor.commit();


        Intent myIntent = new Intent(this, MapsActivity.class);
        startActivity(myIntent);
    }
}
