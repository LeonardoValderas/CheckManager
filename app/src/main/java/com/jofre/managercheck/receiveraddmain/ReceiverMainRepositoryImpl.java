package com.jofre.managercheck.receiveraddmain;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by LEO on 3/7/2016.
 */
public class ReceiverMainRepositoryImpl implements ReceiverMainRepository {
private EventBus eventBus;

    public ReceiverMainRepositoryImpl(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void addCheck(String path) {

    }
}
