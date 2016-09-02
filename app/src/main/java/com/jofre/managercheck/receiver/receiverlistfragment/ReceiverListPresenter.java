package com.jofre.managercheck.receiver.receiverlistfragment;

import android.content.Context;

import com.jofre.managercheck.entities.Check;
import com.jofre.managercheck.lib.base.ImageLoader;
import com.jofre.managercheck.receiver.receiverlistfragment.events.ReceiverListEvent;

import java.util.List;

/**
 * Created by LEO on 8/7/2016.
 */
public interface ReceiverListPresenter {
    void onCreate();
    void onDestroy();
    void removeCheck(List<Check> checks);
    void getChecks();
    void onEventMainThread(ReceiverListEvent event);
    void showAlert(Context context, ImageLoader imageLoader, byte[] bytes);
}
