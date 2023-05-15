package com.example.administrator.ld;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "test.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "staff";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String GENDER = "gender";
    public static final String DEPARTMENT = "department";
    public static final String SALARY = "salary";
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + " (" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NAME + " TEXT, " +
                GENDER + " TEXT, " +
                DEPARTMENT + " TEXT, " +
                SALARY + " FLOAT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
