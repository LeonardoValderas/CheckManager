package com.jofre.managercheck.receiveraddlist;

import android.content.Context;

import com.jofre.managercheck.entities.Check;

/**
 * Created by LEO on 8/7/2016.
 */
public class ReceiverAddListInteractorImpl implements ReceiverAddListInteractor {
    ReceiverAddListRepository repository;

    public ReceiverAddListInteractorImpl(ReceiverAddListRepository repository) {
        this.repository = repository;
    }

    @Override
    public void removeCheck(Check check) {
        repository.removeCheck(check);
    }

    @Override
    public void getChecks(Context context) {
        repository.selectAll(context);
    }

}

