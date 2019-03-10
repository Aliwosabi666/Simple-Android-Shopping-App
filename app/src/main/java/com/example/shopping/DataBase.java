package com.example.shopping;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "shopping";
    private static final int DATABASE_VERSION = 1;
    public static final String electronics = "electronics";
    public static final String stationary = "stationary";
    public static final String furniture = "furniture";
    public static final String login = "login";

    private static final String CREATE_TABLE_ELEC = "CREATE TABLE IF NOT EXISTS " + electronics + "(elec_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, price TEXT )";
    private static final String CREATE_TABLE_FURN = "CREATE TABLE IF NOT EXISTS " + furniture + "(func_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT , price TEXT )";
    private static final String CREATE_TABLE_STAT = "CREATE TABLE IF NOT EXISTS " + stationary + "(stat_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT , price TEXT )";
    private static final String CREATE_TABLE_LOGIN = "CREATE TABLE IF NOT EXISTS " + login + "(login_id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT , password TEXT)";

    private static final String DELETE_TABLE_ELEC="DROP TABLE IF EXISTS " + electronics;
    private static final String DELETE_TABLE_FURN="DROP TABLE IF EXISTS " + furniture;
    private static final String DELETE_TABLE_STAT="DROP TABLE IF EXISTS " + stationary;
    private static final String DELETE_TABLE_LOGIN="DROP TABLE IF EXISTS " + login;

    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_ELEC);
        db.execSQL(CREATE_TABLE_FURN);
        db.execSQL(CREATE_TABLE_STAT);
        db.execSQL(CREATE_TABLE_LOGIN);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

      db.execSQL(DELETE_TABLE_ELEC);
      db.execSQL(DELETE_TABLE_FURN);
      db.execSQL(DELETE_TABLE_STAT);
      db.execSQL(DELETE_TABLE_LOGIN);
        //Create tables again
        onCreate(db);
    }

    public void insertDataIntoElec(String name,String price ){

        SQLiteDatabase db = this.getWritableDatabase();  // Open the database for writing
        db.beginTransaction(); // Start the transaction.
        ContentValues values;
        try
        {
            values = new ContentValues();
            values.put("name",name);
            values.put("price",price);
            long i = db.insert(electronics, null, values);
            Log.i("Insert", i + "");
            db.setTransactionSuccessful();
        }
        catch (SQLiteException e)
        {
            e.printStackTrace();
        }
        finally
        {
            db.endTransaction();
            db.close();
        }
    }

    public void insertDataIntoStat(String name,String price ){

        SQLiteDatabase db = this.getWritableDatabase();  // Open the database for writing
        db.beginTransaction(); // Start the transaction.
        ContentValues values;
        try
        {
            values = new ContentValues();
            values.put("name",name);
            values.put("price",price);
            long i = db.insert(stationary, null, values);
            Log.i("Insert", i + "");
            db.setTransactionSuccessful();
        }
        catch (SQLiteException e)
        {
            e.printStackTrace();
        }
        finally
        {
            db.endTransaction();
            db.close();
        }
    }

    public void insertDataIntoFurn(String name,String price ){

        SQLiteDatabase db = this.getWritableDatabase();  // Open the database for writing
        db.beginTransaction(); // Start the transaction.
        ContentValues values;
        try
        {
            values = new ContentValues();
            values.put("name",name);
            values.put("price",price);
            long i = db.insert(furniture, null, values);
            Log.i("Insert", i + "");
            db.setTransactionSuccessful();
        }
        catch (SQLiteException e)
        {
            e.printStackTrace();
        }
        finally
        {
            db.endTransaction();
            db.close();
        }
    }
    public void insertDataIntoLogin(String username,String password ){

        SQLiteDatabase db = this.getWritableDatabase();  // Open the database for writing
        db.beginTransaction(); // Start the transaction.
        ContentValues values;
        try
        {
            values = new ContentValues();
            values.put("username",username);
            values.put("password",password);
            long i = db.insert(login, null, values);
            Log.i("Insert", i + "");
            db.setTransactionSuccessful();
        }
        catch (SQLiteException e)
        {
            e.printStackTrace();
        }
        finally
        {
            db.endTransaction();
            db.close();
        }
    }
}
