package com.jofre.managercheck.maturities.ui;

import com.jofre.managercheck.entities.Check;
import com.jofre.managercheck.entities.CheckMaturities;

import java.util.List;

/**
 * Created by LEO on 3/7/2016.
 */
public interface MaturitiesView {
    void emptyMaturities(String empty);
    void errorMaturities(String error);
    void setMaturitiesCheck(CheckMaturities checkMaturities);
    void setMaturitiesChecks(List<Check> checks);
}
