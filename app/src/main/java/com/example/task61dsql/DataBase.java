package com.example.task61dsql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBase extends SQLiteOpenHelper {

    public static final String DBNAME = "FoodWaste.db";

    public DataBase(Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase myDb) {
        myDb.execSQL("create Table users(name TEXT, username TEXT primary key, phone TEXT, address TEXT, password TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase myDb, int oldVersion, int newVersion) {
        myDb.execSQL("drop Table if exists users");
    }

    public boolean insertData(String name, String username, String number, String address, String password){
        SQLiteDatabase myDb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("username", username);
        contentValues.put("phone", number);
        contentValues.put("address", address);
        contentValues.put("password", password);

        long result = myDb.insert("users", null, contentValues);

        if(result == -1){
            return false;
        }
        else {
            return true;
        }
    }

    public Boolean checkUserName(String username){
        SQLiteDatabase myDb = this.getWritableDatabase();
        Cursor cursor = myDb.rawQuery("select * from users where username = ?", new String[]{username});
        if (cursor.getCount() > 0){
            return true;
        }
        else {
            return false;
        }
    }

    public Boolean checkUserNamePassword(String username, String password){
        SQLiteDatabase myDb = this.getWritableDatabase();
        Cursor cursor = myDb.rawQuery("select * from users where username = ? and password = ?", new String[]{username,password});
        if (cursor.getCount() > 0){
            return true;
        }
        else {
            return false;
        }
    }
}
