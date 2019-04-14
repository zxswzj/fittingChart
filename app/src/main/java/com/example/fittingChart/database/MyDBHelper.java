package com.example.fittingChart.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

import com.example.fittingChart.R;
import com.example.fittingChart.module.FittingData;
import com.example.fittingChart.module.FittingTableData;

import java.util.ArrayList;

public class MyDBHelper extends SQLiteOpenHelper {

    private static final String TABLE_FITTING_BREAST = "fitting_breast";
    private static final String TABLE_FITTING_SHOULDER = "fitting_shoulder";
    private static final String TABLE_FITTING_BACK = "fitting_back";
    private static final String TABLE_FITTING_ARM = "fitting_arm";
    private static final String TABLE_FITTING_BELLY = "fitting_belly";
    private static final String TABLE_FITTING_LEG = "fitting_leg";
    private static final String TABLE_FITTING_BRAIN = "fitting_brain";
    private static final String TABLE_FITTING_OTHER = "fitting_other";
    private static final String TABLE_SHOW = "fitting_show";

    public MyDBHelper(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    // Called when no database exists in disk and the helper class needs
    // to create a new one.
    @Override
    public void onCreate(SQLiteDatabase _db) {
        try {
            _db.execSQL(MyDatabaseAdapter.DATABASE_CREATE);
            createFittingTable(_db, TABLE_FITTING_ARM);
            createFittingTable(_db, TABLE_FITTING_BACK);
            createFittingTable(_db, TABLE_FITTING_BELLY);
            createFittingTable(_db, TABLE_FITTING_BRAIN);
            createFittingTable(_db, TABLE_FITTING_LEG);
            createFittingTable(_db, TABLE_FITTING_OTHER);
            createFittingTable(_db, TABLE_FITTING_SHOULDER);
            createFittingTable(_db, TABLE_FITTING_BREAST);

            addFittingTableItem(_db, TABLE_FITTING_BREAST, new FittingTableData("俯卧撑","FUWOCHENG","abc", R.drawable.pushup));
            addFittingTableItem(_db, TABLE_FITTING_BREAST, new FittingTableData("跪姿俯卧撑","GUIZIFUWOCHENG","abc", R.drawable.knee_pushup));
            addFittingTableItem(_db, TABLE_FITTING_BREAST, new FittingTableData("扶墙俯卧撑","FUQIANGFUWOCHENG","abc",R.drawable.ic_dashboard_black_24dp));

            addFittingTableItem(_db, TABLE_FITTING_SHOULDER, new FittingTableData("哑铃前平举","YALINGQIANPINGJU","abc",R.drawable.ic_dashboard_black_24dp));
            addFittingTableItem(_db, TABLE_FITTING_SHOULDER, new FittingTableData("哑铃侧平举","YALINGCEPINGJU","abc",R.drawable.ic_dashboard_black_24dp));

            addFittingTableItem(_db, TABLE_FITTING_BACK, new FittingTableData("哑铃飞鸟","YALINGFEINIAO","abc",R.drawable.ic_dashboard_black_24dp));

            addFittingTableItem(_db, TABLE_FITTING_ARM, new FittingTableData("哑铃肱二头肌弯举","YALINGGONGERTOUJIWANJU","abc",R.drawable.ic_dashboard_black_24dp));
            addFittingTableItem(_db, TABLE_FITTING_ARM, new FittingTableData("哑铃肱二头肌弯举","YALINGGONGSANTOUJIWANJU","abc",R.drawable.ic_dashboard_black_24dp));

            addFittingTableItem(_db, TABLE_FITTING_BELLY, new FittingTableData("卷腹","JUANFU","abc",R.drawable.ic_dashboard_black_24dp));
            addFittingTableItem(_db, TABLE_FITTING_BELLY, new FittingTableData("仰卧起坐","YANGWOQIZUO","abc",R.drawable.ic_dashboard_black_24dp));
            addFittingTableItem(_db, TABLE_FITTING_BELLY, new FittingTableData("平板支撑","PINGBANZHICHENG","abc",R.drawable.ic_dashboard_black_24dp));

            addFittingTableItem(_db, TABLE_FITTING_LEG, new FittingTableData("深蹲","SHENDUN","abc",R.drawable.ic_dashboard_black_24dp));
            addFittingTableItem(_db, TABLE_FITTING_LEG, new FittingTableData("靠墙蹲","KAOQIANGDUN","abc",R.drawable.ic_dashboard_black_24dp));

            addFittingTableItem(_db, TABLE_FITTING_BRAIN, new FittingTableData("最强大脑之数字迷宫","ZUIQIANGDANAOZHISHUZIMIGONG","abc",R.drawable.ic_dashboard_black_24dp));

            addFittingTableItem(_db, TABLE_FITTING_OTHER, new FittingTableData("体重","TIZHONG","abc",R.drawable.ic_dashboard_black_24dp));
            addFittingTableItem(_db, TABLE_FITTING_OTHER, new FittingTableData("波比跳","BOBITIAO","abc",R.drawable.ic_dashboard_black_24dp));
            addFittingTableItem(_db, TABLE_FITTING_OTHER, new FittingTableData("倒立","DAOLI","abc",R.drawable.ic_dashboard_black_24dp));


            createFitting(_db, "FUWOCHENG");
            createFitting(_db, "GUIZIFUWOCHENG");
            createFitting(_db, "FUQIANGFUWOCHENG");
            createFitting(_db, "YALINGQIANPINGJU");
            createFitting(_db, "YALINGCEPINGJU");
            createFitting(_db, "YALINGFEINIAO");
            createFitting(_db, "YALINGGONGERTOUJIWANJU");
            createFitting(_db, "YALINGGONGSANTOUJIWANJU");
            createFitting(_db, "JUANFU");
            createFitting(_db, "YANGWOQIZUO");
            createFitting(_db, "PINGBANZHICHENG");
            createFitting(_db, "SHENDUN");
            createFitting(_db, "KAOQIANGDUN");
            createFitting(_db, "ZUIQIANGDANAOZHISHUZIMIGONG");
            createFitting(_db, "TIZHONG");
            createFitting(_db, "BOBITIAO");
            createFitting(_db, "DAOLI");

            createShowTable(_db);
        }catch(Exception er){
            Log.e("Error","exceptioin");
        }
    }
    // Called when there is a database version mismatch meaning that the version
    // of the database on disk needs to be upgraded to the current version.
    @Override
    public void onUpgrade(SQLiteDatabase _db, int _oldVersion, int _newVersion)
    {
        // Log the version upgrade.
        Log.w("TaskDBAdapter", "Upgrading from version " +_oldVersion + " to " +_newVersion + ", which will destroy all old data");
        // Upgrade the existing database to conform to the new version. Multiple
        // previous versions can be handled by comparing _oldVersion and _newVersion
        // values.
        // The simplest case is to drop the old table and create a new one.
        _db.execSQL("DROP TABLE IF EXISTS " + "LOGIN");

        // Create a new one.
        onCreate(_db);
    }

    void createFittingTable(SQLiteDatabase _db, String table) {
        Log.i("SQLite", "DBHelper.createFitting");
        String CREATE_TABLE = "CREATE TABLE if not exists " + table + "(id INTEGER PRIMARY KEY autoincrement,name STRING, DBName STRING, des STRING, layoutResourceID INTEGER)";
        _db.execSQL(CREATE_TABLE);
    }

    public boolean addFittingTableItem(SQLiteDatabase _db, String table, FittingTableData data) {
        Log.i("SQLite", "DBHelper.addFittingTableItem");
        ContentValues values = new ContentValues();
        values.put("name", data.getName());
        values.put("dBName", data.getDbName());
        values.put("des",data.getDes());
        values.put("layoutResourceID", data.getResourceID());
        _db.insert(table, null, values);
        return true;
    }

    public void createFitting(SQLiteDatabase _db, String table) {
        Log.i("SQLite", "DBHelper.createFitting");
        String CREATE_CONTACTS_TABLE = "CREATE TABLE if not exists " + table +
                    "(id INTEGER PRIMARY KEY autoincrement,number INTEGER,durationTime INTEGER, restTime INTEGER, localTime INTEGER,des STRING)";
        _db.execSQL(CREATE_CONTACTS_TABLE);
    }

    void createShowTable(SQLiteDatabase _db) {
        Log.i("SQLite", "DBHelper.createShowTable");
        String CREATE_CONTACTS_TABLE = "CREATE TABLE if not exists fitting_show(id INTEGER PRIMARY KEY autoincrement,name STRING,DBName STRING,count Integer)";
        _db.execSQL(CREATE_CONTACTS_TABLE);
    }
}
