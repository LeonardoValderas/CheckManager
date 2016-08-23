package com.jofre.managercheck.receiveradd.receiveraddmain;

/**
 * Created by LEO on 9/7/2016.
 */
public interface Communicator {
    void refresh();
    void actionMode();
    void updateCounter(int counter);
    void clearActionMode();
}
