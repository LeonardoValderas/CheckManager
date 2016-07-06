package com.jofre.managercheck.receivermain;

import com.jofre.managercheck.receivermain.events.ReceiverMainEvent;

/**
 * Created by LEO on 3/7/2016.
 */
public interface ReceiverMainPresenter {
    void onCreate();

    void onDestroy();

    void onEventMainThread(ReceiverMainEvent event);
    //   void saveCheck(Check check);


}
