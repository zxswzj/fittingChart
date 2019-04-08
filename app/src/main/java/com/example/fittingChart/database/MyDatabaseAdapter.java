package com.example.fittingChart.database;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import android.widget.Toast;

import com.example.fittingChart.R;
import com.example.fittingChart.module.FittingData;
import com.example.fittingChart.module.FittingTableData;
import com.example.fittingChart.module.ShowTable;

import java.util.ArrayList;
import java.util.List;


public class MyDatabaseAdapter {

    String TAG = "SQLite";
    static final String DATABASE_NAME = "database.db";
    String ok="OK";
    static final int DATABASE_VERSION = 1;
    public  static String getPassword="";

    private static final String TABLE_FITTING_BREAST = "fitting_breast";
    private static final String TABLE_FITTING_SHOULDER = "fitting_shoulder";
    private static final String TABLE_FITTING_BACK = "fitting_back";
    private static final String TABLE_FITTING_ARM = "fitting_arm";
    private static final String TABLE_FITTING_BELLY = "fitting_belly";
    private static final String TABLE_FITTING_LEG = "fitting_leg";
    private static final String TABLE_FITTING_BRAIN = "fitting_brain";
    private static final String TABLE_FITTING_OTHER = "fitting_other";
    private static final String TABLE_SHOW = "fitting_show";

    public static final int NAME_COLUMN = 1;
    // TODO: Create public field for each column in your table.
    // SQL Statement to create a new database.
    static final String DATABASE_CREATE = "create table LOGIN( ID integer primary key autoincrement,FIRSTNAME  text,LASTNAME  text,USERNAME text,PASSWORD text); ";


    // Variable to hold the database instance
    public static SQLiteDatabase db;
    // Context of the application using the database.
    private final Context context;
    // Database open/upgrade helper
    private static MyDBHelper dbHelper;
    public  MyDatabaseAdapter(Context _context)
    {
        context = _context;
        dbHelper = new MyDBHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Method to open the Database
    public  MyDatabaseAdapter open() throws SQLException
    {
        db = dbHelper.getWritableDatabase();
        return this;
    }

    // Method to close the Database
    public void close()
    {
        db.close();
    }

    // method returns an Instance of the Database
    public  SQLiteDatabase getDatabaseInstance()
    {
        return db;
    }

    // method to insert a record in Table
    public String insertEntry(String firstName,String lastName,String Id,String password){

        try {

            ContentValues newValues = new ContentValues();
            // Assign values for each column.
            newValues.put("FIRSTNAME", firstName);
            newValues.put("LASTNAME", lastName);
            newValues.put("USERNAME", Id);
            newValues.put("PASSWORD", password);


            // Insert the row into your table
            db = dbHelper.getWritableDatabase();
            long result=db.insert("LOGIN", null, newValues);
            System.out.print(result);
            Toast.makeText(context, "User Info Saved", Toast.LENGTH_LONG).show();


        }catch(Exception ex) {
            System.out.println("Exceptions " +ex);
            Log.e("Note", "One row entered");
        }
        return ok;
    }
    // method to delete a Record of UserName
    public int deleteEntry(String UserName){

        String where="USERNAME=?";
        int numberOFEntriesDeleted= db.delete("LOGIN", where, new String[]{UserName}) ;
        Toast.makeText(context, "Number fo Entry Deleted Successfully : "+numberOFEntriesDeleted, Toast.LENGTH_LONG).show();
        return numberOFEntriesDeleted;

    }

    // method to get the password  of userName
    public String getSinlgeEntry(String userName){

        db=dbHelper.getReadableDatabase();
        Cursor cursor=db.query("LOGIN", null, "USERNAME=?", new String[]{userName}, null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
            return "NOT EXIST";
        cursor.moveToFirst();
        getPassword= cursor.getString(cursor.getColumnIndex("PASSWORD"));
        return getPassword;


    }
    // Method to Update an Existing
    public void  updateEntry(String userName,String password){
        //  create object of ContentValues
        ContentValues updatedValues = new ContentValues();
        // Assign values for each Column.
        updatedValues.put("USERNAME", userName);
        updatedValues.put("PASSWORD", password);

        String where="USERNAME = ?";
        db.update("LOGIN",updatedValues, where, new String[]{userName});

    }

    public void init(){
        addFittingTableItem(TABLE_FITTING_BREAST, new FittingTableData("跪姿俯卧撑","GUIZIFUWOCHENG","abc", R.drawable.ic_launcher_foreground));
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

    }

    public boolean addFittingTableItem(String table, FittingTableData data) {
        Log.i("SQLite", "DBHelper.addFittingTableItem");
        ContentValues values = new ContentValues();
        values.put("name", data.getName());
        values.put("dBName", data.getDbName());
        values.put("des",data.getDes());
        values.put("layoutResourceID", data.getResourceID());
        db.insert(table, null, values);
        return true;
    }
    public boolean addFittingItem(String table,FittingData fittingData){

        try {

            ContentValues newValues = new ContentValues();
            // Assign values for each column.
            newValues.put("number", fittingData.getNumber());
            newValues.put("durationTime", fittingData.getDurationTime());
            newValues.put("localTime", fittingData.getLocalTime());
            newValues.put("des", fittingData.getDes());


            // Insert the row into your table
            db = dbHelper.getWritableDatabase();
            long result=db.insert(table, null, newValues);
            System.out.print(result);
        }catch(Exception ex) {
            System.out.println("Exceptions " +ex);
            Log.e("Note", "One row entered");
        }
        return true;
    }
    public boolean addShowTableItem(String name, String DBName){

        try {
            ArrayList<ShowTable> showTables = getAllShowTable();
            for(int i=0;i<showTables.size();i++){
                String dbname = showTables.get(i).getDBName();
                if(dbname.equals(DBName)){
                    updateShowTableItem(name, DBName,showTables.get(i).getCount() + 1);
                    return true;
                }
            }
            //如果表中没有此数据，则新增加此数据
            insertShowTableItem(name, DBName, 1);
            return true;
        }catch(Exception ex) {
            System.out.println("Exceptions " +ex);
            Log.e("Note", "One row entered");
        }
        return false;
    }
    public boolean updateShowTableItem(String name, String DBName, int count){
        ContentValues updatedValues = new ContentValues();
        // Assign values for each Column.
        updatedValues.put("name", name);
        updatedValues.put("DBName", DBName);
        updatedValues.put("count", count);

        String where="DBName = ?";
        db.update("fitting_show",updatedValues, where, new String[]{DBName});
        return true;
    }

    public ArrayList<FittingData> getAllFitting(String table) {
        Log.i("SQLite", "DBHelper.getAllFitting");
        ArrayList<FittingData> fittingDataList = new ArrayList<>();
        String sql = "SELECT * FROM " + table;

        Cursor cursor = db.rawQuery(sql, null);

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

    public ArrayList<FittingTableData> getAllFittingTable(String table){
        ArrayList<FittingTableData> list = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + table;

        try {
            Cursor cursor = db.rawQuery(selectQuery,null);

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

    public ArrayList<ShowTable> getAllShowTable(){
        ArrayList<ShowTable> list = new ArrayList<>();
        // Select All Query
        String selectQuery = "select * from fitting_show order by count desc";

        try {
            Cursor cursor = db.rawQuery(selectQuery,null);

            while (cursor.moveToNext()) {
                ShowTable data = new ShowTable();
                data.setID(cursor.getInt(0));
                data.setName(cursor.getString(1));
                data.setDBName(cursor.getString(2));//获取第一列的值,第一列的索引从0开始
                data.setCount(cursor.getInt(3));
                list.add(data);
            }
            cursor.close();
            return list;
        }
        catch(SecurityException e){
            return null;
        }
    }
    public boolean insertShowTableItem(String name, String DBName, int count){
        try {

            ContentValues newValues = new ContentValues();
            // Assign values for each column.
            newValues.put("name", name);
            newValues.put("DBName", DBName);
            newValues.put("count", count);

            // Insert the row into your table
            db = dbHelper.getWritableDatabase();
            db.insert("fitting_show", null, newValues);

        }catch(Exception ex) {
            Log.e("Note", "One row entered");
        }
        return true;
    }

}
