package com.example.fittingChart.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.example.fittingChart.database.ShowTable;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "SHOW_TABLE".
*/
public class ShowTableDao extends AbstractDao<ShowTable, String> {

    public static final String TABLENAME = "SHOW_TABLE";

    /**
     * Properties of entity ShowTable.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Name = new Property(0, String.class, "name", true, "NAME");
        public final static Property DbName = new Property(1, String.class, "DbName", false, "DB_NAME");
        public final static Property Count = new Property(2, int.class, "count", false, "COUNT");
    }


    public ShowTableDao(DaoConfig config) {
        super(config);
    }
    
    public ShowTableDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"SHOW_TABLE\" (" + //
                "\"NAME\" TEXT PRIMARY KEY NOT NULL ," + // 0: name
                "\"DB_NAME\" TEXT," + // 1: DbName
                "\"COUNT\" INTEGER NOT NULL );"); // 2: count
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"SHOW_TABLE\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, ShowTable entity) {
        stmt.clearBindings();
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(1, name);
        }
 
        String DbName = entity.getDbName();
        if (DbName != null) {
            stmt.bindString(2, DbName);
        }
        stmt.bindLong(3, entity.getCount());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, ShowTable entity) {
        stmt.clearBindings();
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(1, name);
        }
 
        String DbName = entity.getDbName();
        if (DbName != null) {
            stmt.bindString(2, DbName);
        }
        stmt.bindLong(3, entity.getCount());
    }

    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0);
    }    

    @Override
    public ShowTable readEntity(Cursor cursor, int offset) {
        ShowTable entity = new ShowTable( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // name
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // DbName
            cursor.getInt(offset + 2) // count
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, ShowTable entity, int offset) {
        entity.setName(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setDbName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setCount(cursor.getInt(offset + 2));
     }
    
    @Override
    protected final String updateKeyAfterInsert(ShowTable entity, long rowId) {
        return entity.getName();
    }
    
    @Override
    public String getKey(ShowTable entity) {
        if(entity != null) {
            return entity.getName();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(ShowTable entity) {
        return entity.getName() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
