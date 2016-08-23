package com.jofre.managercheck.deliveryown.deliveryownfragmentlist;

import android.content.Context;

import com.jofre.managercheck.deliveryown.deliveryownfragmentlist.events.DeliveryOwnFragmentListEvent;
import com.jofre.managercheck.entities.Check;
import com.jofre.managercheck.lib.base.ImageLoader;

import java.util.List;

/**
 * Created by LEO on 8/7/2016.
 */
public interface DeliveryOwnFragmentListPresenter {
    void onCreate();
    void onDestroy();
    void removeCheck(List<Check> checks);
    void getChecks();
    void onEventMainThread(DeliveryOwnFragmentListEvent event);
    void showAlert(Context context, ImageLoader imageLoader, byte[] bytes);
}
