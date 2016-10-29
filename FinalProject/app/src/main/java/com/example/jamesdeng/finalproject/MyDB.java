package com.example.jamesdeng.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import static com.example.jamesdeng.finalproject.MyDBHelper.tableName;

public class MyDB {
    MyDBHelper DBHelper;
    SQLiteDatabase db;
    final Context context;

    public MyDB(Context ctx){
        this.context = ctx;
        DBHelper = new MyDBHelper(this.context);
    }

    public MyDB open() {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        DBHelper.close();
    }

    public long insertRecord(String name2_str){ //, String name3_str){
        ContentValues initialValues = new ContentValues();
        initialValues.put(MyDBHelper.columnName2, name2_str);
        //initialValues.put(MyDBHelper.columnName3, name3_str);
        return db.insert(tableName, null, initialValues);
    }

    public int deleteRecord(long rowIDToBeDeleted){
        return db.delete(tableName, MyDBHelper.columnName1 + "=" + rowIDToBeDeleted, null);
    }

    public void removeAll() {
        // db.delete(String tableName, String whereClause, String[] whereArgs);
        // If whereClause is null, it will delete all rows.

        db.delete(tableName, null, null);
        db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" + tableName + "'");
    }

    public int updateRecord(String name1_str, String name2_str){ //, String name3_str){
        ContentValues initialValues = new ContentValues();
        initialValues.put(MyDBHelper.columnName2, name2_str);
       // initialValues.put(MyDBHelper.columnName3, name3_str);
        return db.update(tableName, initialValues, MyDBHelper.columnName1 + "=" + name1_str, null);
    }

    public Cursor getAllRecords() {
        return db.query(
                tableName,
                new String[] {
                        MyDBHelper.columnName1,
                        MyDBHelper.columnName2},
                      //  MyDBHelper.columnName3 },
                null, null, null, null, null);
    }


    public Cursor getRecord(long id) {
        Cursor mCursor = db.query(tableName,
                new String[] {
                        MyDBHelper.columnName1,
                        MyDBHelper.columnName2},
                     //   MyDBHelper.columnName3},
                MyDBHelper.columnName1 + "=" + id,
                null, null, null, null, null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }



}
