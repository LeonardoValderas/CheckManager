package com.jofre.managercheck.deliveryown.deliveryownfragment.ui;

import com.jofre.managercheck.entities.Check;

/**
 * Created by LEO on 3/7/2016.
 */
public interface DeliveryOwnFragmentView {
    void onAddComplete();

    void onAddError(String error);

    void enableUIComponent();

    void unableUIComponent();

    void cleanUIComponent();

    void saveCheck();
    void isUpdateIUElemente(Check check);
}
