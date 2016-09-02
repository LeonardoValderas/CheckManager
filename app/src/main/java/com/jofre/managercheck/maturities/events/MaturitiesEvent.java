package com.jofre.managercheck.maturities.events;

import com.jofre.managercheck.entities.Check;
import com.jofre.managercheck.entities.CheckMaturities;

import java.util.List;

/**
 * Created by LEO on 8/7/2016.
 */
public class MaturitiesEvent {
    private String error;
    private CheckMaturities checkMaturities;
    private String empty;
    public static final String errorSelect = "Error al intentar realizar la consulta.";
    public static final String emptySelect = "La consulta no encuentra vencimientos.";

    public String getError() {
        return error;
    }
    public String getEmpty() {
        return empty;
    }
    public void setEmpty(String empty) {
        this.empty = empty;
    }
    public void setError(String error) {
        this.error = error;
    }
    public CheckMaturities getCheckMaturities() {
        return checkMaturities;
    }
    public void setCheckMaturities(CheckMaturities checkMaturities) {
        this.checkMaturities = checkMaturities;
    }
}
