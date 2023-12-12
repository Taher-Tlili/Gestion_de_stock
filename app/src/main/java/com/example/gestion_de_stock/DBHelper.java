package com.example.gestion_de_stock;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME ="stock";

    public DBHelper( Context context) {

        super(context, "stock", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS USERS (User varchar primary key,password varchar,repassword varchar);");
        db.execSQL("create Table Userdetails(nom TEXT primary key, famille TEXT, quantite INTEGER, dp DATE)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int ii) {
        db.execSQL("drop Table if exists USERS");
        db.execSQL("drop Table if exists Userdetails");
    }
    public Boolean insertData(String User,String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("User",User);
        contentValues.put("password", password);
        long result = db.insert("users",null,contentValues);
        if (result==1) return false;
        else
            return true;

    }
    public Boolean checkusername(String User){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from USERS where User = ?", new String[] {User});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }
    public Boolean checkusernamepassword(String User, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from USERS where User = ? and password = ?", new String[]{User,password});
        if (cursor.getCount()>0)
            return  true;
        else
            return false;
    }
    public Boolean insertuserdata(String famille, String nom, String quantite, String dp)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("famille", famille);
        contentValues.put("nom", nom);
        contentValues.put("quantite", quantite);
        contentValues.put("dp", dp);
        long result = DB.insert("Userdetails",null,contentValues);
        if (result==-1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    public Cursor getdata()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails",null);
        return cursor;
    }
    }

