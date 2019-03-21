package com.example.fittingChart.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
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
    private static final int VERSION = 8;
    String TAG = "SQLite";
    Data data;


    // Database Name
    private static final String DATABASE_NAME = "db_user";

    // Contacts table name
    private static final String TABLE_USER = "users";

    private static final String TABLE_FITTING_BREAST = "fitting_breast";
    private static final String TABLE_FITTING_SHOULDER = "fitting_shoulder";
    private static final String TABLE_FITTING_BACK = "fitting_back";
    private static final String TABLE_FITTING_ARM = "fitting_arm";
    private static final String TABLE_FITTING_BELLY = "fitting_belly";
    private static final String TABLE_FITTING_LEG = "fitting_leg";
    private static final String TABLE_FITTING_BRAIN = "fitting_brain";
    private static final String TABLE_FITTING_OTHER = "fitting_other";


    private static final String TABLE_PUSHUP = "pushup";
    private static final String TABLE_HANDSTAND = "handStand";
    private static final String TABLE_BURPEE = "burpee";
    private static final String TABLE_JUMPING_JACK = "jumpingJack";//开合跳




    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_SLOGAN = "slogan";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        Log.i("SQLite", "DBHelper.DBHelper");
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        createUser(db);

        createFittingTable(db, TABLE_FITTING_ARM);
        createFittingTable(db, TABLE_FITTING_BACK);
        createFittingTable(db, TABLE_FITTING_BELLY);
        createFittingTable(db, TABLE_FITTING_BRAIN);
        createFittingTable(db, TABLE_FITTING_LEG);
        createFittingTable(db, TABLE_FITTING_OTHER);
        createFittingTable(db, TABLE_FITTING_SHOULDER);
        createFittingTable(db, TABLE_FITTING_BREAST);

        addFittingTableItem(db, TABLE_FITTING_BREAST, "俯卧撑");
        addFittingTableItem(db, TABLE_FITTING_BREAST, "跪姿俯卧撑");
        addFittingTableItem(db, TABLE_FITTING_BREAST, "扶墙俯卧撑");

        addFittingTableItem(db, TABLE_FITTING_SHOULDER, "哑铃前平举");
        addFittingTableItem(db, TABLE_FITTING_SHOULDER, "哑铃侧平举");

        addFittingTableItem(db, TABLE_FITTING_BACK, "哑铃飞鸟");

        addFittingTableItem(db, TABLE_FITTING_ARM, "哑铃肱二头肌弯举");
        addFittingTableItem(db, TABLE_FITTING_ARM, "哑铃肱二头肌弯举");

        addFittingTableItem(db, TABLE_FITTING_BELLY, "卷腹");
        addFittingTableItem(db, TABLE_FITTING_BELLY, "仰卧起坐");
        addFittingTableItem(db, TABLE_FITTING_BELLY, "平板支撑");

        addFittingTableItem(db, TABLE_FITTING_LEG, "深蹲");
        addFittingTableItem(db, TABLE_FITTING_LEG, "靠墙蹲");

        addFittingTableItem(db, TABLE_FITTING_BRAIN, "最强大脑之数字迷宫");

        addFittingTableItem(db, TABLE_FITTING_OTHER, "体重");
        addFittingTableItem(db, TABLE_FITTING_OTHER, "波比跳");

        Log.i("SQLite", "DBHelper.onCreate");
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("SQLite", "DBHelper.onUpgrade");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PUSHUP);
        // Create tables again
        onCreate(db);
    }

    public void createUser(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_SLOGAN + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
        Log.i("SQLite", "DBHelper.createUser");
    }

    public void createFitting(SQLiteDatabase db, String tableName) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE if not exists " + tableName + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + "number" + " INTEGER,"
                + "durationTime" + " INTEGER," + "localTime" + " INTEGER" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
        Log.i("SQLite", "DBHelper.createFitting");
    }

    public void createFittingTable(SQLiteDatabase db, String tableName) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE if not exists " + tableName + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + "name" + " STRING" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
        Log.i("SQLite", "DBHelper.createFitting");
    }

    // Adding new item in fitting
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

    public void addFittingItem(String table, FittingData data) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("number", data.getNumber());
        values.put("durationTime", data.getDurationTime());
        values.put("localTime", data.getLocalTime());
        // Inserting Row
        db.insert(table, null, values);
        db.close(); // Closing database connection
        Log.i("SQLite", "DBHelper.addUser");
    }

    public void addFittingTableItem(SQLiteDatabase db, String table, String item) {
//        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", item);

        // Inserting Row
        db.insert(table, null, values);
        Log.i("SQLite", "DBHelper.addFittingTableItem");
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
        cursor.close();
        db.close();
        return user;
    }

    //Getting All Contacts
    public ArrayList<Users> getAllUsers() {
        Log.i("SQLite", "DBHelper.getAllUsers");
        ArrayList<Users> userList = new ArrayList<Users>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_USER;

        SQLiteDatabase db = this.getReadableDatabase();
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

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                FittingData fd = new FittingData();
                fd.setID(Integer.parseInt(cursor.getString(0)));
                fd.setNumber(cursor.getInt(1));
                fd.setDurationTime(cursor.getInt(2));
                fd.setLocalTime(cursor.getInt(3));

                // Adding contact to list
                fittingDataList.add(fd);
                Log.i(TAG, "getAllFitting: " + fd.getID() + ":" + fd.getNumber() + ":" + fd.getDurationTime() + ":" + fd.getLocalTime());
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