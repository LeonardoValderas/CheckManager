package com.jofre.managercheck.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.jofre.managercheck.entities.Check;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by LEO on 17/7/2016.
 */
public class CheckController {
    Context context;
    SQLiteDataBaseCheck sqLiteDataBaseCheck;
    SQLiteDatabase database;

    public CheckController(Context context) {
        this.context = context;
    }

    public CheckController openDataBase() throws SQLException {
        sqLiteDataBaseCheck = new SQLiteDataBaseCheck(context,
                "CheckDataBase.db", null, 1);
        database = sqLiteDataBaseCheck.getWritableDatabase();
        return this;
    }

    public void closeDataBase() {
        sqLiteDataBaseCheck.close();
    }

    public boolean insertCheck(Check check)
            throws SQLiteException {

        ContentValues cv = new ContentValues();
        openDataBase();
        try {
            cv.put("NUMBER", check.getNumber());
            cv.put("AMOUNT", check.getAmount());
            cv.put("EXPIRATION", String.valueOf(check.getExpiration()));
            cv.put("PHOTO", check.getPhoto());
            cv.put("ORIGIN", check.getOrigin());

            long valor = database.insert("CHECKS", null, cv);
            closeDataBase();
            if (valor > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLiteException e) {
            closeDataBase();
            return false;
        }
    }
    public boolean updateCheck(Check check)
            throws SQLiteException {

        ContentValues cv = new ContentValues();
        openDataBase();
        try {
            cv.put("NUMBER", check.getNumber());
            cv.put("AMOUNT", check.getAmount());
            cv.put("EXPIRATION", check.getExpiration());
            cv.put("PHOTO", check.getPhoto());
            cv.put("ORIGIN", check.getOrigin());

            long valor = database.update("CHECKS", cv,"ID_CHECK="+ check.getId_check(),null);
            closeDataBase();
            if (valor > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLiteException e) {
            closeDataBase();
            return false;
        }
    }

    public List<Check> selectAllCheck() {

        String sql = "SELECT * FROM CHECKS";
        List<Check> arrayChecks = new ArrayList<Check>();
        String number = null, amount = null, origin = null, date = null;
        int id;
//
//        Date d = null;
//        SimpleDateFormat  df = new SimpleDateFormat("dd-MM-yyyy");
        byte[] photo = null;
        Cursor cursor = null;
        openDataBase();
        if (database != null && database.isOpen()) {
            try {
                cursor = database.rawQuery(sql, null);
                if (cursor != null && cursor.getCount() > 0) {
                    while (cursor.moveToNext()) {
                        Check check = null;
                        id = cursor.getInt(cursor.getColumnIndex("ID_CHECK"));
                        number = cursor.getString(cursor
                                .getColumnIndex("NUMBER"));

                        photo = cursor.getBlob(cursor
                                .getColumnIndex("PHOTO"));
                        amount = cursor.getString(cursor
                                .getColumnIndex("AMOUNT"));
                        origin = cursor.getString(cursor
                                .getColumnIndex("ORIGIN"));
                        date = cursor.getString(cursor
                                .getColumnIndex("EXPIRATION"));
//                        try {
//                            d = df.parse(date);
//                        }catch (ParseException e){
//                            e.printStackTrace();
//                        }
                        check = new Check(id, number, amount, date, origin, photo);
                        arrayChecks.add(check);
                    }
                }
            } catch (Exception e) {
                arrayChecks = null;
            }
        } else {
            arrayChecks = null;
        }
        closeDataBase();
        sql = null;
        cursor = null;
        database = null;
        number = null;
        amount = null;
        origin = null;
        date = null;
//        d = null;
        return arrayChecks;
    }

}
