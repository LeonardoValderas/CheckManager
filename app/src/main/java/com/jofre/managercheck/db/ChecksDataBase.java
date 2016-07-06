package com.jofre.managercheck.db;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by LEO on 3/7/2016.
 */
@Database(name = ChecksDataBase.NAME, version = ChecksDataBase.VERSION)
public class ChecksDataBase{
    public static final int VERSION = 1;
    public static final String NAME = "Checks";
}
