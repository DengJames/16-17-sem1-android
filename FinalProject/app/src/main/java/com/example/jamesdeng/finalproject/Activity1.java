package com.example.jamesdeng.finalproject;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Activity1 extends AppCompatActivity {

    MyDB db;
    TextView resultHere;
    EditText Entries;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);

        db = new MyDB(this);
        resultHere = (TextView) findViewById(R.id.Result);
        Entries = (EditText) findViewById(R.id.Input);

        ArrayList<String[]> myRecords = GetAllRecords();
        for (int i = 0; i < myRecords.size(); i++) {
            String[] myRecord = myRecords.get(i);
            // columnName1, columnName2 are stored in myRecord[0],myRecord[1] respectively
        }
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

    public void updateRecord(String id, String name2_str){//}, String name3_str){
        db.open();
        db.updateRecord(id, name2_str);//, name3_str);
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
            String[] temp = {c.getString(0),c.getString(1)};//,c.getString(2)};
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
        String fName=Entries.getText().toString();
       // String cName=et2.getText().toString();
        Entries.setText("");
       // et2.setText("");
        addRecord(fName);//, cName);
        Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show();
    }

    public void display(View v)
    {
        insert(v);
        db.open();
        Cursor c = db.getAllRecords();
        resultHere.setText("");
        c.moveToFirst();
        do{
            String fName=c.getString(1);
           // String cName=c.getString(2);
            resultHere.append(c.getString(0)+" "+fName+"\n");//+" "+cName+"\n");
        } while (c.moveToNext());
    }

    public void delete(View v){
        db.open();
        db.removeAll();
        resultHere.setText("");
        Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
        db.close();
    }
}
