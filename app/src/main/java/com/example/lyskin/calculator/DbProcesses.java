package com.example.lyskin.calculator;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;


public class DbProcesses {

    private DbHelper dbHelper;

    public DbProcesses(DbHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    void setDataIntoDb(String data){
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(DbHelper.DbEntry.COLUMN_NAME_EQUATION,data);

        sqLiteDatabase.insert(DbHelper.DbEntry.TABLE_NAME,null,contentValues);
    }

    List<String> getDataFromDb(){
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(
                DbHelper.DbEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        final List<String> equationsResultsList = new ArrayList<>();

        while(cursor.moveToNext()){
            String equationResult = cursor.getString(
                    cursor.getColumnIndexOrThrow(DbHelper.DbEntry.COLUMN_NAME_EQUATION)
            );

            equationsResultsList.add(equationResult);
        }

        cursor.close();

        return equationsResultsList;
    }

    void deleteDataFromDb(){
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();

        sqLiteDatabase.delete(DbHelper.DbEntry.TABLE_NAME,"1",null);
    }

    void closeConnection(){
        dbHelper.close();
    }
}
