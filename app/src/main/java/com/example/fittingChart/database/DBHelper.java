package com.example.fittingChart.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.fittingChart.Users;
import com.example.fittingChart.module.Data;
import com.example.fittingChart.module.FittingData;

/**
 * Created by seanz on 2016/10/10.
 */
public class DBHelper  extends SQLiteOpenHelper {
    // All Static variables
    // Database Version
    String TAG = "SQLite";
    Data data;

    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "db_user";

    // Contacts table name
    private static final String TABLE_USER = "users";
    private static final String TABLE_PUSHUP = "pushup";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_SLOGAN = "slogan";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.i("SQLite", "DBHelper.DBHelper");
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        createUser(db);
        createFitting(db, TABLE_PUSHUP);
        Log.i("SQLite", "DBHelper.onCreate");
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);

        // Create tables again
        onCreate(db);
        Log.i("SQLite", "DBHelper.onUpgrade");
    }

    public void createUser(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_SLOGAN + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
        Log.i("SQLite", "DBHelper.onCreate");
    }

    public void createFitting(SQLiteDatabase db, String tableName) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE if not exists " + tableName + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + "number" + " INTEGER,"
                + "time" + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
        Log.i("SQLite", "DBHelper.onCreate");
    }

    // Adding new item in table
    public void addItem(Users user, String table) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, user.getUsername()); // Contact Name
        values.put(KEY_SLOGAN, user.getSlogan()); // Contact Phone

        // Inserting Row
        db.insert(table, null, values);
        db.close(); // Closing database connection
        Log.i("SQLite", "DBHelper.addUser");
    }

    public void addFittingItem(FittingData data, String table) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("number", data.getNumber()); // Contact Name
        values.put("time", data.getTime()); // Contact Phone

        // Inserting Row
        db.insert(table, null, values);
        db.close(); // Closing database connection
        Log.i("SQLite", "DBHelper.addUser");
    }

    // Getting single contact
    public Users getUser(int id) {
        Log.i("SQLite", "DBHelper.getUser");
        Users user = new Users();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_USER + " WHERE id=?";
        Cursor cursor = db.rawQuery(selectQuery, new String[]{"1"});

        //cursor.moveToFirst();
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            user.setID(Integer.parseInt(cursor.getString(0)));
            user.setUsername(cursor.getString(1));
            user.setSlogan(cursor.getString(2));

            String log = "Id: " + user.getID() + " ,Name: " + user.getUsername() + " ,Slogan: " + user.getSlogan();
            Log.i("SQLite: ", log);
        }

        return user;
    }

    //Getting All Contacts
    public ArrayList<Users> getAllUsers() {
        Log.i("SQLite", "DBHelper.getAllUsers");
        ArrayList<Users> userList = new ArrayList<Users>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_USER;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Users user = new Users();
                user.setID(Integer.parseInt(cursor.getString(0)));
                user.setUsername(cursor.getString(1));
                user.setSlogan(cursor.getString(2));
                // Adding contact to list
                userList.add(user);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        // return user list
        return userList;
    }

    public ArrayList<FittingData> getAllFitting(String table) {
        Log.i("SQLite", "DBHelper.getAllFitting");
        ArrayList<FittingData> fittingDataList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + table;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                FittingData fd = new FittingData();
                fd.setID(Integer.parseInt(cursor.getString(0)));
                fd.setNumber(cursor.getInt(1));
                fd.setTime(cursor.getInt(2));
                // Adding contact to list
                fittingDataList.add(fd);
                Log.i(TAG, "getAllFitting: " + fd.getID() + fd.getNumber() + fd.getTime());
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        // return user list
        return fittingDataList;
    }

    // Updating single user
    public int updateUser(Users user) {
        Log.i("SQLite", "DBHelper.updateUser");
        int ret_value = 0;
        SQLiteDatabase db = this.getWritableDatabase();

        String sql = "update " + TABLE_USER + " set name=?,slogan=? where id = 1";
        db.execSQL(sql, new String[]{user.getUsername(), user.getSlogan()});

//        ContentValues values = new ContentValues();
//        values.put(KEY_NAME, user.getUsername());
//        values.put(KEY_SLOGAN, user.getSlogan());
//
//        // updating row
//        ret_value = db.update(TABLE_USER, values, KEY_ID + " = ?",
//                new String[] { String.valueOf(user.getID()) });
        db.close();

        return 0;
    }

    // Deleting single contact
    public void deleteUser(Users user) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER, KEY_ID + " = ?",
                new String[]{String.valueOf(user.getUsername())});
        db.close();
    }


    // Getting user Count
    public int getUserCount() {
        String countQuery = "SELECT  * FROM " + TABLE_USER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();
        db.close();

        return count;
    }

    //return table item number, 0 means no table;
    public long hasTable(String table) {
        Log.i("SQLite", "hasTable");
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            Cursor cursor = db.query(table, new String[]{"id"}, "id=?", new String[]{"1"}, null, null, null, null);
            cursor.moveToFirst();
            long n = cursor.getInt(0);
            cursor.close();
            db.close();
            return n;
        } catch (SQLiteException e) {
            return 0;
        }
    }

    public boolean tableExists(String tableName)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        if (tableName == null || db == null || !db.isOpen())
        {
            return false;
        }
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM sqlite_master WHERE type = ? AND name = ?", new String[] {"table", tableName});
        if (!cursor.moveToFirst())
        {
            cursor.close();
            return false;
        }
        int count = cursor.getInt(0);
        cursor.close();
        return count > 0;
    }
}
//    String sqlDataStore = "create table if not exists " +
//            TABLE_NAME_INFOTABLE + " ("+ BaseColumns._ID + " integer primary key autoincrement,"
//
//            + COLUMN_NAME_SITE + " text not null,"
//            + COLUMN_NAME_ADDRESS + " text not null,"
//            + COLUMN_NAME_USERNAME + " text not null,"
//            + COLUMN_NAME_PASSWORD + " text not null,"
//            + COLUMN_NAME_NOTES + " text not null);";
//
//        db.execSQL(sqlDataStore);
//                }