package com.example.jamesdeng.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;

public class Activity2 extends Activity {

    TextView tv;
    String result;
    String getTheUserName;
    TextView tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        //---------------------------------------------

        tv = (TextView) findViewById(R.id.textView);
        tv2 = (TextView) findViewById(R.id.textView2);

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        result = pref.getString("randomResult", "");

        SharedPreferences pref2 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        getTheUserName = pref2.getString("theUserName", "");

        tv.setText("");
        tv2.setText("");

        tv.append("Hi " + getTheUserName + ", lucky choice is:");
        tv2.append(result);

        //----------------------------------------------
    }


    public void backtoActivity1(View view) {
        Intent myIntent = new Intent(this, Activity1.class);
        startActivity(myIntent);
    }

    public void moveForward(View view) {
//        Intent myIntent = new Intent(this, Activity3.class);
//        startActivity(myIntent);
    }

}
