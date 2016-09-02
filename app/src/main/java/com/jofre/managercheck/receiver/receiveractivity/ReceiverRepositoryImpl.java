package com.jofre.managercheck.receiver.receiveractivity;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by LEO on 3/7/2016.
 */
public class ReceiverRepositoryImpl implements ReceiverRepository {
private EventBus eventBus;

    public ReceiverRepositoryImpl(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void addCheck(String path) {

    }
}
