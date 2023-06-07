package com.example.practicefinalexam.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.practicefinalexam.CreditClass;

import java.util.ArrayList;
import java.util.List;

public class ClassHelper {
    private Context context;
    private DatabaseHelper db;
    public ClassHelper(Context context){
        this.context = context;
        this.db = new DatabaseHelper(context);
    }
    public void insertClass(String name,String description){
        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("description",description);
        contentValues.put("stu_number",(int)0);
        sqLiteDatabase.insert("creditclass",null,contentValues);
        sqLiteDatabase.close();
    }
    public int getStudentNumberByClass(int id){
        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
        int num=0;
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT COUNT(*) FROM creditclass WHERE id ="+id,null);
        if(cursor.moveToFirst()){
            num = cursor.getInt(0);
        }
        cursor.close();
        return num;
    }
    public List<CreditClass> getAll(){
        List<CreditClass> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = db.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM creditclass",null);
        if (cursor.moveToFirst()){
            do {
                CreditClass c = new CreditClass();
                c.id = cursor.getInt(0);
                c.name = cursor.getString(1);
                c.description = cursor.getString(2);
                c.stuNumber = cursor.getInt(3);
                list.add(c);
            }while (cursor.moveToNext());
        }
        cursor.close();
        sqLiteDatabase.close();
        return list;

    }
    public List<CreditClass> getDesc(){
        List<CreditClass> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = db.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM creditclass ORDER BY stu_number desc",null);
        if (cursor.moveToFirst()){
            do {
                CreditClass c = new CreditClass();
                c.id = cursor.getInt(0);
                c.name = cursor.getString(1);
                c.description = cursor.getString(2);
                c.stuNumber = cursor.getInt(3);
                list.add(c);
            }while (cursor.moveToNext());
        }
        cursor.close();
        sqLiteDatabase.close();
        return list;

    }
}
