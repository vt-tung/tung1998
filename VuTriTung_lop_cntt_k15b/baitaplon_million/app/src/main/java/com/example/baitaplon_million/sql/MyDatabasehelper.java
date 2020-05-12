package com.example.baitaplon_million.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDatabasehelper extends SQLiteOpenHelper {

    public static final String DATA_NAME="tbl_cauhoi1.sqlite";
    public static final int DATA_VERSION =1;

    String createTableCauHoi="CREATE TABLE IF NOT EXISTS Cauhoi (id integer primary key autoincrement, capdo integer, noidung text, dapan text, dapansai1 text, dapansai2 text, dapansai3 text)";

    public MyDatabasehelper(@Nullable Context context) {
        super(context, DATA_NAME, null, DATA_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTableCauHoi);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}
