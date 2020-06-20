package com.example.thi1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private SQLiteDatabase myDB;
    private static final String DBname = "ress.db";
    private static final int VERSION = 1;
    private static final String TABLE_NAME = "Restaurant";
    private static final String ID = "_id";
    private static final String NAME = "name";
    private static final String DIACHI = "DiaChi";
    private static final String RATE = "rate";

    public DBHelper(@Nullable Context context) {
        super(context, DBname, null, VERSION);
    }

    public static String getID() {
        return ID;
    }

    public static String getNAME() {
        return NAME;
    }

    public static String getDIACHI() {
        return DIACHI;
    }

    public static String getRATE() {
        return RATE;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryTB = "CREATE TABLE "+TABLE_NAME+" ( "+
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NAME + " TEXT NOT NULL, "+
                DIACHI + " TEXT NOT NULL, "+
                RATE + " DOUBLE NOT NULL )";
        db.execSQL(queryTB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void openDB(){
        myDB = getWritableDatabase();
    }

    public void closeDB(){
        if (myDB!=null && myDB.isOpen()){
            myDB.close();
        }
    }
    public long Insert(int id, String name, String diachi, double rate){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID ,id);
        contentValues.put(NAME,name);
        contentValues.put(DIACHI,diachi);
        contentValues.put(RATE,rate);
        return myDB.insert(TABLE_NAME, null, contentValues);
    }

    public long Update(int id, String name, String diachi, double rate){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID ,id);
        contentValues.put(NAME,name);
        contentValues.put(DIACHI,diachi);
        contentValues.put(RATE,rate);
        String where = ID +" = "+id;
        return myDB.update(TABLE_NAME, contentValues, where, null);
    }

    public long Delete(int id){
        String where = ID +" = "+id;
        return myDB.delete(TABLE_NAME, where, null);
    }

    public Cursor getAllRecord(){
        String query = "SELECT * FROM "+ TABLE_NAME +" ORDER BY " + NAME +" asc";
        return myDB.rawQuery(query, null);
    }

    public ArrayList<restaurant> getAll(){
        ArrayList<restaurant> st = new ArrayList<>();
        String where = "SELECT * FROM "+ TABLE_NAME +" ORDER BY " + NAME +" asc";
        Cursor cursor = myDB.rawQuery(where,null);
        if (cursor.moveToFirst()){
            do {
                restaurant res = new restaurant();
                res.setMa(cursor.getInt(0));
                res.setName(cursor.getString(1));
                res.setLocal(cursor.getString(2));
                res.setRate(cursor.getDouble(3));
                st.add(res);
            }while (cursor.moveToNext());
        }
        return st;
    }
}
