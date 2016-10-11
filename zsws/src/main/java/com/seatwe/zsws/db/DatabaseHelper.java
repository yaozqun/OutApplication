package com.seatwe.zsws.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.grgbanking.baselib.util.log.LogUtil;
import com.grgbanking.baselib.web.bean.CashBoxData;
import com.grgbanking.baselib.web.bean.NetInfoData;
import com.grgbanking.baselib.web.bean.UpgradeData;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.seatwe.zsws.bean.LineInfoData;
import com.seatwe.zsws.bean.TaskInfoData;

/**
 * 数据库操作类
 * 
 * @author G0210181
 * 
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    // name of the database file for your application -- change to something
    // appropriate for your app
    private static final String DATABASE_NAME = "acspda.db";

    // any time you make changes to your database objects, you may have to
    // increase the database version
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This is called when the database is first created. Usually you should
     * call createTable statements here to create the tables that will store
     * your data.
     */
    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        LogUtil.i(DatabaseHelper.class.getName(), "onCreate");
        try {
            try {
                TableUtils.createTable(connectionSource, LineInfoData.class);
                TableUtils.createTable(connectionSource, TaskInfoData.class);
                TableUtils.createTable(connectionSource, CashBoxData.class);
                TableUtils.createTable(connectionSource, NetInfoData.class);
                TableUtils.createTable(connectionSource, UpgradeData.class);
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This is called when your application is upgraded and it has a higher
     * version number. This allows you to adjust the various data to match the
     * new version number.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
    }
}
