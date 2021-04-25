package com.example.colan_infotech.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "colen_db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SqliteHelper.CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + SqliteHelper.TABLE_NAME);
        onCreate(db);
    }

    public long insertAuth(String productid,String name,String comment,String title) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SqliteHelper.COLUMN__PRODUCT_ID, productid);
        values.put(SqliteHelper.COLUMN__NAME, name);
        values.put(SqliteHelper.COLUMN__COMMENT, comment);
        values.put(SqliteHelper.COLUMN__TITLE, title);
        long id = db.insert(SqliteHelper.TABLE_NAME, null, values);
        db.close();
        return id;
    }

    public SqliteHelper getAllAuth() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor resultSet = db.rawQuery("SELECT * FROM " + SqliteHelper.TABLE_NAME, null);
        List<String> products=new ArrayList<>();
        List<String> quantity=new ArrayList<>();
        List<String> price=new ArrayList<>();
        List<String> image=new ArrayList<>();
        List<Integer> id=new ArrayList<>();
        for (resultSet.moveToFirst(); !resultSet.isAfterLast(); resultSet.moveToNext()) {
            Log.d("vino",resultSet.getString(0)+resultSet.getString(1)+resultSet.getString(2)+resultSet.getString(3)+resultSet.getString(4));
            products.add(resultSet.getString(1));
            quantity.add(resultSet.getString(2));
            price.add(resultSet.getString(3));
            image.add(resultSet.getString(4));
        }
        db.close();
        return new SqliteHelper(id,products,quantity,price,image);
    }






    public int getCount() {
        String countQuery = "SELECT  * FROM " + SqliteHelper.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }


    public void deleteNote(int position) {
        Log.d("vino","yes");
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(SqliteHelper.TABLE_NAME, SqliteHelper.COLUMN_ID + " = ?",
                new String[]{String.valueOf(position)});
        db.close();
    }


}