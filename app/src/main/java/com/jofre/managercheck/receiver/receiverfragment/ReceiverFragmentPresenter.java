package com.jofre.managercheck.receiver.receiverfragment;

import android.content.Context;

import com.jofre.managercheck.entities.Check;
import com.jofre.managercheck.receiver.receiverfragment.events.ReceiverFragmentEvent;
import com.jofre.managercheck.receiver.receiverfragment.ui.ReceiverView;

/**
 * Created by LEO on 4/7/2016.
 */
public interface ReceiverFragmentPresenter {
    void onCreate();
    void onDestroy();
    void isUpdate(Check check);
    void saveCheck(Check check, Context context);
    void updateCheck(Check check, Context context);
    void onEventMainThread(ReceiverFragmentEvent event);
    ReceiverView getView();
}
