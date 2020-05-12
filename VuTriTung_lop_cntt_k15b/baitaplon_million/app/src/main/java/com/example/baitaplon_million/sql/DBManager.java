package com.example.baitaplon_million.sql;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.baitaplon_million.CauHoi;

import java.util.ArrayList;
import java.util.HashMap;

public class DBManager {
    private Context context;
    private SQLiteDatabase database;
    private MyDatabasehelper dbHelper;

    public DBManager(Context c) {
        this.context = c;
    }

    public DBManager open() throws SQLException {
        this.dbHelper = new MyDatabasehelper(this.context);
        this.database = this.dbHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        this.dbHelper.close();
    }

    public ArrayList<ArrayList<CauHoi>> getcauhoi(){
        ArrayList< ArrayList<CauHoi>> arr = new ArrayList<>();
        HashMap<Integer, ArrayList<CauHoi>> arrs= new HashMap<>();
        Cursor cursor = this.database.query("Cauhoi",
                new String[]{
                        "capdo",
                        "noidung",
                        "dapan",
                        "dapansai1",
                        "dapansai2",
                        "dapansai3"}, null, null, null, null, null);

        while (cursor.moveToNext()){
            int capdo = cursor.getInt(cursor.getColumnIndex("capdo"));
            ArrayList<CauHoi> arrCapDo = arrs.get(capdo);
            if (arrCapDo==null){
                arrCapDo= new ArrayList<>();
            }
            arrCapDo.add(taoCauHoi(
                    cursor.getString(cursor.getColumnIndex("noidung")),
                    cursor.getString(cursor.getColumnIndex("dapan")),
                    cursor.getString(cursor.getColumnIndex("dapansai1")),
                    cursor.getString(cursor.getColumnIndex("dapansai2")),
                    cursor.getString(cursor.getColumnIndex("dapansai3"))
            ));
            arrs.put(capdo,arrCapDo);
        }
        for (Integer key: arrs.keySet()){
            arr.add(arrs.get(key));
        }
        return arr;
    }

    public CauHoi taoCauHoi(String s1, String s2, String s3, String s4, String s5){
        CauHoi setCH = new CauHoi();
        setCH.setNoiDung(s1);
        setCH.setDapAnDung(s2);
        setCH.setDapAnSai1(s3);
        setCH.setDapAnSai2(s4);
        setCH.setDapAnSai3(s5);
        return setCH;
    }
}
