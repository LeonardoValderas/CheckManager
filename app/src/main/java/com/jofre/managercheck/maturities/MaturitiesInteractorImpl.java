package com.jofre.managercheck.maturities;

/**
 * Created by LEO on 8/7/2016.
 */
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

