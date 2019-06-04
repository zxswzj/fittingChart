package com.example.fittingChart.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "PRODUCT".
*/
public class FittingItemDao extends AbstractDao<FittingItem, Integer> {

    public static final String TABLENAME = "FITTINGDATA";

    /**
     * Properties of entity Product.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Number = new Property(0, Integer.class, "Number", true, "NUMBER");
        public final static Property DurationTime = new Property(1, Long.class, "DurationTime", false, "DURATIONTIME");
        public final static Property RestTime = new Property(2, Long.class, "RestTime", false, "RESTTIME");
        public final static Property LocalTime = new Property(3, Long.class, "LocalTime", false, "LOCALTIME");
        public final static Property Des = new Property(4, String.class, "Des", false, "DES");
    }


    public FittingItemDao(DaoConfig config) {
        super(config);
    }
    
    public FittingItemDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"FITTINGDATA\" (" + //
                "\"NUMBER\" Integer PRIMARY KEY NOT NULL ," +
                "\"DURATIONTIME\" Long , " +
                "\"RESTTIME\" Long , " +
                "\"LOCALTIME\" Long , " +
                "\"DES\" String);");
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"PRODUCT\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, FittingItem entity) {
        stmt.clearBindings();
 
        Integer number = entity.getNumber();
        stmt.bindLong(1, number);

 
        Long durationTime = entity.getDurationTime();
        stmt.bindLong(2, durationTime);

        Long restTime = entity.getRestTime();
        stmt.bindLong(3, restTime);

        Long localTime = entity.getLocalTime();
        stmt.bindLong(4, localTime);

        String des = entity.getDes();
        stmt.bindString(5, des);
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, FittingItem entity) {
        stmt.clearBindings();

        Integer number = entity.getNumber();
        stmt.bindLong(1, number);


        Long durationTime = entity.getDurationTime();
        stmt.bindLong(2, durationTime);

        Long restTime = entity.getRestTime();
        stmt.bindLong(3, restTime);

        Long localTime = entity.getLocalTime();
        stmt.bindLong(4, localTime);

        String des = entity.getDes();
        stmt.bindString(5, des);
    }

    @Override
    public Integer readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getInt(offset + 0);
    }    

    @Override
    public FittingItem readEntity(Cursor cursor, int offset) {
        FittingItem entity = new FittingItem( //
            cursor.isNull(offset + 0) ? null : cursor.getInt(offset + 0), // Id
            cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // Name
            cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2), // Name
            cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3), // Name
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4) // Name
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, FittingItem entity, int offset) {
        entity.setNumber(cursor.isNull(offset + 0) ? null : cursor.getInt(offset + 0));
        entity.setDurationTime(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setRestTime(cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2));
        entity.setLocalTime(cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3));
        entity.setDes(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
    }
    
    @Override
    protected final Integer updateKeyAfterInsert(FittingItem entity, long rowId) {
        return entity.getNumber();
    }
    
    @Override
    public Integer getKey(FittingItem entity) {
        if(entity != null) {
            return entity.getNumber();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(FittingItem entity) {
        return entity.getDes() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}