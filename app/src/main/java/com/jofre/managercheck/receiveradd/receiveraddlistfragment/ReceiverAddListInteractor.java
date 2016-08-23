package com.jofre.managercheck.receiveradd.receiveraddlistfragment;

import com.jofre.managercheck.entities.Check;

import java.util.List;

/**
 * Created by LEO on 8/7/2016.
 */
public interface ReceiverAddListInteractor {
    void removeCheck(List<Check> checks);
    void getChecks();
}
