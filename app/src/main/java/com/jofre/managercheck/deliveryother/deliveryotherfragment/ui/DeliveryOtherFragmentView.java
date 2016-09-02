package com.jofre.managercheck.deliveryother.deliveryotherfragment.ui;

import com.jofre.managercheck.entities.Check;

import java.util.List;

/**
 * Created by LEO on 3/7/2016.
 */
public interface DeliveryOtherFragmentView {
   // void emptyList(String empty);
    void setChecks(List<Check> checks);
    void errorShowList(String error);
    void errorUpdate(String error);
    void closeDialogSuccess(String msg, List<Check> checks);
    void closeDialogError(String msg);
    void dialogEmpty(String msg);
    void closeDialogAccept(String msg, List<Check> checks);
    void closeDialogBackError(String msg);
    void closeDialogCancel();
}
