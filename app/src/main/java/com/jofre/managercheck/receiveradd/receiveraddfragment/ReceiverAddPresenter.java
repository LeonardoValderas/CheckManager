package com.jofre.managercheck.receiveradd.receiveraddfragment;

import android.content.Context;

import com.jofre.managercheck.entities.Check;
import com.jofre.managercheck.receiveradd.receiveraddfragment.events.ReceiverAddEvent;
import com.jofre.managercheck.receiveradd.receiveraddfragment.ui.ReceiverAddView;

/**
 * Created by LEO on 4/7/2016.
 */
public interface ReceiverAddPresenter {
    void onCreate();
    void onDestroy();
    void isUpdate(Check check);
    void saveCheck(Check check, Context context);
    void updateCheck(Check check, Context context);
    void onEventMainThread(ReceiverAddEvent event);
    ReceiverAddView getView();
}
