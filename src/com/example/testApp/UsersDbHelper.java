package com.example.testApp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.testApp.DatabaseContract.Users;

public class UsersDbHelper extends SQLiteOpenHelper {
	
	public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "USERSURVEY.db";
	
	public UsersDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
	
	public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    private static final String TEXT_TYPE = " TEXT";
    private static final String INT_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
        "CREATE TABLE " + Users.TABLE_NAME + " (" +
        Users._ID + " INTEGER PRIMARY KEY," +   
        Users.COLUMN_NAME_LNAME + TEXT_TYPE + COMMA_SEP + 
        Users.COLUMN_NAME_FNAME + TEXT_TYPE + COMMA_SEP +
        Users.COLUMN_NAME_AGE + TEXT_TYPE + COMMA_SEP +
        Users.COLUMN_NAME_EYESCOLOR + TEXT_TYPE + COMMA_SEP +
        Users.COLUMN_NAME_SKINTYPE + TEXT_TYPE + COMMA_SEP +
        Users.COLUMN_NAME_CUSTOMER + INT_TYPE +         
        " )";

    private static final String SQL_DELETE_ENTRIES =
        "DROP TABLE IF EXISTS " + Users.TABLE_NAME;


}
