package com.jofre.managercheck.deliveryown.deliveryownmainactivity;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by LEO on 3/7/2016.
 */
public class DeliveryOwnMainRepositoryImpl implements DeliveryOwnMainRepository {
private EventBus eventBus;

    public DeliveryOwnMainRepositoryImpl(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void addCheck(String path) {

    }
}
