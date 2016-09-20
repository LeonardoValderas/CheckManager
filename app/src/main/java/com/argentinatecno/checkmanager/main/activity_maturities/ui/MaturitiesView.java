package com.argentinatecno.checkmanager.main.activity_maturities.ui;

import com.argentinatecno.checkmanager.entities.Check;
import com.argentinatecno.checkmanager.entities.Maturities;

import java.util.List;

public interface MaturitiesView {
    void emptyMaturities(String empty);
    void errorMaturities(String error);
    void setMaturitiesCheck(Maturities checkMaturities);
    void setMaturitiesAdapter(List<Check> checks);
}
