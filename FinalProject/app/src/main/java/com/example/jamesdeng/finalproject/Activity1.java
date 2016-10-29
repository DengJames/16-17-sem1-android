package com.example.jamesdeng.finalproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class Activity1 extends AppCompatActivity {

    SharedPreferences pref;
    String randomResult = "randomResult";

    MyDB db;
    TextView resultHere;
    EditText entries;
    ScrollView sv;
    int numOfInput = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);

        db = new MyDB(this);
        resultHere = (TextView) findViewById(R.id.Result);
        entries = (EditText) findViewById(R.id.Input);
        entries.setImeOptions(EditorInfo.IME_ACTION_DONE);
        sv = (ScrollView) findViewById(R.id.scrollView1);

        ArrayList<String[]> myRecords = GetAllRecords();
        for (int i = 0; i < myRecords.size(); i++) {
            String[] myRecord = myRecords.get(i);
        }

        // handle soft/hard keyboard enter key
        entries.setOnEditorActionListener(
                new EditText.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if (actionId == EditorInfo.IME_ACTION_DONE) {
                            display(v);
                            return true;
                        }
                        else if (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() &&
                                        KeyEvent.ACTION_DOWN == event.getAction() ) {
                            display(v);
                            return true;
                        }
                        return false;
                    }
                }
        );

    }

// when press enter on physical keyboard, entries is still focus
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if (!entries.hasFocus()){
            entries.requestFocus();
            entries.onKeyDown(keyCode, event);

            InputMethodManager inputManager =
                    (InputMethodManager) this.
                            getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(
                    this.getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
        return super.onKeyDown(keyCode, event);
    }


    public void addRecord(String name2_str){//}, String name3_str){
        db.open();
        db.insertRecord(name2_str);//, name3_str);
        // insertRecord is a user-defined method in MyDB that will call db.insert ()
        db.close();
    }

    public int deleteRecord(long id){
        db.open();
        int res = db.deleteRecord(id); //deleteRecord is a user-defined method in MyDB that will call db.delete()
        db.close();
        return res;
    }

    public void updateRecord(String id, String name2_str){
        db.open();
        db.updateRecord(id, name2_str);
        // updateRecord is a user-defined method in MyDB that will call db.update ()
        db.close();
    }

    public void retrieveRecord(long id){
        db.open();
        Cursor c = db.getRecord(id);
        db.close();
    }

    public String[] GetRecord(long id) {
        db.open();
        Cursor c = db.getRecord(id);
        String[] record = new String[2];
        if (c.moveToFirst()) {
            String[] temp = {c.getString(0),c.getString(1)};
            record = temp; }
        else
            Toast.makeText(this, "No record found", Toast.LENGTH_LONG).show();
        db.close();
        return record;
    }

    public void retrieveAllRecord(){
        db.open();
        Cursor c = db.getAllRecords();
        db.close();
    }

    public ArrayList<String[]> GetAllRecords() {
        db.open();
        Cursor c = db.getAllRecords();
        ArrayList<String[]> records = new ArrayList<String[]>();
        if (c.moveToFirst()){
            do{
                String[] record = {c.getString(0),c.getString(1)};//,c.getString(2)};
                records.add(record);
            } while (c.moveToNext());
        }
        db.close();
        return records;
    }

    public void insert(View v)
    {
        String fName= entries.getText().toString();
        entries.setText("");
        if (fName.matches("")  ) {
            Toast.makeText(this, "Please input your item", Toast.LENGTH_SHORT).show();
        } else {
            addRecord(fName);
            Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show();
        }

    }

    public void display(View v)
    {
        insert(v);

        // auto scroll to bottom
        sv.post(new Runnable() {
            @Override
            public void run() {
                sv.fullScroll(View.FOCUS_DOWN);
            }
        });

        db.open();
        Cursor c = db.getAllRecords();
        resultHere.setText("");
        c.moveToFirst();
        if ( c.moveToFirst() ) {
            do {
                String fName = c.getString(1);
                resultHere.append(c.getString(0) + " " + fName + "\n");
                numOfInput = Integer.parseInt(c.getString(0));
            } while (c.moveToNext());
        }

    }


    public void delete(View v){
        numOfInput = 0;
        db.open();
        db.removeAll();
        resultHere.setText("");
        Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
        db.close();


//        InputMethodManager inputManager =
//                (InputMethodManager) this.
//                        getSystemService(Context.INPUT_METHOD_SERVICE);
//        inputManager.hideSoftInputFromWindow(
//                this.getCurrentFocus().getWindowToken(),
//                InputMethodManager.HIDE_NOT_ALWAYS);

    }

    public void calculateRand(View view){
      //  Intent myIntent = new Intent(this, Activity2.class);
       // startActivity(myIntent);


        if (numOfInput == 0) {
            Toast.makeText(this, "Please input your item", Toast.LENGTH_SHORT).show();
        } else {
            Random ran = new Random();
            int x = ran.nextInt(numOfInput) + 1;

            db.open();

            Cursor c = db.getRecord(x);
            String fName = c.getString(1);
            String calculatedRandomResult = c.getString(0) + fName;

            pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

            SharedPreferences.Editor editor = pref.edit();
            editor.putString(randomResult, calculatedRandomResult);
            editor.commit();

            db.close();

            Intent myIntent = new Intent(this, Activity2.class);
            startActivity(myIntent);
        }

//        InputMethodManager inputManager =
//                (InputMethodManager) this.
//                        getSystemService(Context.INPUT_METHOD_SERVICE);
//        inputManager.hideSoftInputFromWindow(
//                this.getCurrentFocus().getWindowToken(),
//                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    // click empty space, hide keyboard
    public boolean onTouchEvent(MotionEvent event) {
        if(null != this.getCurrentFocus()){
            /**
             *  hide keyboard
             */
            InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            return mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        }
        return super .onTouchEvent(event);
    }

    // after user hit the back button, and return back to the app, the input still be shown
    @Override
    public void onResume(){
        super.onResume();

        db.open();
        Cursor c = db.getAllRecords();
        resultHere.setText("");
        if ( c.moveToFirst() ) {
            do{
                String fName=c.getString(1);

                resultHere.append(c.getString(0)+" "+fName+"\n");
                numOfInput = Integer.parseInt(c.getString(0));
            } while (c.moveToNext());
        }
    }
}
