package com.jofre.managercheck.receiveradd.receiveraddmain;

import com.jofre.managercheck.receiveradd.receiveraddmain.events.ReceiverMainEvent;
import com.jofre.managercheck.receiveradd.receiveraddmain.ui.ReceiverMainView;

/**
 * Created by LEO on 3/7/2016.
 */
public interface ReceiverMainPresenter {
    void onCreate();

    void onDestroy();

    void onEventMainThread(ReceiverMainEvent event);
    //   void saveCheck(Check check);

    ReceiverMainView getView();
}
