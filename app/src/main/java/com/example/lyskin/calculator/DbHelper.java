package com.example.lyskin.calculator;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class DbHelper extends SQLiteOpenHelper {



    static class DbEntry implements BaseColumns {

        private DbEntry() {
        }

        static final String TABLE_NAME = "equal";
        static final String COLUMN_NAME_EQUATION = "equation";
    }

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "HistoryActivity.db";
    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " +
            DbEntry.TABLE_NAME + "(" +
            DbEntry.COLUMN_NAME_EQUATION + " TEXT)";
    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " +
            DbEntry.TABLE_NAME;


    DbHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        //onCreate(db);
        db.execSQL(SQL_CREATE_ENTRIES);
    }


}