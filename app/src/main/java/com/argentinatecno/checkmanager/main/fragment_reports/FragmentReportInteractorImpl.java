package com.argentinatecno.checkmanager.main.fragment_reports;

public class FragmentReportInteractorImpl implements FragmentReportInteractor {
    FragmentReportRepository repository;

    public FragmentReportInteractorImpl(FragmentReportRepository repository) {
        this.repository = repository;
    }

    @Override
    public void getMaturitiesWeek(String date) {
        repository.getMaturitiesWeek(date);
    }
}
