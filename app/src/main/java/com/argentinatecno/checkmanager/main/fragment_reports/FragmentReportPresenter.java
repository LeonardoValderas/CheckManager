package com.argentinatecno.checkmanager.main.fragment_reports;

import com.argentinatecno.checkmanager.main.fragment_reports.events.FragmentReportEvent;

public interface FragmentReportPresenter {
    void getMaturitiesWeek(String date);
    void onCreate();
    void onDestroy();
    void onEventMainThread(FragmentReportEvent event);
}
