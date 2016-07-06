package com.jofre.managercheck.receiveradd.ui;

import com.jofre.managercheck.entities.Check;

/**
 * Created by LEO on 3/7/2016.
 */
public interface ReceiverAddView {
    void onAddInit();
    void onAddComplete();
    void onAddError(String error);
    void hideUIComponent();
    void showUIComponent();
    void hideProgress();
    void showProgress();
void saveCheck();
}
