package com.jofre.managercheck.deliveryown.deliveryownmainactivity;

import com.jofre.managercheck.receiveradd.receiveraddmain.events.ReceiverMainEvent;

/**
 * Created by LEO on 3/7/2016.
 */
public interface DeliveryOwnMainPresenter {
    void onCreate();
    void onDestroy();
    void onEventMainThread(ReceiverMainEvent event);
}
