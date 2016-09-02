package com.jofre.managercheck.receiver.receiveractivity.events;

/**
 * Created by LEO on 3/7/2016.
 */
public class ReceiverActivityEvent {
    private int type;
    private String error;
    public final static int ADD_INIT = 0;
    public final static int ADD_COMPLETE = 1;
    public final static int ADD_ERROR = 2;

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

