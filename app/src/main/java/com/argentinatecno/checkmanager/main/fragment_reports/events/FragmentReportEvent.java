package com.argentinatecno.checkmanager.main.fragment_reports.events;

import com.argentinatecno.checkmanager.entities.Check;
import com.argentinatecno.checkmanager.entities.Maturities;

public class FragmentReportEvent {
    private String error;
    private Maturities maturities;
    public final static String ERROR= "Error al obtener los vencimientos de la semana.";

    public Maturities getMaturities() {
        return maturities;
    }

    public void setMaturities(Maturities maturities) {
        this.maturities = maturities;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}

