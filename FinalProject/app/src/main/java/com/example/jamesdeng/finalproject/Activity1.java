package com.example.jamesdeng.finalproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Activity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);
    }


    public void onClick_enterInput(View view){


        EditText Entries = (EditText)findViewById(R.id.Input);
        String getEntries = Entries.getEditableText().toString();

        TextView resultHere = (TextView) findViewById(R.id.Result);
        resultHere.setText(getEntries);

    }
}
