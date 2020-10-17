package com.example.sqliteexampleapp;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.ArrayAdapter;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import models.StudentsModel;
import models.UserModel;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DbVersion = 1;
    private static final String DbName = "StudentsDB";
//    private static final String DbTable = "Students";


    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String ADDRESS = "address";
    private static final String PHONE = "phone";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DbName, null, DbVersion);
    }

    private String studentsTable = "create table students"+ "(id integer primary key, dept text, versity text)";

    private String userTable = "create table DbTable"+ "(id integer primary key, name text, address text, phone text)";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(userTable);
        db.execSQL(studentsTable);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+"students");
        db.execSQL("DROP TABLE IF EXISTS "+"DbTable");
    }


    public void insertStudentsTable(StudentsModel studentsModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("id", studentsModel.getId());
        contentValues.put("dept", studentsModel.getDept());
        contentValues.put("versity", studentsModel.getVersity());

        db.insert("students", null, contentValues);
        db.close();
    }


    public List<StudentsModel> tableUserWithStudents() {
        List<StudentsModel> studentLists = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM students", null);


        if(cursor.moveToFirst()){
            do{
                StudentsModel studentsModel = new StudentsModel();
//                UserModel userModel = new UserModel();

                studentsModel.setId(Integer.parseInt(cursor.getString(0)));
                studentsModel.setDept(cursor.getString(1));
                studentsModel.setVersity(cursor.getString(2));

                studentLists.add(studentsModel);

            }while (cursor.moveToNext());
        }


        return studentLists;
    }

    public void insertData(UserModel userModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(NAME, userModel.getName());
        contentValues.put(ADDRESS, userModel.getAddress());
        contentValues.put(PHONE, userModel.getPhone());

        db.insert("DbTable", null, contentValues);
        db.close();

    }

    public List<UserModel> getUserList (){
        List<UserModel> userList = new ArrayList<UserModel>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from DbTable", null);
        if(cursor.moveToFirst()){
            do {
                UserModel user = new UserModel();

                user.setId(Integer.parseInt(cursor.getString(0)));
                user.setName(cursor.getString(1));
                user.setAddress(cursor.getString(2));
                user.setPhone(cursor.getString(3));
                userList.add(user);
            }while (cursor.moveToNext());
        }

        return userList;
    }


    public int updateData(UserModel userModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content = new ContentValues();

        content.put(NAME , userModel.getName());
        content.put(ADDRESS, userModel.getAddress());
        content.put(PHONE, userModel.getPhone());

       return db.update("DbTable", content, ID+"=?", new String[] {String.valueOf(userModel.getId())});

    }


    public void deleteData(UserModel userModel){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("DbTable", ID +"= ?", new String[] { String.valueOf(userModel.getId())} );
        db.close();
    }

}
