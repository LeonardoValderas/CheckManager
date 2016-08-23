package com.jofre.managercheck.deliveryother.deliveryotherfragment;

import android.content.Context;

import com.jofre.managercheck.deliveryother.deliveryotherfragment.events.DeliveryOtherFragmentEvent;
import com.jofre.managercheck.entities.Check;
import com.jofre.managercheck.lib.base.ImageLoader;

import java.util.List;

/**
 * Created by LEO on 8/7/2016.
 */
public interface DeliveryOtherFragmentPresenter {
    void onCreate();
    void onDestroy();
    void removeCheck(List<Check> checks);
    void getChecks();
    void onEventMainThread(DeliveryOtherFragmentEvent event);
    void showAlert(Context context, ImageLoader imageLoader, byte[] bytes);
}
