package com.jofre.managercheck.receiveraddmain;

import com.jofre.managercheck.receiveraddmain.events.ReceiverMainEvent;

/**
 * Created by LEO on 3/7/2016.
 */
public interface ReceiverMainPresenter {
    void onCreate();

    void onDestroy();

    void onEventMainThread(ReceiverMainEvent event);
    //   void saveCheck(Check check);


}
