package com.jofre.managercheck.receiveraddlist;

import android.content.Context;

import com.jofre.managercheck.entities.Check;
import com.jofre.managercheck.lib.base.ImageLoader;
import com.jofre.managercheck.receiveraddlist.events.CheckListEvent;

import java.util.List;

/**
 * Created by LEO on 8/7/2016.
 */
public interface ReceiverAddListPresenter {
    void onCreate();
    void onDestroy();
    void removeCheck(List<Check> checks);
    void getChecks();
    void onEventMainThread(CheckListEvent event);
    void showAlert(Context context, ImageLoader imageLoader, byte[] bytes);
}
