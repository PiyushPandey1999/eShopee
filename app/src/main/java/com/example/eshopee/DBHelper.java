package com.example.eshopee;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "eShopee", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS UserDetails (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, fullName VARCHAR, userName VARCHAR, password VARCHAR, phone_number VARCHAR, address VARCHAR)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP Table if EXISTS UserDetails");
    }

    public Boolean insertUserData(String fullName, String userName, String password, String phoneNo, String address) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("fullName", fullName);
        values.put("userName", userName);
        values.put("password", password);
        values.put("phone_number", phoneNo);
        values.put("address", address);
        long result = db.insert("UserDetails", null, values);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getData(String phoneNumberHolder) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM UserDetails WHERE phone_number = "+phoneNumberHolder+"", null);
        return result;
    }

    public Boolean checkLoginDetails(String userNameHolder, String passwordHolder) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM UserDetails WHERE userName = ? AND password = ?", new String[] {userNameHolder, passwordHolder}, null);
        if(result.getCount() > 0)
            return true;
        else
            return false;
    }

    public boolean checkUsername(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM userDetails WHERE username = ?", new String[] {username});

        if(result.getCount() > 0)
            return true;
        else
            return false;
    }

    public boolean updatePassword(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("password", password);
        long result = db.update("userDetails", values, "username = ?", new String[] {username});
        if(result == -1)
            return false;
        else
            return true;
    }

    public String[] getUserDetails(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM userDetails WHERE username = ? AND password = ?", new String[] {username, password}, null);
        if(result.getCount() > 0) {
            result.moveToFirst();
            ArrayList<String> details = new ArrayList<String>();

            while(!result.isAfterLast()) {
                details.add(result.getString(result.getColumnIndex("fullName")));
                details.add(result.getString(result.getColumnIndex("userName")));
                details.add(result.getString(result.getColumnIndex("password")));
                details.add(result.getString(result.getColumnIndex("phone_number")));
                details.add(result.getString(result.getColumnIndex("address")));
                result.moveToNext();
            }
            result.close();
            return details.toArray(new String[details.size()]);
        }
        else
            return (new String[2]);
    }
}