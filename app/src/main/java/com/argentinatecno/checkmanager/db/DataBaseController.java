package com.argentinatecno.checkmanager.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.argentinatecno.checkmanager.entities.Check;
import com.argentinatecno.checkmanager.entities.Maturities;

import java.util.ArrayList;
import java.util.List;

public class DataBaseController {
    Context context;
    SQLiteDataBase sqLiteDataBase;
    SQLiteDatabase database;

    public DataBaseController(Context context) {
        this.context = context;
    }

    public DataBaseController openDataBase() throws SQLException {
        sqLiteDataBase = new SQLiteDataBase(context,
                "DataBase.db", null, 1);
        database = sqLiteDataBase.getWritableDatabase();
        return this;
    }

    public void closeDataBase() {
        sqLiteDataBase.close();
    }

    //    INSERT/UPDATE/DELETE CHECK
    public boolean insertCheck(Check check)
            throws SQLiteException {

        ContentValues cv = new ContentValues();
        openDataBase();
        try {
            cv.put("TYPE", check.getType());
            cv.put("NUMBER", check.getNumber());
            cv.put("AMOUNT", check.getAmount());
            cv.put("EXPIRATION", String.valueOf(check.getExpiration()));
            cv.put("PHOTO", check.getPhoto());
            cv.put("ORIGIN", check.getOrigin());
            cv.put("DESTINY", check.getDestiny());
            cv.put("DESTINYDATE", check.getDestinyDate());

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
            cv.put("TYPE", check.getType());
            cv.put("NUMBER", check.getNumber());
            cv.put("AMOUNT", check.getAmount());
            cv.put("EXPIRATION", check.getExpiration());
            cv.put("PHOTO", check.getPhoto());
            cv.put("ORIGIN", check.getOrigin());
            cv.put("DESTINY", check.getDestiny());
            cv.put("DESTINYDATE", check.getDestinyDate());

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

    public boolean updateCheckDestiny(int id, String destiny, String destinyDate)
            throws SQLiteException {

        ContentValues cv = new ContentValues();
        openDataBase();
        try {
            cv.put("DESTINY", destiny);
            cv.put("DESTINYDATE", destinyDate);

            long valor = database.update("CHECKS", cv, "ID_CHECK=" + id, null);
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

    public List<Check> selectAllCheck(boolean isOwn) {
        String sql = "SELECT * FROM CHECKS ORDER BY ID_CHECK DESC";
        List<Check> arrayChecks = new ArrayList<Check>();
        String number = null, amount = null, origin = null, date = null, destiny = null, destinyDate = null;
        int id, type;
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
                        type = cursor.getInt(cursor.getColumnIndex("TYPE"));
                        number = cursor.getString(cursor
                                .getColumnIndex("NUMBER"));
                        photo = cursor.getBlob(cursor
                                .getColumnIndex("PHOTO"));
                        amount = cursor.getString(cursor
                                .getColumnIndex("AMOUNT"));
                        origin = cursor.getString(cursor
                                .getColumnIndex("ORIGIN"));
                        destiny = cursor.getString(cursor
                                .getColumnIndex("DESTINY"));
                        date = cursor.getString(cursor
                                .getColumnIndex("EXPIRATION"));
                        destinyDate = cursor.getString(cursor
                                .getColumnIndex("DESTINYDATE"));
                        check = new Check(id, type, number, amount, date, origin, destiny, destinyDate, photo);
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
        destiny = null;
        destinyDate = null;
        return arrayChecks;
    }


//    public List<Check> selectAllCheckDelivey() {
//
//        String sql = "SELECT * FROM CHECKS WHERE DESTINY IS NOT NULL AND DESTINY != '' AND TYPE = 0 ORDER BY ID_CHECK DESC";
//        List<Check> arrayChecks = new ArrayList<Check>();
//        String number = null, amount = null, origin = null, date = null, destiny = null, destinyDate = null;
//        int id, type;
//        byte[] photo = null;
//        Cursor cursor = null;
//        openDataBase();
//        if (database != null && database.isOpen()) {
//            try {
//                cursor = database.rawQuery(sql, null);
//                if (cursor != null && cursor.getCount() > 0) {
//                    while (cursor.moveToNext()) {
//                        Check check = null;
//                        id = cursor.getInt(cursor.getColumnIndex("ID_CHECK"));
//                        type = cursor.getInt(cursor.getColumnIndex("TYPE"));
//                        number = cursor.getString(cursor
//                                .getColumnIndex("NUMBER"));
//                        photo = cursor.getBlob(cursor
//                                .getColumnIndex("PHOTO"));
//                        amount = cursor.getString(cursor
//                                .getColumnIndex("AMOUNT"));
//                        origin = cursor.getString(cursor
//                                .getColumnIndex("ORIGIN"));
//                        destiny = cursor.getString(cursor
//                                .getColumnIndex("DESTINY"));
//                        date = cursor.getString(cursor
//                                .getColumnIndex("EXPIRATION"));
//                        destinyDate = cursor.getString(cursor
//                                .getColumnIndex("DESTINYDATE"));
//                        check = new Check(id, type, number, amount, date, origin, destiny, destinyDate, photo);
//                        arrayChecks.add(check);
//                    }
//                }
//            } catch (Exception e) {
//                arrayChecks = null;
//            }
//        } else {
//            arrayChecks = null;
//        }
//        closeDataBase();
//        sql = null;
//        cursor = null;
//        database = null;
//        number = null;
//        amount = null;
//        origin = null;
//        date = null;
//        destiny = null;
//        destinyDate = null;
//        return arrayChecks;
//    }

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


    public Maturities selectMaturitiesWeek(String since, String until) {

        String sql = "SELECT (SELECT SUM(AMOUNT)  FROM CHECKS WHERE TYPE = 0) AS AMOUNT_OTHER_TOTAL," +
                " (SELECT COUNT(ID_CHECK) FROM CHECKS WHERE TYPE = 0) AS QUANTITY_OTHER," +
                " (SELECT SUM(AMOUNT) FROM CHECKS WHERE TYPE = 1 ) AS AMOUNT_OWN_TOTAL," +
                " (SELECT COUNT(ID_CHECK) FROM CHECKS WHERE TYPE = 1) AS QUANTITY_OWN," +
                " (SELECT SUM(AMOUNT) FROM CHECKS WHERE TYPE = 0 AND" +
                "  DATE(substr(EXPIRATION,7,4) || substr(EXPIRATION,4,2) || substr(EXPIRATION,1,2))" +
                "  BETWEEN DATE('" + since + "') AND DATE('" + until + "')) AS AMOUNT_OTHER," +
                " (SELECT SUM(AMOUNT) FROM CHECKS WHERE TYPE = 1 AND" +
                "  DATE(substr(EXPIRATION,7,4) || substr(EXPIRATION,4,2) || substr(EXPIRATION,1,2))" +
                "  BETWEEN DATE('" + since + "') AND DATE('" + until + "')) AS AMOUNT_OWN;";
        Maturities maturities = null;
        Cursor cursor = null;
        openDataBase();
        if (database != null && database.isOpen()) {
            try {
                cursor = database.rawQuery(sql, null);
                if (cursor != null && cursor.getCount() > 0) {
                    while (cursor.moveToNext()) {
                    //    Check check = null;
                        maturities = new Maturities();
                        maturities.setAmountTotalOther(cursor.getString(cursor
                                .getColumnIndex("AMOUNT_OTHER_TOTAL")));
                        maturities.setQuantityOther(cursor.getString(cursor
                                .getColumnIndex("QUANTITY_OTHER")));
                        maturities.setAmountTotalOwn(cursor.getString(cursor
                                .getColumnIndex("AMOUNT_OWN_TOTAL")));
                        maturities.setQuantityOwn(cursor.getString(cursor
                                .getColumnIndex("QUANTITY_OWN")));
                        maturities.setAmountOther(cursor.getString(cursor
                                .getColumnIndex("AMOUNT_OTHER")));
                        maturities.setAmountOwn(cursor.getString(cursor
                                .getColumnIndex("AMOUNT_OWN")));
                    }
                }
            } catch (Exception e) {
                maturities = null;
            }
        } else {
            maturities = null;
        }
        closeDataBase();
        sql = null;
        cursor = null;
        database = null;
        return maturities;
    }


    public List<Check> selectMaturities(String since, String until) {

        String sql = "SELECT * FROM CHECKS WHERE DATE(substr(EXPIRATION,7,4) || substr(EXPIRATION,4,2) || substr(EXPIRATION,1,2)) " +
                "BETWEEN DATE('" + since + "') AND DATE('" + until + "') ORDER BY ID_CHECK DESC;";
        List<Check> checkArrayList = new ArrayList<Check>();
        String number = null, amount = null, origin = null, date = null, destiny = null, destinyDate = null;
        int id, type;
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
                        type = cursor.getInt(cursor.getColumnIndex("TYPE"));
                        number = cursor.getString(cursor
                                .getColumnIndex("NUMBER"));
                        photo = cursor.getBlob(cursor
                                .getColumnIndex("PHOTO"));
                        amount = cursor.getString(cursor
                                .getColumnIndex("AMOUNT"));
                        origin = cursor.getString(cursor
                                .getColumnIndex("ORIGIN"));
                        destiny = cursor.getString(cursor
                                .getColumnIndex("DESTINY"));
                        date = cursor.getString(cursor
                                .getColumnIndex("EXPIRATION"));
                        destinyDate = cursor.getString(cursor
                                .getColumnIndex("DESTINYDATE"));
                        check = new Check(id, type, number, amount, date, origin, destiny, destinyDate, photo);
                        checkArrayList.add(check);
                    }
                }
            } catch (Exception e) {
                checkArrayList = null;
            }
        } else {
            checkArrayList = null;
        }
        closeDataBase();
        sql = null;
        cursor = null;
        database = null;
        number = null;
        amount = null;
        origin = null;
        date = null;
        destiny = null;
        destinyDate = null;
        return checkArrayList;
    }


//    public CheckMaturities QuantityAmountTotalCheckAdd() {
//
//        String sql = "SELECT COUNT(ID_CHECK) AS QUANTITY, SUM(AMOUNT) AS QUANTITYTOTAL FROM CHECKS";
//        String quantityAmount = null;
//        String quantityAmountTotal = null;
//        Cursor cursor = null;
//        CheckMaturities checkInformationAdd = new CheckMaturities();
//        openDataBase();
//        if (database != null && database.isOpen()) {
//            try {
//                cursor = database.rawQuery(sql, null);
//                if (cursor != null && cursor.getCount() > 0) {
//                    while (cursor.moveToNext()) {
//                        quantityAmount = String.valueOf(cursor.getInt(cursor.getColumnIndex("QUANTITY")));
//                        quantityAmountTotal = cursor.getString(cursor.getColumnIndex("QUANTITYTOTAL"));
//
//                        checkInformationAdd.setAmountTotal(quantityAmount);
//                        checkInformationAdd.setAmountQuantityTotal(quantityAmountTotal);
//                    }
//                }
//            } catch (Exception e) {
//                quantityAmount = null;
//            }
//        } else {
//            quantityAmount = null;
//        }
//        closeDataBase();
//        sql = null;
//        cursor = null;
//        database = null;
//
//        return checkInformationAdd;
//    }
//
//    public CheckMaturities QuantityAmountWeekCheckAdd(String startDate, String endDate) {
//
//        String sql = "SELECT COUNT(ID_CHECK) AS QUANTITY, SUM(AMOUNT) AS QUANTITYTOTAL FROM CHECKS WHERE EXPIRATION >= '" + startDate + "' AND EXPIRATION <= '" + endDate + "'";
//        String quantityAmount = null;
//        String quantityAmountTotal = null;
//        Cursor cursor = null;
//        CheckMaturities checkInformationAdd = new CheckMaturities();
//        openDataBase();
//        if (database != null && database.isOpen()) {
//            try {
//                cursor = database.rawQuery(sql, null);
//                if (cursor != null && cursor.getCount() > 0) {
//                    while (cursor.moveToNext()) {
//                        quantityAmount = String.valueOf(cursor.getInt(cursor.getColumnIndex("QUANTITY")));
//                        quantityAmountTotal = cursor.getString(cursor.getColumnIndex("QUANTITYTOTAL"));
//
//                        checkInformationAdd.setAmountWeek(quantityAmountTotal);
//                        checkInformationAdd.setAmountQuantityWeek(quantityAmount);
//                    }
//                }
//            } catch (Exception e) {
//                quantityAmount = null;
//            }
//        } else {
//            quantityAmount = null;
//        }
//        closeDataBase();
//        sql = null;
//        cursor = null;
//        database = null;
//
//        return checkInformationAdd;
//    }
//
//
//    //    INSERT/UPDATE/DELETE CHECK OWN
//    public boolean insertCheckOwn(Check check)
//            throws SQLiteException {
//
//        ContentValues cv = new ContentValues();
//        openDataBase();
//        try {
//            cv.put("NUMBER", check.getNumber());
//            cv.put("AMOUNT", check.getAmount());
//            cv.put("EXPIRATION", String.valueOf(check.getExpiration()));
//            cv.put("PHOTO", check.getPhoto());
//            cv.put("ORIGIN", check.getOrigin());
//
//            long valor = database.insert("CHECKS_OWN", null, cv);
//            closeDataBase();
//            if (valor > 0) {
//                return true;
//            } else {
//                return false;
//            }
//        } catch (SQLiteException e) {
//            closeDataBase();
//            return false;
//        }
//    }
//
//    public boolean updateCheckOwn(Check check)
//            throws SQLiteException {
//
//        ContentValues cv = new ContentValues();
//        openDataBase();
//        try {
//            cv.put("NUMBER", check.getNumber());
//            cv.put("AMOUNT", check.getAmount());
//            cv.put("EXPIRATION", check.getExpiration());
//            cv.put("PHOTO", check.getPhoto());
//            cv.put("ORIGIN", check.getOrigin());
//
//            long valor = database.update("CHECKS_OWN", cv, "ID_CHECK=" + check.getId_check(), null);
//            closeDataBase();
//            if (valor > 0) {
//                return true;
//            } else {
//                return false;
//            }
//        } catch (SQLiteException e) {
//            closeDataBase();
//            return false;
//        }
//    }
//
//    public List<Check> selectAllCheckOwn() {
//
//        String sql = "SELECT * FROM CHECKS_OWN ORDER BY ID_CHECK DESC";
//        List<Check> arrayChecks = new ArrayList<Check>();
//        String number = null, amount = null, origin = null, date = null, destiny = null, destinyDate = null;
//        int id;
//        byte[] photo = null;
//        Cursor cursor = null;
//        openDataBase();
//        if (database != null && database.isOpen()) {
//            try {
//                cursor = database.rawQuery(sql, null);
//                if (cursor != null && cursor.getCount() > 0) {
//                    while (cursor.moveToNext()) {
//                        Check check = null;
//                        id = cursor.getInt(cursor.getColumnIndex("ID_CHECK"));
//                        number = cursor.getString(cursor
//                                .getColumnIndex("NUMBER"));
//
//                        photo = cursor.getBlob(cursor
//                                .getColumnIndex("PHOTO"));
//                        amount = cursor.getString(cursor
//                                .getColumnIndex("AMOUNT"));
//                        origin = cursor.getString(cursor
//                                .getColumnIndex("ORIGIN"));
//                        destiny = cursor.getString(cursor
//                                .getColumnIndex("ORIGIN"));
//                        date = cursor.getString(cursor
//                                .getColumnIndex("EXPIRATION"));
//                        destinyDate = cursor.getString(cursor
//                                .getColumnIndex("DESTINYDATE"));
//
//
//                        check = new Check(id, number, amount, date, origin, destiny, destinyDate, photo);
//                        arrayChecks.add(check);
//                    }
//                }
//            } catch (Exception e) {
//                arrayChecks = null;
//            }
//        } else {
//            arrayChecks = null;
//        }
//        closeDataBase();
//        sql = null;
//        cursor = null;
//        database = null;
//        number = null;
//        amount = null;
//        origin = null;
//        date = null;
//        return arrayChecks;
//    }
//
//    public boolean deleteCheckOwn(List<Check> checks)
//            throws SQLiteException {
//        boolean result = true;
//        ContentValues cv = new ContentValues();
//        openDataBase();
//        try {
//            for (int i = 0; i < checks.size(); i++) {
//                long valor = database.delete("CHECKS_OWN", "ID_CHECK=" + checks.get(i).getId_check(), null);
//                if (valor > 0) {
//                    result = true;
//                } else {
//                    result = false;
//                    break;
//                }
//            }
//
//            closeDataBase();
//        } catch (SQLiteException e) {
//            closeDataBase();
//            result = false;
//        }
//        return result;
//    }
//

}
