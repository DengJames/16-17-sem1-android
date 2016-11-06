package com.example.jamesdeng.finalproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent data) {
        String str = data.getStringExtra("BroadcastMessage");
        Toast.makeText(context, str + ", please proceed to the next step", Toast.LENGTH_SHORT).show();
    }
}
