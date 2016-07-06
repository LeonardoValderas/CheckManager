package com.jofre.managercheck.receiverlist.ui;

/**
 * Created by LEO on 3/7/2016.
 */
public interface ReceiverListView {
    void onAddInit();
    void onAddComplete();
    void onAddError(String error);

    void hideComponent();
    void showComponent();
    void hideProgress();
    void showProgress();

}
