package com.jofre.managercheck.receiveraddlist;

import android.content.Context;

import com.jofre.managercheck.entities.Check;

/**
 * Created by LEO on 8/7/2016.
 */
public interface ReceiverAddListInteractor {
    void removeCheck(Check check);
    void getChecks(Context context);
}
