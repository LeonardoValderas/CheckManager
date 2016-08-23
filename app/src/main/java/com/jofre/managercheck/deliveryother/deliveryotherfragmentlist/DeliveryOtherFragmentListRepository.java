package com.jofre.managercheck.deliveryother.deliveryotherfragmentlist;

import com.jofre.managercheck.entities.Check;

import java.util.List;

/**
 * Created by LEO on 8/7/2016.
 */
public interface DeliveryOtherFragmentListRepository {
    void removeCheck(List<Check> checks);
    void selectAll();
}
