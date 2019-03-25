package com.example.fittingChart.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class MyDBHelper extends SQLiteOpenHelper {

    public MyDBHelper(Context context, String name,
                      int version) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuffer tableCreate = new StringBuffer();
        tableCreate.append("create table ")
                .append("table name")
                .append(" (")
                .append("id integer primary key autoincrement,")
                .append("content text,")
                .append("chatTime text,")
                .append("isComeMsg text,")
                .append("type text ")
                .append(")");
        System.out.println(tableCreate.toString());
        db.execSQL(tableCreate.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "drop table if exists " + "tablename";
        db.execSQL(sql);
        onCreate(db);
    }

}
