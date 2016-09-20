package com.argentinatecno.checkmanager.main.activity_maturities.events;
import com.argentinatecno.checkmanager.entities.Check;
import com.argentinatecno.checkmanager.entities.Maturities;

import java.util.List;

public class MaturitiesEvent {
    private String error;
    private Maturities maturities;
    private List<Check> checkList;
    private String empty;
    public static final String errorSelect = "Error al intentar realizar la consulta.";
    public static final String emptySelect = "La consulta no encuentra vencimientos.";

    public List<Check> getCheckList() { return checkList;}
    public void setCheckList(List<Check> checkList) { this.checkList = checkList;}
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
    public Maturities getMaturities() {
        return maturities;
    }
    public void setMaturities(Maturities maturities) {
        this.maturities = maturities;
    }
}
