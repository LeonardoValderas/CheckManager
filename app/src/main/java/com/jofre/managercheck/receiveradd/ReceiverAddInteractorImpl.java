package com.jofre.managercheck.receiveradd;

import android.content.Context;

import com.jofre.managercheck.entities.Check;

/**
 * Created by LEO on 4/7/2016.
 */
public class ReceiverAddInteractorImpl implements ReceiverAddInteractor {
    ReceiverAddRepository repository;

    public ReceiverAddInteractorImpl(ReceiverAddRepository repository) {
        this.repository = repository;
    }

    @Override
    public void saveCheck(Check check, Context context) {
        repository.saveCheck(check, context);
    }

    @Override
    public void updateCheck(Check check, Context context) {
        repository.updateCheck(check, context);
    }
}
