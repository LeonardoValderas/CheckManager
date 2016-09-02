package com.jofre.managercheck.navigationmain.events;

import com.jofre.managercheck.entities.CheckMaturities;

/**
 * Created by LEO on 3/7/2016.
 */
public class NavigationMainEvent {
    private int type;
    private String error;
    private CheckMaturities checkInformationAdd;
    public final static int ADD_COMPLETE = 0;

    public CheckMaturities getCheckInformationAdd() {
        return checkInformationAdd;
    }

    public void setCheckInformationAdd(CheckMaturities checkInformationAdd) {
        this.checkInformationAdd = checkInformationAdd;
    }

    public final static int ADD_ERROR = 1;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}

