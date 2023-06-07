package com.example.practicefinalexam.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.practicefinalexam.CreditClass;
import com.example.practicefinalexam.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentHelper {
    private Context context;
    private DatabaseHelper db;
    public StudentHelper(Context context){
        this.context = context;
        this.db = new DatabaseHelper(context);
    }
    public void addStudenttoClass(String name,String dob,String que,String year1,int class_id){
        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("dob",dob);
        contentValues.put("que",que);
        contentValues.put("year1",year1);
        contentValues.put("creditclass_id",class_id);
        sqLiteDatabase.insert("student",null,contentValues);
        CreditClass c = new CreditClass();
        ContentValues contentValues1 = new ContentValues();
        contentValues1.put("stu_number",getStudentNumberByClass(class_id)+1);
        sqLiteDatabase.update("creditclass",contentValues1,"id=?",new String[]{String.valueOf(class_id)});
        sqLiteDatabase.close();
    }
    public int getStudentNumberByClass(int id){
        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
        int num=0;
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT stu_number FROM creditclass WHERE id ="+id,null);
        if(cursor.moveToFirst()){
            num = cursor.getInt(0);
        }
        cursor.close();
        return num;
    }

    public List<Student> getAllNamnam2(){
        List<Student> students=new ArrayList<>();
        SQLiteDatabase sql = db.getReadableDatabase();
        Cursor cursor=sql.rawQuery("SELECT * FROM student WHERE name LIKE '%NAM%' AND year1 = 2",null);
        if(cursor.moveToFirst()){
            do{
                Student s = new Student();
                s.id = cursor.getInt(0);
                s.name = cursor.getString(1);
                s.dob = cursor.getString(2);
                s.que = cursor.getString(3);
                s.year1 = cursor.getString(4);
                students.add(s);
            }while (cursor.moveToNext());
        }
        return students;
    }
    public List<Student> getAll(){
        List<Student> students=new ArrayList<>();
        SQLiteDatabase sql = db.getReadableDatabase();
        Cursor cursor=sql.rawQuery("SELECT * FROM student",null);
        if(cursor.moveToFirst()){
            do{
                Student s = new Student();
                s.id = cursor.getInt(0);
                s.name = cursor.getString(1);
                s.dob = cursor.getString(2);
                s.que = cursor.getString(3);
                s.year1 = cursor.getString(4);
                students.add(s);
            }while (cursor.moveToNext());
        }
        return students;
    }
    public List<Student> getAllByClassId(int class_id){
        List<Student> students=new ArrayList<>();
        SQLiteDatabase sql = db.getReadableDatabase();
        Cursor cursor=sql.rawQuery("SELECT * FROM student WHERE creditclass_id = "+ class_id,null);
        if(cursor.moveToFirst()){
            do{
                Student s = new Student();
                s.id = cursor.getInt(0);
                s.name = cursor.getString(1);
                s.dob = cursor.getString(2);
                s.que = cursor.getString(3);
                s.year1 = cursor.getString(4);
                students.add(s);
            }while (cursor.moveToNext());
        }
        return students;
    }
}
