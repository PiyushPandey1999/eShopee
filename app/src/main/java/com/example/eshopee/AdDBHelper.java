package com.example.eshopee;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class AdDBHelper extends SQLiteOpenHelper {

    public AdDBHelper(@Nullable Context context) {
        super(context, "PostDb", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table Details(Title TEXT primary key, Description TEXT , Price TEXT)" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Boolean insert(String title, String description, String price) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Title", title);
        contentValues.put("Description", description);
        contentValues.put("Price", price);
        long result = db.insert("Details",null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public List<String> getAllAds()
    {
        List<String> contactList = new ArrayList<>();
        String result = "";
        SQLiteDatabase db = this.getReadableDatabase();
        String select = "SELECT * FROM Details";
        Cursor cursor = db.rawQuery(select,null);
        if(cursor.moveToFirst()){
            do {
                String title = cursor.getString(0);
                String description = cursor.getString(1);
                String price = cursor.getString(2);
                result = "Title: " + title + "\nDescription: " + description + "\nPrice: " + price;
                contactList.add(result);
            }while(cursor.moveToNext());
        }
        return contactList;
    }
}