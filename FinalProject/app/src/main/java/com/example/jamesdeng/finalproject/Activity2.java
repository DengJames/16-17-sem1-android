package com.example.jamesdeng.finalproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import static com.example.jamesdeng.finalproject.R.id.btnUserInput;

public class Activity2 extends AppCompatActivity {

    TextView tv;
    String result;
    String getTheUserName;
    TextView tv2;

    SharedPreferences pref3;
    String itemCategory = "itemCategory";
    String itemName;

    EditText entries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //---------------------------------------------

        tv = (TextView) findViewById(R.id.textView);
        tv2 = (TextView) findViewById(R.id.textView2);

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        result = pref.getString("randomResult", "");

        SharedPreferences pref2 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        getTheUserName = pref2.getString("theUserName", "");

        tv.setText("");
        tv2.setText("");

        tv.append("Hi " + getTheUserName + ", lucky draw is:");
        tv2.append(result);

        //----------------------------------------------
    }


    public void backtoActivity1(View view) {
        Intent myIntent = new Intent(this, Activity1.class);
        startActivity(myIntent);
    }

    public void moveForward(View view) {


        // get prompts.xml view
        LayoutInflater layoutInflater = LayoutInflater.from(Activity2.this);
        View promptView = layoutInflater.inflate(R.layout.input_dialog, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Activity2.this);
        alertDialogBuilder.setView(promptView);

        entries = (EditText) promptView.findViewById(btnUserInput);
        // setup a dialog window
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        itemName = entries.getText().toString();
                        itemName = itemName.replaceAll(" ", "_").toLowerCase();

                        pref3 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

                        SharedPreferences.Editor editor = pref3.edit();
                        editor.putString(itemCategory, itemName);
                        editor.commit();

                        startActivity(new Intent(getBaseContext(), MapsActivity.class));

                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();

    }

}
