package com.jofre.managercheck.deliveryother.deliveryotherfragment;

import com.jofre.managercheck.entities.Check;

import java.util.List;

/**
 * Created by LEO on 8/7/2016.
 */
public interface DeliveryOtherFragmentRepository {
    void removeCheck(List<Check> checks);
    void selectAll();
}
