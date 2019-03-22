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

import com.example.fittingChart.R;
import com.example.fittingChart.Users;
import com.example.fittingChart.module.Data;
import com.example.fittingChart.module.FittingData;
import com.example.fittingChart.module.FittingTableData;

/**
 * Created by seanz on 2016/10/10.
 */
public class DBHelper  extends SQLiteOpenHelper {
    // All Static variables
    // Database Version
    private static final int VERSION = 9;
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
        Log.i("SQLite", "DBHelper.onCreate");

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
        addFittingTableItem(db, TABLE_FITTING_OTHER, "倒立");


        createFitting(db, new FittingTableData("俯卧撑","FUWOCHENG","爽"，R.drawable.ic_home_black_24dp));
        createFitting(db, "GUIZIFUWOCHENG");
        createFitting(db, "FUQIANGFUWOCHENG");

        createFitting(db, "YALINGQIANPINGJU");
        createFitting(db, "YALINGCEPINGJU");

        createFitting(db, "YALINGFEINIAO");

        createFitting(db, "YALINGGONGERTOUJIWANJU");
        createFitting(db, "YALINGGONGSANTOUJIWANJU");

        createFitting(db, "JUANFU");
        createFitting(db, "YANGWOQIZUO");
        createFitting(db, "PINGBANZHICHENG");

        createFitting(db, "SHENDUN");
        createFitting(db, "KAOQIANGDUN");

        createFitting(db, "ZUIQIANGDANAOZHISHUZIMIGONG");

        createFitting(db, "TIZHONG");
        createFitting(db, "BOBITIAO");
        createFitting(db, "DAOLI");
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("SQLite", "DBHelper.onUpgrade");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + "FUWOCHENG");
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
    public void addUser(Users user, String table) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, user.getUsername()); // Contact Name
        values.put(KEY_SLOGAN, user.getSlogan()); // Contact Phone

        // Inserting Row
        db.insert(table, null, values);
        db.close(); // Closing database connection
        Log.i("SQLite", "DBHelper.addUser");
    }
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
    public void deleteUser(Users user) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER, KEY_ID + " = ?",
                new String[]{String.valueOf(user.getUsername())});
        db.close();
    }
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


    public boolean createFittingTable(SQLiteDatabase db, String table) {
        Log.i("SQLite", "DBHelper.createFitting");
        String CREATE_CONTACTS_TABLE = "CREATE TABLE if not exists " + table + "(id INTEGER PRIMARY KEY autoincrement,name STRING, dBname STRING, des STRING, layoutResourceID, INTEGER)";
        try {
            db.execSQL(CREATE_CONTACTS_TABLE);
            return true;
        }catch(SQLiteException e){
            return false;
        }
    }
    public ArrayList<FittingTableData> getAllFittingTable(String table){
        ArrayList<FittingTableData> list = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + table;

        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQuery,null);

            while (cursor.moveToNext()) {
                FittingTableData data = new FittingTableData();

                data.setName(cursor.getString(0)); //获取第一列的值,第一列的索引从0开始
                data.setDbName(cursor.getString(1));
                data.setDes(cursor.getString(2));
                data.setResourceID(cursor.getInt(3));
                list.add(data);
            }
            cursor.close();
            db.close();

            // return user list
            return list;
        }
        catch(SecurityException e){
            return null;
        }
    }
    public boolean addFittingTableItem(SQLiteDatabase db, String table, FittingTableData data) {
//        SQLiteDatabase db = this.getWritableDatabase();
        Log.i("SQLite", "DBHelper.addFittingTableItem");
        try {
            ContentValues values = new ContentValues();
            values.put("name", data.getName());
            values.put("dBName", data.getDbName());
            values.put("des",data.getDes());
            values.put("layoutResourceID", data.getResourceID());
            db.insert(table, null, values);
            return true;
        }catch(SQLiteException e)
        {
            return false;
        }
    }

    public boolean createFitting(SQLiteDatabase db, String table, FittingData data) {
        Log.i("SQLite", "DBHelper.createFitting");
        try{
            String CREATE_CONTACTS_TABLE = "CREATE TABLE if not exists " + table +
                "(id INTEGER PRIMARY KEY autoincrement,number INTEGER,durationTime INTEGER,localTime INTEGER,des STRING)";
            db.execSQL(CREATE_CONTACTS_TABLE);
            return true;
        }catch(SQLiteException e)
        {
            return false;
        }
    }
    public boolean addFittingItem(String table, FittingData data) {
        SQLiteDatabase db = this.getWritableDatabase();
        Log.i("SQLite", "DBHelper.addFittingItem");
        try {
            ContentValues values = new ContentValues();
            values.put("id", data.getID();
            values.put("number", data.getNumber());
            values.put("durationTime",data.getDurationTime());
            values.put("localTime", data.getLocalTime());
            values.put("des", "temp");
            db.insert(table, null, values);
            return true;
        }catch(SQLiteException e)
        {
            return false;
        }
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

    public boolean deleteTable(SQLiteDatabase db, String table){
        Log.i("SQLite", "DBHelper.deleteFittingTable");
        String cmd = "drop table " + table;
        try {
            db.execSQL(cmd);
            return true;
        }catch(SQLiteException e){
            return false;
        }
    }
    //清空整个表
    public boolean clearTable(SQLiteDatabase db, String table){
        Log.i("SQLite", "DBHelper.deleteFittingTable");
        String cmd = "delete from " + table;
        try {
            db.execSQL(cmd);
            return true;
        }catch(SQLiteException e){
            return false;
        }
    }
    public boolean deleteItem(SQLiteDatabase db, String table, int index){
        Log.i("SQLite", "DBHelper.createFitting");
        String cmd = "delete from " + table + "where id=" + index;
        try {
            db.execSQL(cmd);
            return true;
        }catch(SQLiteException e){
            return false;
        }
    }
    public int getTableCount(String table){
        try{
            SQLiteDatabase db = this.getReadableDatabase();
            String countQuery = "SELECT  * FROM " + table;
            Cursor cursor = db.rawQuery(countQuery, null);
            int count = cursor.getCount();
            cursor.close();
            db.close();
            return count;
        }
        catch(SQLiteException e)
        {
            return -1;
        }
    }
    public boolean tableExists(String tableName){
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
