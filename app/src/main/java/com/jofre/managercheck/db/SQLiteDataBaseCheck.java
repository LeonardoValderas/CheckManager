package com.jofre.managercheck.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by LEO on 17/7/2016.
 */
public class SQLiteDataBaseCheck extends SQLiteOpenHelper {

    String TABLA_CHECKS = "CREATE TABLE IF NOT EXISTS CHECKS(ID_CHECK INTEGER PRIMARY KEY AUTOINCREMENT,"
            + " NUMBER VARCHAR(100),"
            + " AMOUNT VARCHAR(100),"
            + " EXPIRATION VARCHAR(100),"
            + " PHOTO BLOB,"
            + " ORIGIN VARCHAR(100));";

    public SQLiteDataBaseCheck(Context context, String name,
                               SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLA_CHECKS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS CHECKS");
        db.execSQL(TABLA_CHECKS);
    }
}
