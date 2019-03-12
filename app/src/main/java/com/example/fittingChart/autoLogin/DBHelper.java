package com.example.fittingChart.autoLogin;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.fittingChart.Users;

/**
 * Created by seanz on 2016/10/10.
 */
public class DBHelper  extends SQLiteOpenHelper {
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "db_user";

    // Contacts table name
    private static final String TABLE_USER = "table_users";

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
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_SLOGAN + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
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

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new contact
    public void addUser(Users user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, user.getUsername()); // Contact Name
        values.put(KEY_SLOGAN, user.getSlogan()); // Contact Phone

        // Inserting Row
        db.insert(TABLE_USER, null, values);
        db.close(); // Closing database connection
        Log.i("SQLite", "DBHelper.addUser");
    }

    // Getting single contact
    public Users getUser(int id) {
        Log.i("SQLite","DBHelper.getUser");
        Users user = new Users();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_USER + " WHERE id=?";
        Cursor cursor = db.rawQuery(selectQuery, new String[]{"1"});

        //cursor.moveToFirst();
        for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext())
        {
            user.setID(Integer.parseInt(cursor.getString(0)));
            user.setUsername(cursor.getString(1));
            user.setSlogan(cursor.getString(2));

            String log = "Id: "+user.getID()+" ,Name: " + user.getUsername() + " ,Slogan: " + user.getSlogan();
            Log.i("SQLite: ", log);

        }

        return user;

        //Cursor cursor = db.query(TABLE_USER, new String[] { KEY_ID, KEY_NAME, KEY_SLOGAN }, KEY_ID + "=?",
        //        new String[] { String.valueOf(id) }, null, null, null, null);


//        if(cursor.getCount() == 0)
//            return null;
//        else
//        {
//            cursor.moveToFirst();
//            Users user = new Users(Integer.parseInt(cursor.getString(0)),
//                    cursor.getString(1), cursor.getString(2));
//            cursor.close();
//            db.close();
//            return user;
//        }
    }

    //Getting All Contacts
    public List<Users> getAllUsers() {
        Log.i("SQLite","DBHelper.getAllUsers");
        List<Users> userList = new ArrayList<Users>();
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

    // Updating single user
    public int updateUser(Users user) {
        Log.i("SQLite","DBHelper.updateUser");
        int ret_value = 0;
        SQLiteDatabase db = this.getWritableDatabase();

        String sql = "update " + TABLE_USER + " set name=?,slogan=? where id = 1";
        db.execSQL(sql,new String[]{user.getUsername(),user.getSlogan()});

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
                new String[] { String.valueOf(user.getUsername()) });
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
}