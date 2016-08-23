package com.jofre.managercheck.navigationmain.events;

import com.jofre.managercheck.entities.CheckInformationAdd;

import java.util.List;

/**
 * Created by LEO on 3/7/2016.
 */
public class NavigationMainEvent {
    private int type;
    private String error;
    private CheckInformationAdd checkInformationAdd;
    public final static int ADD_COMPLETE = 0;

    public CheckInformationAdd getCheckInformationAdd() {
        return checkInformationAdd;
    }

    public void setCheckInformationAdd(CheckInformationAdd checkInformationAdd) {
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

