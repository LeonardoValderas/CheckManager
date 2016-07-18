package com.jofre.managercheck.receiveradd;

import android.content.Context;

import com.jofre.managercheck.entities.Check;
import com.jofre.managercheck.receiveradd.events.ReceiverAddEvent;

/**
 * Created by LEO on 4/7/2016.
 */
public interface ReceiverAddPresenter {
    void onCreate();
    void onDestroy();
    void siUpdate(Check check);
    void saveCheck(Check check, Context context);
    void updateCheck(Check check, Context context);
    void onEventMainThread(ReceiverAddEvent event);
}
