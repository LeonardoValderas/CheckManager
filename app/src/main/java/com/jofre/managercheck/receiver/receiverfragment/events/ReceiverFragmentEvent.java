package com.jofre.managercheck.receiver.receiverfragment.events;

/**
 * Created by LEO on 4/7/2016.
 */
public class ReceiverFragmentEvent {
    private int Type;
    private String error;
    public static final int COMPLETE_EVENT = 0;

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
