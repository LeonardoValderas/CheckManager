package com.jofre.managercheck.deliveryother.deliveryothermainactivity;

import com.jofre.managercheck.receiveradd.receiveraddmain.events.ReceiverMainEvent;

/**
 * Created by LEO on 3/7/2016.
 */
public interface DeliveryOtherMainPresenter {
    void onCreate();
    void onDestroy();
    void onEventMainThread(ReceiverMainEvent event);
}
