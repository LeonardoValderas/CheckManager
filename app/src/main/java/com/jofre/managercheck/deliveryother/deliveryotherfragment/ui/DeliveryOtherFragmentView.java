package com.jofre.managercheck.deliveryother.deliveryotherfragment.ui;

import com.jofre.managercheck.entities.Check;

import java.util.List;

/**
 * Created by LEO on 3/7/2016.
 */
public interface DeliveryOtherFragmentView {
    void emptyList(String empty);
    void errorShowList(String error);
    void errorDelete(String error);
    void successDelete(String success);
    void removeCheck(Check check);
    void setChecks(List<Check> checks);

}