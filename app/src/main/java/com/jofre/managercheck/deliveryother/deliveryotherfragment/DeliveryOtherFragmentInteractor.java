package com.jofre.managercheck.deliveryother.deliveryotherfragment;

import com.jofre.managercheck.entities.Check;

import java.util.List;

/**
 * Created by LEO on 8/7/2016.
 */
public interface DeliveryOtherFragmentInteractor {
    void updateCheck(int id, String delivery, String deliveryDate, boolean isUpdate);
    void getChecks();
}
