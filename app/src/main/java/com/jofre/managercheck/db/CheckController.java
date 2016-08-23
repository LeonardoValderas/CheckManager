package com.jofre.managercheck.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.jofre.managercheck.entities.Check;
import com.jofre.managercheck.entities.CheckInformationAdd;

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

    //    INSERT/UPDATE/DELETE CHECK ADD
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

            long valor = database.update("CHECKS", cv, "ID_CHECK=" + check.getId_check(), null);
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

        String sql = "SELECT * FROM CHECKS ORDER BY ID_CHECK DESC";
        List<Check> arrayChecks = new ArrayList<Check>();
        String number = null, amount = null, origin = null, date = null;
        int id;
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
        return arrayChecks;
    }

    public boolean deleteCheck(List<Check> checks)
            throws SQLiteException {
        boolean result = true;
        ContentValues cv = new ContentValues();
        openDataBase();
        try {
            for (int i = 0; i < checks.size(); i++) {
                long valor = database.delete("CHECKS", "ID_CHECK=" + checks.get(i).getId_check(), null);
                if (valor > 0) {
                    result = true;
                } else {
                    result = false;
                    break;
                }
            }
            closeDataBase();
        } catch (SQLiteException e) {
            closeDataBase();
            result = false;
        }
        return result;
    }

    public CheckInformationAdd QuantityAmountTotalCheckAdd() {

        String sql = "SELECT COUNT(ID_CHECK) AS QUANTITY, SUM(AMOUNT) AS QUANTITYTOTAL FROM CHECKS";
        String quantityAmount = null;
        String quantityAmountTotal = null;
        Cursor cursor = null;
        CheckInformationAdd checkInformationAdd = new CheckInformationAdd();
        openDataBase();
        if (database != null && database.isOpen()) {
            try {
                cursor = database.rawQuery(sql, null);
                if (cursor != null && cursor.getCount() > 0) {
                    while (cursor.moveToNext()) {
                        quantityAmount = String.valueOf(cursor.getInt(cursor.getColumnIndex("QUANTITY")));
                        quantityAmountTotal = cursor.getString(cursor.getColumnIndex("QUANTITYTOTAL"));

                        checkInformationAdd.setAmountTotal(quantityAmount);
                        checkInformationAdd.setAmountQuantityTotal(quantityAmountTotal);
                    }
                }
            } catch (Exception e) {
                quantityAmount = null;
            }
        } else {
            quantityAmount = null;
        }
        closeDataBase();
        sql = null;
        cursor = null;
        database = null;

        return checkInformationAdd;
    }
    public CheckInformationAdd QuantityAmountWeekCheckAdd(String startDate, String endDate) {

        String sql = "SELECT COUNT(ID_CHECK) AS QUANTITY, SUM(AMOUNT) AS QUANTITYTOTAL FROM CHECKS WHERE EXPIRATION >= '"+startDate+"' AND EXPIRATION <= '"+endDate+"'";
        String quantityAmount = null;
        String quantityAmountTotal = null;
        Cursor cursor = null;
        CheckInformationAdd checkInformationAdd = new CheckInformationAdd();
        openDataBase();
        if (database != null && database.isOpen()) {
            try {
                cursor = database.rawQuery(sql, null);
                if (cursor != null && cursor.getCount() > 0) {
                    while (cursor.moveToNext()) {
                        quantityAmount = String.valueOf(cursor.getInt(cursor.getColumnIndex("QUANTITY")));
                        quantityAmountTotal = cursor.getString(cursor.getColumnIndex("QUANTITYTOTAL"));

                        checkInformationAdd.setAmountWeek(quantityAmountTotal);
                        checkInformationAdd.setAmountQuantityWeek(quantityAmount);
                    }
                }
            } catch (Exception e) {
                quantityAmount = null;
            }
        } else {
            quantityAmount = null;
        }
        closeDataBase();
        sql = null;
        cursor = null;
        database = null;

        return checkInformationAdd;
    }


    //    INSERT/UPDATE/DELETE CHECK OWN
    public boolean insertCheckOwn(Check check)
            throws SQLiteException {

        ContentValues cv = new ContentValues();
        openDataBase();
        try {
            cv.put("NUMBER", check.getNumber());
            cv.put("AMOUNT", check.getAmount());
            cv.put("EXPIRATION", String.valueOf(check.getExpiration()));
            cv.put("PHOTO", check.getPhoto());
            cv.put("ORIGIN", check.getOrigin());

            long valor = database.insert("CHECKS_OWN", null, cv);
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

    public boolean updateCheckOwn(Check check)
            throws SQLiteException {

        ContentValues cv = new ContentValues();
        openDataBase();
        try {
            cv.put("NUMBER", check.getNumber());
            cv.put("AMOUNT", check.getAmount());
            cv.put("EXPIRATION", check.getExpiration());
            cv.put("PHOTO", check.getPhoto());
            cv.put("ORIGIN", check.getOrigin());

            long valor = database.update("CHECKS_OWN", cv, "ID_CHECK=" + check.getId_check(), null);
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

    public List<Check> selectAllCheckOwn() {

        String sql = "SELECT * FROM CHECKS_OWN ORDER BY ID_CHECK DESC";
        List<Check> arrayChecks = new ArrayList<Check>();
        String number = null, amount = null, origin = null, date = null;
        int id;
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
        return arrayChecks;
    }

    public boolean deleteCheckOwn(List<Check> checks)
            throws SQLiteException {
        boolean result = true;
        ContentValues cv = new ContentValues();
        openDataBase();
        try {
            for (int i = 0; i < checks.size(); i++) {
                long valor = database.delete("CHECKS_OWN", "ID_CHECK=" + checks.get(i).getId_check(), null);
                if (valor > 0) {
                    result = true;
                } else {
                    result = false;
                    break;
                }
            }

            closeDataBase();
        } catch (SQLiteException e) {
            closeDataBase();
            result = false;
        }
        return result;
    }


}
