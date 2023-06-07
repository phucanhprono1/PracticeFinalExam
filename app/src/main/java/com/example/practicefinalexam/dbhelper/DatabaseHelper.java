package com.example.practicefinalexam.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.practicefinalexam.CreditClass;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {


    public DatabaseHelper(@Nullable Context context) {
        super(context, "class", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE creditclass (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, description TEXT, stu_number INTEGER)");
        sqLiteDatabase.execSQL("CREATE TABLE student (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, dob TEXT, que TEXT, year1 TEXT, creditclass_id INTEGER, FOREIGN KEY(creditclass_id) REFERENCES creditclass(id))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(("DROP TABLE IF EXISTS creditclass"));
        sqLiteDatabase.execSQL(("DROP TABLE IF EXISTS student"));
        onCreate(sqLiteDatabase);
    }
}
