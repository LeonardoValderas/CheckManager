package com.argentinatecno.checkmanager.main.fragment_reports.ui;

import com.argentinatecno.checkmanager.entities.Check;
import com.argentinatecno.checkmanager.entities.Maturities;

public interface FragmentReportView {
    void getError(String error);
    void setMaturitiesWeek(Maturities maturities);
}
