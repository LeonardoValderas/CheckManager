package com.jofre.managercheck.deliveryown.deliveryownfragmentlist;

import com.jofre.managercheck.entities.Check;

import java.util.List;

/**
 * Created by LEO on 8/7/2016.
 */
public interface DeliveryOwnFragmentListInteractor {
    void removeCheck(List<Check> checks);
    void getChecks();
}
