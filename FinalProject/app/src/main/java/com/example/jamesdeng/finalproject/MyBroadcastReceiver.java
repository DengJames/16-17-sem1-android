package com.example.jamesdeng.finalproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent data) {
        String str = data.getStringExtra("BroadcastMessage1");
        Toast.makeText(context, "Thank you, " + str + ", for using tha app. See you again!", Toast.LENGTH_SHORT).show();
    }
}
