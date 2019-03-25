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
import com.example.fittingChart.module.ShowTable;

/**
 * Created by seanz on 2016/10/10.
 */
public class DBHelper  extends SQLiteOpenHelper {
    // All Static variables
    // Database Version
    private static final int VERSION = 2;
    String TAG = "SQLite";
    Data data;
    private Context context;


    public static SQLiteDatabase dbInstance;

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
    private static final String TABLE_SHOW = "fitting_show";



    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_SLOGAN = "slogan";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.context = context;
        Log.i("SQLite", "DBHelper.DBHelper");
    }

    public void openDatabase() {
        dbInstance = this.getWritableDatabase();
    }
    public void closeDatabase(){
        dbInstance.close();
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        createUser();
        Log.i("SQLite", "DBHelper.onCreate");

        createFittingTable(TABLE_FITTING_ARM);
        createFittingTable(TABLE_FITTING_BACK);
        createFittingTable(TABLE_FITTING_BELLY);
        createFittingTable(TABLE_FITTING_BRAIN);
        createFittingTable(TABLE_FITTING_LEG);
        createFittingTable(TABLE_FITTING_OTHER);
        createFittingTable(TABLE_FITTING_SHOULDER);
        createFittingTable(TABLE_FITTING_BREAST);

        addFittingTableItem(TABLE_FITTING_BREAST, new FittingTableData("俯卧撑","FUWOCHENG","abc",R.drawable.ic_launcher_foreground));
        addFittingTableItem(TABLE_FITTING_BREAST, new FittingTableData("跪姿俯卧撑","GUIZIFUWOCHENG","abc",R.drawable.ic_launcher_foreground));
        addFittingTableItem(TABLE_FITTING_BREAST, new FittingTableData("扶墙俯卧撑","FUQIANGFUWOCHENG","abc",R.drawable.ic_launcher_foreground));

        addFittingTableItem(TABLE_FITTING_SHOULDER, new FittingTableData("哑铃前平举","YALINGQIANPINGJU","abc",R.drawable.ic_launcher_foreground));
        addFittingTableItem(TABLE_FITTING_SHOULDER, new FittingTableData("哑铃侧平举","YALINGCEPINGJU","abc",R.drawable.ic_launcher_foreground));

        addFittingTableItem(TABLE_FITTING_BACK, new FittingTableData("哑铃飞鸟","YALINGFEINIAO","abc",R.drawable.ic_launcher_foreground));

        addFittingTableItem(TABLE_FITTING_ARM, new FittingTableData("哑铃肱二头肌弯举","YALINGGONGERTOUJIWANJU","abc",R.drawable.ic_launcher_foreground));
        addFittingTableItem(TABLE_FITTING_ARM, new FittingTableData("哑铃肱二头肌弯举","YALINGGONGSANTOUJIWANJU","abc",R.drawable.ic_launcher_foreground));

        addFittingTableItem(TABLE_FITTING_BELLY, new FittingTableData("卷腹","JUANFU","abc",R.drawable.ic_launcher_foreground));
        addFittingTableItem(TABLE_FITTING_BELLY, new FittingTableData("仰卧起坐","YANGWOQIZUO","abc",R.drawable.ic_launcher_foreground));
        addFittingTableItem(TABLE_FITTING_BELLY, new FittingTableData("平板支撑","PINGBANZHICHENG","abc",R.drawable.ic_launcher_foreground));

        addFittingTableItem(TABLE_FITTING_LEG, new FittingTableData("深蹲","SHENDUN","abc",R.drawable.ic_launcher_foreground));
        addFittingTableItem(TABLE_FITTING_LEG, new FittingTableData("靠墙蹲","KAOQIANGDUN","abc",R.drawable.ic_launcher_foreground));

        addFittingTableItem(TABLE_FITTING_BRAIN, new FittingTableData("最强大脑之数字迷宫","ZUIQIANGDANAOZHISHUZIMIGONG","abc",R.drawable.ic_launcher_foreground));

        addFittingTableItem(TABLE_FITTING_OTHER, new FittingTableData("体重","TIZHONG","abc",R.drawable.ic_launcher_foreground));
        addFittingTableItem(TABLE_FITTING_OTHER, new FittingTableData("波比跳","BOBITIAO","abc",R.drawable.ic_launcher_foreground));
        addFittingTableItem(TABLE_FITTING_OTHER, new FittingTableData("倒立","DAOLI","abc",R.drawable.ic_launcher_foreground));


        createFitting("FUWOCHENG");
        createFitting("GUIZIFUWOCHENG");
        createFitting("FUQIANGFUWOCHENG");

        createFitting("YALINGQIANPINGJU");
        createFitting("YALINGCEPINGJU");

        createFitting("YALINGFEINIAO");

        createFitting("YALINGGONGERTOUJIWANJU");
        createFitting("YALINGGONGSANTOUJIWANJU");

        createFitting("JUANFU");
        createFitting("YANGWOQIZUO");
        createFitting("PINGBANZHICHENG");

        createFitting("SHENDUN");
        createFitting("KAOQIANGDUN");

        createFitting("ZUIQIANGDANAOZHISHUZIMIGONG");

        createFitting("TIZHONG");
        createFitting("BOBITIAO");
        createFitting("DAOLI");
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


    public void createUser() {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_SLOGAN + " TEXT" + ")";
        dbInstance.execSQL(CREATE_CONTACTS_TABLE);
        Log.i("SQLite", "DBHelper.createUser");
    }
    public void addUser(Users user, String table) {
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, user.getUsername()); // Contact Name
        values.put(KEY_SLOGAN, user.getSlogan()); // Contact Phone

        // Inserting Row
        dbInstance.insert(table, null, values);
        dbInstance.close(); // Closing database connection
        Log.i("SQLite", "DBHelper.addUser");
    }
    public int updateUser(Users user) {
        Log.i("SQLite", "DBHelper.updateUser");
        int ret_value = 0;
        String sql = "update " + TABLE_USER + " set name=?,slogan=? where id = 1";
        dbInstance.execSQL(sql, new String[]{user.getUsername(), user.getSlogan()});
        return 0;
    }
    public void deleteUser(Users user) {
        dbInstance.delete(TABLE_USER, KEY_ID + " = ?", new String[]{String.valueOf(user.getUsername())});
    }
    public Users getUser(int id) {
        Log.i("SQLite", "DBHelper.getUser");
        Users user = new Users();

        String selectQuery = "SELECT * FROM " + TABLE_USER + " WHERE id=?";
        Cursor cursor = dbInstance.rawQuery(selectQuery, new String[]{"1"});

        //cursor.moveToFirst();
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            user.setID(Integer.parseInt(cursor.getString(0)));
            user.setUsername(cursor.getString(1));
            user.setSlogan(cursor.getString(2));

            String log = "Id: " + user.getID() + " ,Name: " + user.getUsername() + " ,Slogan: " + user.getSlogan();
            Log.i("SQLite: ", log);
        }
        cursor.close();
        return user;
    }
    public ArrayList<Users> getAllUsers() {
        Log.i("SQLite", "DBHelper.getAllUsers");
        ArrayList<Users> userList = new ArrayList<Users>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_USER;
        Cursor cursor = dbInstance.rawQuery(selectQuery, null);

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

        // return user list
        return userList;
    }


    public boolean createFittingTable(String table) {
        Log.i("SQLite", "DBHelper.createFitting");
        String CREATE_CONTACTS_TABLE = "CREATE TABLE if not exists " + table + "(id INTEGER PRIMARY KEY autoincrement,name STRING, dBname STRING, des STRING, layoutResourceID, INTEGER)";
        try {
            dbInstance.execSQL(CREATE_CONTACTS_TABLE);
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
            Cursor cursor = dbInstance.rawQuery(selectQuery,null);

            while (cursor.moveToNext()) {
                FittingTableData data = new FittingTableData();

                data.setName(cursor.getString(1)); //获取第一列的值,第一列的索引从0开始
                data.setDbName(cursor.getString(2));
                data.setDes(cursor.getString(3));
                data.setResourceID(cursor.getInt(4));
                list.add(data);
            }
            cursor.close();

            // return user list
            return list;
        }
        catch(SecurityException e){
            return null;
        }
    }
    public boolean addFittingTableItem(String table, FittingTableData data) {
        Log.i("SQLite", "DBHelper.addFittingTableItem");
        try {
            ContentValues values = new ContentValues();
            values.put("name", data.getName());
            values.put("dBName", data.getDbName());
            values.put("des",data.getDes());
            values.put("layoutResourceID", data.getResourceID());
            dbInstance.insert(table, null, values);
            return true;
        }catch(SQLiteException e)
        {
            return false;
        }
    }

    public boolean createFitting(String table) {
        Log.i("SQLite", "DBHelper.createFitting");
        try{
            String CREATE_CONTACTS_TABLE = "CREATE TABLE if not exists " + table +
                "(id INTEGER PRIMARY KEY autoincrement,number INTEGER,durationTime INTEGER,localTime INTEGER,des STRING)";
            dbInstance.execSQL(CREATE_CONTACTS_TABLE);
            return true;
        }catch(SQLiteException e)
        {
            return false;
        }
    }
    public boolean addFittingItem(String table, FittingData data) {
        Log.i("SQLite", "DBHelper.addFittingItem");
        try {
            ContentValues values = new ContentValues();
//            values.put("id", data.getID());
            values.put("number", data.getNumber());
            values.put("durationTime",data.getDurationTime());
            values.put("localTime", data.getLocalTime());
            values.put("des", "Abc");
            dbInstance.insert(table, null, values);
            return true;
        }catch(SQLiteException e)
        {
            return false;
        }
    }
    public ArrayList<FittingData> getAllFitting(String table) {
        Log.i("SQLite", "DBHelper.getAllFitting");
        ArrayList<FittingData> fittingDataList = new ArrayList<>();
        String sql = "SELECT * FROM " + table;

        Cursor cursor = dbInstance.rawQuery(sql, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                FittingData fd = new FittingData();
//                fd.setID(Integer.parseInt(cursor.getString(0)));
                fd.setNumber(cursor.getInt(1));
                fd.setDurationTime(cursor.getLong(2));
                fd.setLocalTime(cursor.getLong(3));
                fd.setDes(cursor.getString(4));

                // Adding contact to list
                fittingDataList.add(fd);
                Log.i(TAG, "getAllFitting: " + ":" + fd.getNumber() + ":" + fd.getDurationTime() + ":" + fd.getLocalTime() + ":" + fd.getDurationTime());
            } while (cursor.moveToNext());
        }

        cursor.close();

        // return user list
        return fittingDataList;
    }

    public boolean createShowTable() {
        Log.i("SQLite", "DBHelper.createShowTable");
        try{
            String CREATE_CONTACTS_TABLE = "CREATE TABLE if not exists fitting_show(id INTEGER PRIMARY KEY autoincrement,name STRING, count Integer)";
            dbInstance.execSQL(CREATE_CONTACTS_TABLE);
            return true;
        }catch(SQLiteException e)
        {
            return false;
        }
    }
    public ArrayList<ShowTable> getAllShowTable(){
        ArrayList<ShowTable> list = new ArrayList<>();
        // Select All Query
        String selectQuery = "select * from fitting_show order by count desc";

        try {
            Cursor cursor = dbInstance.rawQuery(selectQuery,null);

            while (cursor.moveToNext()) {
                ShowTable data = new ShowTable();
                data.setID(cursor.getInt(0));
                data.setName(cursor.getString(1)); //获取第一列的值,第一列的索引从0开始
                data.setCount(cursor.getInt(2));
                list.add(data);
            }
            cursor.close();
            return list;
        }
        catch(SecurityException e){
            return null;
        }
    }
    public boolean updateShowTableItem(String tablename) {
        Log.i("SQLite", "DBHelper.updateShowTableItem");
        String cmd;
        try {
//            ArrayList<ShowTable> tablelist = getAllShowTable();
            String selectQuery = "select * from fitting_show";//" order by count desc";
//            Cursor cursor = dbInstance.rawQuery(selectQuery,null);
//            for(int i=0;i<cursor.getCount();i++){
//                String name = cursor.getString(1);
//                int count = cursor.getInt(2);
//                int id = cursor.getInt(0);
//
//                if(name.equals(tablename)) {
//                    cmd = "UPDATE fitting_show SET count=" + count+1 +" WHERE ID=" + id;
//                    dbInstance.execSQL(cmd);
//                    return true;
//                }
//            }
            String sql = "SELECT * FROM fitting_show";

            Cursor cursor = dbInstance.rawQuery(sql, null);
            while (cursor.moveToNext()) {
                String name = cursor.getString(1);
                int count = cursor.getInt(2);
                int id = cursor.getInt(0);
            }

            ContentValues values = new ContentValues();
            values.put("name", tablename);
            values.put("count",1);
            dbInstance.insert(TABLE_SHOW, null, values);
            return false;
        }catch(SQLiteException e)
        {
            return false;
        }
    }


    //insert into fitting_show(id,name,count)values(2,"JUANFU",14);
    //select * from fitting_show order by count desc;
    //update fitting_show set count=15 where id=2;

    public boolean deleteTable(String table){
        Log.i("SQLite", "DBHelper.deleteFittingTable");
        String cmd = "drop table " + table;
        try {
            dbInstance.execSQL(cmd);
            return true;
        }catch(SQLiteException e){
            return false;
        }
    }
    //清空整个表
    public boolean clearTable(String table){
        Log.i("SQLite", "DBHelper.deleteFittingTable");
        String cmd = "delete from " + table;
        try {
            dbInstance.execSQL(cmd);
            return true;
        }catch(SQLiteException e){
            return false;
        }
    }
    public boolean deleteItem(String table, int index){
        Log.i("SQLite", "DBHelper.createFitting");
        String cmd = "delete from " + table + "where id=" + index;
        try {
            dbInstance.execSQL(cmd);
            return true;
        }catch(SQLiteException e){
            return false;
        }
    }
    public int getTableCount(String table){
        try{
            String countQuery = "SELECT  * FROM " + table;
            Cursor cursor = dbInstance.rawQuery(countQuery, null);
            int count = cursor.getCount();
            cursor.close();
            return count;
        }
        catch(SQLiteException e)
        {
            return -1;
        }
    }
    public boolean tableExists(String tableName){
        if (tableName == null)
        {
            return false;
        }
        if(dbInstance == null)
            return false;
        if(dbInstance.isOpen())
            return false;

        Cursor cursor = dbInstance.rawQuery("SELECT COUNT(*) FROM sqlite_master WHERE type = ? AND name = ?", new String[] {"table", tableName});
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
