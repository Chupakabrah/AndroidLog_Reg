package com.example.forth.loginapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB extends SQLiteOpenHelper {

    public static final String DB_Name = "UsersDatabase.db";
    public static final String User_TableName = "UserTable";
    public static final String User_Col_1 = "ID";
    public static final String User_Col_2 = "UserName";
    public static final String User_Col_3 = "PassWord";
    public static final String User_Col_4 = "Full_UserName";

    DB(Context context){
        super(context, DB_Name,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + User_TableName + "(ID integer primary key autoincrement, UserName text unique, PassWord text, Full_UserName text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + User_TableName);
    }

    public Cursor getData(String tableName){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("select * from" + tableName,null);
        return data;
    }

    public boolean Reg(String userName, String passWord, String fullName){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(User_Col_2, userName);
        contentValues.put(User_Col_3, passWord);
        contentValues.put(User_Col_4, fullName);

        long result = db.insert(User_TableName,null,contentValues);

        if (result == -1){
            return false;
        }
        else {
            return true;
        }
    }
}
