package com.jofre.managercheck.deliveryother.deliveryothermainactivity;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by LEO on 3/7/2016.
 */
public class DeliveryOtherMainRepositoryImpl implements DeliveryOtherMainRepository {
private EventBus eventBus;

    public DeliveryOtherMainRepositoryImpl(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void addCheck(String path) {

    }
}
