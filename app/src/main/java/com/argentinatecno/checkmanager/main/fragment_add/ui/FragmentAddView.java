package com.argentinatecno.checkmanager.main.fragment_add.ui;

import com.argentinatecno.checkmanager.entities.Check;

import java.util.List;

public interface FragmentAddView {
    void onAddComplete();

    void onAddError(String error);

    void enableUIComponent();

    void unableUIComponent();

    void cleanUIComponent();

    void saveCheck();
    void isUpdateIUElemente(Check check);
}
