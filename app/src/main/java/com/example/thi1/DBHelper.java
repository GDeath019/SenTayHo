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
    private static final String DBname = "tx.db";
    private static final int VERSION = 1;
    private static final String TABLE_NAME = "Taxi";
    private static final String ID = "_id";
    private static final String BIENSOXE = "biensoxe";
    private static final String QUANGDUONG = "quangduong";
    private static final String DONGIA = "dongia";
    private static final String GIAMGIA = "giamgia";

    public DBHelper(@Nullable Context context) {
        super(context, DBname, null, VERSION);
    }

    public static String getID() {
        return ID;
    }

    public static String getBIENSOXE() {
        return BIENSOXE;
    }

    public static String getQUANGDUONG() {
        return QUANGDUONG;
    }

    public static String getDONGIA() {
        return DONGIA;
    }

    public static String getGIAMGIA() {
        return GIAMGIA;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryTB = "CREATE TABLE "+TABLE_NAME+" ( "+
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                BIENSOXE + " TEXT NOT NULL, "+
                QUANGDUONG + " TEXT NOT NULL, "+
                DONGIA + " DOUBLE NOT NULL, "+
                GIAMGIA + " DOUBLE NOT NULL )";
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
    public long Insert(int id, String BienSoXe, Double QuangDuong, double DonGia, Double giamgia){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID ,id);
        contentValues.put(BIENSOXE,BienSoXe);
        contentValues.put(QUANGDUONG,QuangDuong);
        contentValues.put(DONGIA,DonGia);
        contentValues.put(GIAMGIA, giamgia);
        return myDB.insert(TABLE_NAME, null, contentValues);
    }

    public long Update(int id, String BienSoXe, Double QuangDuong, double DonGia, Double giamgia){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID ,id);
        contentValues.put(BIENSOXE,BienSoXe);
        contentValues.put(QUANGDUONG,QuangDuong);
        contentValues.put(DONGIA,DonGia);
        contentValues.put(GIAMGIA, giamgia);
        String where = ID +" = "+id;
        return myDB.update(TABLE_NAME, contentValues, where, null);
    }

    public long Delete(int id){
        String where = ID +" = "+id;
        return myDB.delete(TABLE_NAME, where, null);
    }

    public Cursor getAllRecord(){
        String query = "SELECT * FROM "+ TABLE_NAME +" ORDER BY " + DONGIA +" desc";
        return myDB.rawQuery(query, null);
    }

    public ArrayList<HoaDon> getAll(){
        ArrayList<HoaDon> st = new ArrayList<>();
        String where = "SELECT * FROM "+ TABLE_NAME +" ORDER BY " + DONGIA +" desc";
        Cursor cursor = myDB.rawQuery(where,null);
        if (cursor.moveToFirst()){
            do {
                HoaDon tx = new HoaDon();
                tx.setMa(cursor.getInt(0));
                tx.setBienSoXe(cursor.getString(1));
                tx.setQuangDuong(Double.parseDouble(cursor.getString(2)));
                tx.setDonGia(Double.parseDouble(cursor.getString(3)));
                tx.setGiamGia(Double.parseDouble(cursor.getString(4)));
                st.add(tx);
            }while (cursor.moveToNext());
        }
        return st;
    }
}
