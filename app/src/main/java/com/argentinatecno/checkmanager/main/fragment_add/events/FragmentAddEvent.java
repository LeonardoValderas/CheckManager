package com.argentinatecno.checkmanager.main.fragment_add.events;

import com.argentinatecno.checkmanager.entities.Check;

import java.util.List;

public class FragmentAddEvent {
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
