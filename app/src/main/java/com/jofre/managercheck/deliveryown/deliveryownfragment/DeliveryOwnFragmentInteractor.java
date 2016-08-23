package com.jofre.managercheck.deliveryown.deliveryownfragment;

import android.content.Context;

import com.jofre.managercheck.entities.Check;

/**
 * Created by LEO on 4/7/2016.
 */
public interface DeliveryOwnFragmentInteractor {
    void saveCheck(Check check, Context context);
    void updateCheck(Check check, Context context);
}
