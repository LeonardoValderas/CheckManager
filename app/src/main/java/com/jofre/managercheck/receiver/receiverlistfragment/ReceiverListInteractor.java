package com.jofre.managercheck.receiver.receiverlistfragment;

import com.jofre.managercheck.entities.Check;

import java.util.List;

/**
 * Created by LEO on 8/7/2016.
 */
public interface ReceiverListInteractor {
    void removeCheck(List<Check> checks);
    void getChecks();
}
