package com.example.eshopee;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class AdDBHelper extends SQLiteOpenHelper {

    public AdDBHelper(@Nullable Context context) {
        super(context, "PostDb", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table Details(title TEXT primary key, Description TEXT , Price TEXT)" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Boolean insert(String title, String Description, String Price) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("Description", Description);
        contentValues.put("Price", Price);
        long result = db.insert("Details",null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }
}