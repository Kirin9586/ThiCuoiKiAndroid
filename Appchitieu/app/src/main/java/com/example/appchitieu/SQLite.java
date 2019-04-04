package com.example.appchitieu;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class SQLite extends SQLiteOpenHelper {
    private static String TABLE_Name = "user";
    private static String TABLE_a_id="ID";
    private static String TABLE_a_sotien = "Sotien";
    private static String TABLE_a_loaichitieu = "Loaichitieu";
    private static String TABLE_a_diadiemchitieu ="Diadiem";
    private static String TABLE_a_thoigianchitieu = "Thoigian";
    private static String TABLE_a_ghichu ="Ghichu";


    private static String TABLE_NameLogin ="Login";
    private static String TABLE_a_id1 ="ID";
    private static String TABLE_a_user = "username";
    private static String TABLE_a_pass ="password";
    private static String TABLE_a_sodu ="sodu";
    private SQLiteDatabase database;
    private SQLiteDatabase databaser;
    private static final String DATABASE_NAME = "SQL.db";
    public SQLite(Context context) {
        super(context, DATABASE_NAME, null,1);
        database = this.getWritableDatabase();
        databaser = getReadableDatabase();
    }
    public void Insert(int sotien,String loai,String diadiem, String thoigian,String  ghichu) {
        String insert = String.format("insert into %s ( %s , %s , %s , %s , %s) values (%s, '%s', '%s', '%s', '%s')", TABLE_Name, TABLE_a_sotien,TABLE_a_loaichitieu,TABLE_a_diadiemchitieu,TABLE_a_thoigianchitieu,TABLE_a_ghichu, sotien, loai, diadiem, thoigian, ghichu);
        database.execSQL(insert);
    }
    public void InsertLogin(String user,String pass,int sodu){
        String query = String.format("insert into %s ( %s , %s , %s ) values ('%s', '%s' , %s )", TABLE_NameLogin,TABLE_a_user,TABLE_a_pass,TABLE_a_sodu,user,pass,sodu);
        database.execSQL(query);
    }
    public List<User> getAll() {
        List<User> getUser = new ArrayList<>();
        String query = "select * from  " + TABLE_Name;
        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                User user = new User();
                user.setSotienchitieu(cursor.getInt(cursor.getColumnIndex(TABLE_a_sotien)));
                user.setLoai(cursor.getString(cursor.getColumnIndex(TABLE_a_loaichitieu)));
                user.setDiadiem(cursor.getString(cursor.getColumnIndex(TABLE_a_diadiemchitieu)));
                user.setThoigian(cursor.getString(cursor.getColumnIndex(TABLE_a_thoigianchitieu)));
                user.setGhichu(cursor.getString(cursor.getColumnIndex(TABLE_a_ghichu)));
                getUser.add(user);
            }
            cursor.close();
        }
        return getUser;
    }
    public boolean CheckData(String username, String password) {
        String query = "select *from " + TABLE_NameLogin+ " where " + TABLE_a_user + "='" + username + "' and " + TABLE_a_pass + "='" + password + "'";
        Cursor cursor = databaser.rawQuery(query, null);
        if (cursor.getCount() > 0) {
           return true;
        }
        cursor.close();
        return false;
    }
    public int GetIDLogin(String user,String pass) {
        String mquery = "select *from " + TABLE_NameLogin + " where " + TABLE_a_user + "='" + user + "' and " + TABLE_a_pass + "='" + pass + "'";
        Cursor cursor = database.rawQuery(mquery, null);
        cursor.moveToFirst();
        int ID = cursor.getInt(0);
        cursor.close();
        return ID;
    }
    public  void Update(int ID, String sodu) {
        String query = "update " + TABLE_NameLogin + " set " + TABLE_a_sodu + "= '" + sodu + "' where " + TABLE_a_id + "=" + ID;
        database.execSQL(query);
    }
    public int Sum(int month,int year) throws ParseException {

       String query = String.format("SELECT * FROM %s  ",TABLE_Name);
       Cursor cursor = database.rawQuery(query,null);
        int Tongchitieu= 0;
       for(int i = 0;i < cursor.getCount();i++){
           cursor.moveToNext();
           Date date = new SimpleDateFormat("dd/MM/yyyy").parse(cursor.getString(4));
           if(date.getYear() == year && date.getMonth() == month){
               Tongchitieu+=cursor.getInt(1);
           }
           else if(date.getYear() == year && date.getMonth() > month){
               Tongchitieu+=0;
           }
           else if(date.getYear() == year && date.getMonth()<month){
               Tongchitieu+=0;
           }
       }
       cursor.close();
       return Tongchitieu;
    }

    public int GetSodu(int id) {
        String mquery = "select *from " + TABLE_NameLogin+ " where " + TABLE_a_id1 + "='" + id +"'";
        Cursor cursor = database.rawQuery(mquery,null);
        cursor.moveToFirst();
        int sodu = cursor.getInt(3);
        cursor.close();
        return sodu;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String mquery = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY NOT NULL, %s INT, %s TEXT, %s TEXT, %s TEXT, %s TEXT)",TABLE_Name, TABLE_a_id, TABLE_a_sotien, TABLE_a_loaichitieu, TABLE_a_diadiemchitieu, TABLE_a_thoigianchitieu, TABLE_a_ghichu);
        db.execSQL(mquery);
        String query = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY NOT NULL, %s TEXT, %s TEXT, %s INT default(10000))", TABLE_NameLogin, TABLE_a_id1, TABLE_a_user, TABLE_a_pass, TABLE_a_sodu);
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
