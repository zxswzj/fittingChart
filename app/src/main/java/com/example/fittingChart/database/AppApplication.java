package com.example.fittingChart.database;

import android.app.Application;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;

import com.example.fittingChart.R;
import com.example.fittingChart.greendao.DaoMaster;
import com.example.fittingChart.greendao.DaoSession;
import com.example.fittingChart.greendao.FittingItemDao;
import com.example.fittingChart.greendao.FittingTableDao;

import java.util.List;

public class AppApplication extends Application{
        public static AppApplication instances;

        private SharedPreferences preferences;
        @Override
        public void onCreate() {
                super.onCreate();
                instances = this;
                initGreenDao();
        }
        public static AppApplication getInstances() {
                return instances;
        }
        private SQLiteDatabase db;
        private DaoSession mDaoSession;
        private void initGreenDao() {
                DaoMaster.DevOpenHelper mHelper;
                DaoMaster mDaoMaster;
                // 通过DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的SQLiteOpenHelper 对象。
                // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为greenDAO 已经帮你做了。
                // 注意：默认的DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
                // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
                mHelper = new DaoMaster.DevOpenHelper(this, "notes-db", null);
                db = mHelper.getWritableDatabase();
                // 注意：该数据库连接属于DaoMaster，所以多个 Session 指的是相同的数据库连接。
                mDaoMaster = new DaoMaster(db);
                mDaoSession = mDaoMaster.newSession();

                getSharedPreferences
                //初始化fittingTable
                FittingTableDao fittingTableDao = mDaoSession.getFittingTableDao();
                List<FittingTable> fittingTable = fittingTableDao.queryBuilder().build().list();
                if(fittingTable.size() == 0){
                        fittingTableDao.insert(new FittingTable("FUWOCHENG", "sdfds","CHEST", "des", R.drawable.bicycle));
                        fittingTableDao.insert(new FittingTable("BANCHENGFUWOCHENG", "sdfds","CHEST", "des", R.drawable.dumbbell2));

                        fittingTableDao.insert(new FittingTable("DAOLI", "sdfds","OTHERS", "des", R.drawable.dumbbell2));
                }

        }
        public DaoSession getDaoSession() {
                return mDaoSession;
        }
        public SQLiteDatabase getDb() {return db;}
}