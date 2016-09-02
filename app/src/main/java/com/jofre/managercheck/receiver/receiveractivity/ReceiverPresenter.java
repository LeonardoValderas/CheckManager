package com.jofre.managercheck.receiver.receiveractivity;

import com.jofre.managercheck.receiver.receiveractivity.events.ReceiverActivityEvent;
import com.jofre.managercheck.receiver.receiveractivity.ui.ReceiverView;

/**
 * Created by LEO on 3/7/2016.
 */
public interface ReceiverPresenter {
    void onCreate();

    void onDestroy();

    void onEventMainThread(ReceiverActivityEvent event);
    //   void saveCheck(Check check);

    ReceiverView getView();
}
