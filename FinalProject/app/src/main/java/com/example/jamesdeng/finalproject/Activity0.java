package com.example.jamesdeng.finalproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Activity0 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_0);

        TextView tv = ((TextView) findViewById(R.id.tv0));
        tv.setText(getIntent().getStringExtra("KEY_StringName"));
    }
}
