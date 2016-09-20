package com.argentinatecno.checkmanager.main.activity_maturities;

public class MaturitiesInteractorImpl implements MaturitiesInteractor {
    MaturitiesRepository repository;

    public MaturitiesInteractorImpl(MaturitiesRepository repository) {
        this.repository = repository;
    }

    @Override
    public void getMaturitiesCheck(String since, String until) {
     repository.selectMaturities(since, until);
    }
}

