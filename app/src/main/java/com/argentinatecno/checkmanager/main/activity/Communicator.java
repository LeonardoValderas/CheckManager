package com.argentinatecno.checkmanager.main.activity;

/**
 * Created by LEO on 9/7/2016.
 */
public interface Communicator {
    void refresh();
    void actionMode();
    void updateCounter(int counter);
    void clearActionMode();
}
